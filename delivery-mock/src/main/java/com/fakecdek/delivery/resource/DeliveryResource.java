package com.fakecdek.delivery.resource;


import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse;
import com.fakecdek.delivery.exception.InvalidCountryParameterException;
import com.fakecdek.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse.ResponseStatus.REJECTED;

@RestController
@RequestMapping("/api/fakecdek")
public class DeliveryResource {

    @Autowired
    private DeliveryService deliveryService;

    private static final Logger log = LoggerFactory.getLogger(DeliveryResource.class);

    @PostMapping("/apply")
    public ResponseEntity<?> applyForShipment(@Valid @RequestBody ApplyForDeliveryRequest request) {
        try {
            return ResponseEntity.ok(deliveryService.handleDeliveryRequest(request));
        } catch (InvalidCountryParameterException e) {
            return ResponseEntity.badRequest().body(new ApplyForDeliveryResponse(e.getMessage(), REJECTED, null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PostMapping("/shipment/status")
    public void updateShipmentStatus(@Valid @RequestBody UpdateShipmentStatusRequest request) {
        log.info("Received UpdateShipmentStatusRequest: {}", request.toString());
        deliveryService.handleDeliveryStatusUpdate(request);
    }
}
