package com.chomik.chomikdelivery.service;


import com.chomik.chomikdelivery.domain.Shipment;
import com.chomik.chomikdelivery.event.ApplyForDeliveryEvent;
import com.chomik.chomikdelivery.exception.ShipmentNotFoundException;
import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.repository.ShipmentRepository;
import com.chomik.chomikdelivery.service.mapper.ShipmentMapper;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import com.chomik.delivery.client.dto.UserAddressDto;
import com.fakecdek.delivery.mock.model.dto.DeliveryStatus;
import com.fakecdek.delivery.mock.model.dto.ShipmentDto;
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest;
import com.winter.event.service.publisher.EventPublisher;
import com.fakecdek.deliverymockclient.dto.TrackLinkDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ShipmentService {
    private static final Logger log = LoggerFactory.getLogger(ShipmentService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private EventPublisher publisher;

    @Autowired
    private ShipmentMapper shipmentMapper;


    @Transactional
    public ShipmentDto createShipment(CreateShipmentRequest request) throws UserAddressNotFoundException {
        UserAddressDto userAddressFrom = validateAndGetAddress(request.getUserAddressFrom());
        UserAddressDto userAddressTo = validateAndGetAddress(request.getUserAddressTo());
        log.info("find user addresses");

        Shipment newShipment = new Shipment(request.getOrderId(), request.getUserAddressFrom(), request.getUserAddressTo(), DeliveryStatus.CREATED);
        newShipment = shipmentRepository.save(newShipment);

        log.info("successfully create, id {}", newShipment.getId());

        publisher.publishEvent(new ApplyForDeliveryEvent(
                newShipment.getId(),
                userAddressFrom.getAddress(), userAddressTo.getAddress(),
                request.getUserFromPhone(), request.getUserToPhone())
        );
        log.info("successfully publish event");
        return shipmentMapper.toDto(newShipment);
    }

    @Transactional
    public void updateStatusAndTrackLink(String shipmentId, TrackLinkDto trackLink) throws ShipmentNotFoundException {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ShipmentNotFoundException("Couldn't find shipment entity with id: " + shipmentId));

        shipment.setStatus(DeliveryStatus.TAKEN_INTO_PROCESS);
        shipment.setTrackLink(trackLink.link());
        shipmentRepository.save(shipment);
    }

    @Transactional
    public void updateShipmentStatus(UpdateShipmentStatusRequest request) throws ShipmentNotFoundException {
        Shipment shipment = shipmentRepository.findById(request.shipmentId())
                .orElseThrow(() -> new ShipmentNotFoundException("Couldn't find shipment entity with id: " + request.shipmentId()));

        shipment.setStatus(request.status());
        if (request.status() == DeliveryStatus.REJECTED_BY_RECIPIENT) shipment.setInReturn(true);
        shipmentRepository.save(shipment);

        // todo send notification to user
    }

    private UserAddressDto validateAndGetAddress(String addressId) throws UserAddressNotFoundException {
        return userAddressService.getUserAddressById(addressId)
                .orElseThrow(() -> new UserAddressNotFoundException("Couldn't find user address with id: " + addressId));
    }
}
