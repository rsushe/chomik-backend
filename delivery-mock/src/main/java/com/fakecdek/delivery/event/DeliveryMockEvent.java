package com.fakecdek.delivery.event;

import com.winter.event.service.event.ChomikEvent;

public class DeliveryMockEvent extends ChomikEvent {
    protected final String shipmentId;

    public DeliveryMockEvent(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }
}
