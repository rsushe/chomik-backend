package com.chomik.chomikdelivery.resource;


import com.chomik.chomikdelivery.domain.Shipment;
import com.chomik.chomikdelivery.exception.UserAddressNotFoundException;
import com.chomik.chomikdelivery.service.ShipmentService;
import com.chomik.delivery.client.dto.CreateShipmentRequest;
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/delivery/shipment")
public class ShipmentResource {


    @Autowired
    private ShipmentService shipmentService;

    private static final Logger log = LoggerFactory.getLogger(ShipmentResource.class);


    @PostMapping
    public ResponseEntity<?> createShipment(@RequestBody @Valid CreateShipmentRequest request) {
        try {
            Shipment shipment = shipmentService.createShipment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
        } catch (UserAddressNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/status")
    public void updateShipmentStatus(@RequestBody @Valid UpdateShipmentStatusRequest request) {
        try {
            log.info("Received UpdateShipmentStatusRequest: {}", request.toString());
            shipmentService.updateShipmentStatus(request);
        } catch (Exception e) {
            log.error("Error while updating shipment status", e);
        }
    }
}
