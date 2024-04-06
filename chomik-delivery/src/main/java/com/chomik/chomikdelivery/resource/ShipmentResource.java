package com.chomik.chomikdelivery.resource;


import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.service.ShipmentService;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/delivery/shipment")
public class ShipmentResource {


    @Autowired
    private ShipmentService shipmentService;


    @PostMapping
    public ResponseEntity<?> createShipment(@RequestBody CreateShipmentRequest request) {
        try {
            shipmentService.createShipment(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAddressNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
