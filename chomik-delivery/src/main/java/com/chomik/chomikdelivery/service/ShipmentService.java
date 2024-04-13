package com.chomik.chomikdelivery.service;


import com.chomik.chomikdelivery.domain.Shipment;
import com.chomik.chomikdelivery.event.ApplyForDeliveryEvent;
import com.chomik.chomikdelivery.exception.ShipmentNotFoundException;
import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.repository.ShipmentRepository;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import com.chomik.delivery.client.dto.ShipmentStatus;
import com.chomik.delivery.client.dto.UserAddressDto;
import com.chomik.event.service.publisher.EventPublisher;
import com.fakecdek.deliverymockclient.dto.TrackLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private EventPublisher publisher;


    @Transactional
    public void createShipment(CreateShipmentRequest request) throws UserAddressNotFoundException {
        UserAddressDto userAddressFrom = validateAndGetAddress(request.getUserAddressFrom());
        UserAddressDto userAddressTo = validateAndGetAddress(request.getUserAddressTo());

        Shipment newShipment = new Shipment(request.getOrderId(), request.getUserAddressFrom(), request.getUserAddressTo(), ShipmentStatus.CREATED);
        newShipment = shipmentRepository.save(newShipment);

        publisher.publishEvent(new ApplyForDeliveryEvent(
                newShipment.getId(),
                userAddressFrom.getAddress(), userAddressTo.getAddress(),
                request.getUserFromPhone(), request.getUserToPhone())
        );
    }


    @Transactional
    public void updateStatusAndTrackLink(String shipmentId, TrackLinkDto trackLink) throws ShipmentNotFoundException {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ShipmentNotFoundException("Couldn't find shipment entity with id: " + shipmentId));

        shipment.setStatus(ShipmentStatus.CONFIRMED_BY_DELIVERY_SERVICE);
        shipment.setTrackLink(trackLink.link());
        shipmentRepository.save(shipment);
    }

    private UserAddressDto validateAndGetAddress(String addressId) throws UserAddressNotFoundException {
        return userAddressService.getUserAddressById(addressId)
                .orElseThrow(() -> new UserAddressNotFoundException("Couldn't find user address with id: " + addressId));
    }
}
