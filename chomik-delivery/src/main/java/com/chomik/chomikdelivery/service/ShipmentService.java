package com.chomik.chomikdelivery.service;


import com.chomik.chomikdelivery.domain.Shipment;
import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.repository.ShipmentRepository;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import com.chomik.delivery.client.dto.ShipmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserAddressService userAddressService;



    public void createShipment(CreateShipmentRequest request) throws UserAddressNotFoundException {
        checkAddressIsCorrect(request.getAddressFrom());
        checkAddressIsCorrect(request.getAddressTo());

        Shipment newShipment = new Shipment(request.getOrderId(), request.getAddressFrom(), request.getAddressTo(), ShipmentStatus.CREATED);
        shipmentRepository.save(newShipment);
    }

    private void checkAddressIsCorrect(String addressId) throws UserAddressNotFoundException {
        userAddressService.getUserAddressById(addressId)
                .orElseThrow(() -> new UserAddressNotFoundException("Couldn't find user address with id: " + addressId));
    }
}
