package com.chomik.chomikdelivery.event.handler;

import com.chomik.chomikdelivery.event.ApplyForDeliveryEvent;
import com.chomik.chomikdelivery.service.ShipmentService;
import com.chomik.delivery.client.dto.AddressDto;
import com.chomik.event.service.handler.EventHandler;
import com.fakecdek.deliverymockclient.DeliveryMockClient;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse;
import com.fakecdek.deliverymockclient.dto.DeliveryAddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ApplyForDeliveryEventHandler extends DeliveryEventHandler<ApplyForDeliveryEvent> implements EventHandler<ApplyForDeliveryEvent> {

    @Autowired
    private DeliveryMockClient deliveryMockClient;

    @Autowired
    private ShipmentService shipmentService;

    private static final Logger log = LoggerFactory.getLogger(ApplyForDeliveryEventHandler.class);


    @Override
    public void handle(ApplyForDeliveryEvent event) {
        ApplyForDeliveryRequest request = new ApplyForDeliveryRequest(
                DeliveryAddressMapper.convertTo(event.getAddressFrom()),
                event.getUserFromMobile(),
                DeliveryAddressMapper.convertTo(event.getAddressTo()),
                event.getUserToMobile()
        );

        ResponseEntity<?> response = deliveryMockClient.applyForDelivery(request);
        handleDeliveryMockResponse(response, event);
    }

    private void handleDeliveryMockResponse(ResponseEntity<?> response, ApplyForDeliveryEvent event) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Application for shipment {} wasn't successful", event.getShipmentId());
            return;
        }

        ApplyForDeliveryResponse applyForDeliveryResponse = getApplyForDeliveryResponse(response);
        if (applyForDeliveryResponse == null) {
            log.error("Couldn't read ApplyForDeliveryResponse from delivery mock");
            return;
        }

        if (applyForDeliveryResponse.status() == ApplyForDeliveryResponse.ResponseStatus.REJECTED) {
            // todo send user notify with reject
            log.info("The delivery-mock service rejected to process the delivery request. Reason {}", applyForDeliveryResponse.message());
            return;
        }

        try {
            shipmentService.updateStatusAndTrackLink(event.getShipmentId(), applyForDeliveryResponse.trackLink());
            // todo send user notify with success
        } catch (Exception e) {
            log.error("Error during updating shipment", e);
        }
    }

    private ApplyForDeliveryResponse getApplyForDeliveryResponse(ResponseEntity<?> response) {
        try {
            return (ApplyForDeliveryResponse) response.getBody();
        } catch (Exception e) {
            log.error("Delivery mock response has unknown body type");
            return null;
        }
    }


    private static class DeliveryAddressMapper {
        public static DeliveryAddressDto convertTo(AddressDto addressDto) {
            return new DeliveryAddressDto(
                    addressDto.getCountry(), addressDto.getCity(),
                    addressDto.getStreet(), addressDto.getHouse(),
                    addressDto.getFloor(), addressDto.getFlat(),
                    addressDto.getExtraInfo());
        }
    }
}
