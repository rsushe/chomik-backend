package com.fakecdek.delivery.service;


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


    public ApplyForDeliveryResponse handleDeliveryRequest(ApplyForDeliveryRequest request) throws InvalidCountryParameterException {
        validator.validateApplicationRequest(request);
        TrackLinkDto trackLinkDto = trackingService.generateTrackLink();
        return new ApplyForDeliveryResponse("The delivery request has been processed", TAKEN, trackLinkDto);
    }

}
