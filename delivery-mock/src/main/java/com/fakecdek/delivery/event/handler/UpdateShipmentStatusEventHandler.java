package com.fakecdek.delivery.event.handler;

import com.chomik.delivery.client.DeliveryClient;
import com.winter.event.service.exception.EventHandleException;
import com.winter.event.service.handler.EventHandler;
import com.fakecdek.delivery.event.UpdateShipmentStatusEvent;
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateShipmentStatusEventHandler extends DeliveryMockEventHandler<UpdateShipmentStatusEvent> implements EventHandler<UpdateShipmentStatusEvent> {

    @Autowired
    private DeliveryClient deliveryClient;

    private static final Logger log = LoggerFactory.getLogger(UpdateShipmentStatusEventHandler.class);

    @Override
    public void handle(UpdateShipmentStatusEvent event) throws EventHandleException {
        log.info("Sending UpdateShipmentStatusRequest: {}", event.toString());
        deliveryClient.updateShipmentStatus(new UpdateShipmentStatusRequest(event.getShipmentId(), event.getStatus()));
    }
}
