package com.fakecdek.delivery.resource;


import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse;
import com.fakecdek.delivery.exception.InvalidCountryParameterException;
import com.fakecdek.delivery.service.DeliveryService;
import jakarta.validation.Valid;
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
}
