package com.chomik.chomikdelivery.service;


import com.chomik.chomikdelivery.domain.Shipment;
import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.repository.ShipmentRepository;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import com.chomik.delivery.client.dto.ShipmentStatus;
import com.chomik.delivery.client.dto.UserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserAddressService userAddressService;


    @Transactional
    public void createShipment(CreateShipmentRequest request) throws UserAddressNotFoundException {
        UserAddressDto userAddressFrom = validateAndGetAddress(request.getUserAddressFrom());
        UserAddressDto userAddressTo = validateAndGetAddress(request.getUserAddressTo());

        Shipment newShipment = new Shipment(request.getOrderId(), request.getUserAddressFrom(), request.getUserAddressTo(), ShipmentStatus.CREATED);
        shipmentRepository.save(newShipment);
    }

    private UserAddressDto validateAndGetAddress(String addressId) throws UserAddressNotFoundException {
        return userAddressService.getUserAddressById(addressId)
                .orElseThrow(() -> new UserAddressNotFoundException("Couldn't find user address with id: " + addressId));
    }
}
