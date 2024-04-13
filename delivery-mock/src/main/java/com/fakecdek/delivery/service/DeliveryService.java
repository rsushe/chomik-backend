package com.fakecdek.delivery.service;


import com.winter.event.service.publisher.EventPublisher;
import com.fakecdek.delivery.event.UpdateShipmentStatusEvent;
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse;
import com.fakecdek.deliverymockclient.dto.TrackLinkDto;
import com.fakecdek.delivery.exception.InvalidCountryParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse.ResponseStatus.TAKEN;


@Service
public class DeliveryService {

    @Autowired
    private AddressValidator validator;

    @Autowired
    private TrackingService trackingService;

    @Autowired
    private EventPublisher eventPublisher;


    public ApplyForDeliveryResponse handleDeliveryRequest(ApplyForDeliveryRequest request) throws InvalidCountryParameterException {
        validator.validateApplicationRequest(request);
        TrackLinkDto trackLinkDto = trackingService.generateTrackLink();
        return new ApplyForDeliveryResponse("The delivery request has been processed", TAKEN, trackLinkDto);
    }

    public void handleDeliveryStatusUpdate(UpdateShipmentStatusRequest request) {
        eventPublisher.publishEvent(new UpdateShipmentStatusEvent(request.shipmentId(), request.status()));
    }

}
