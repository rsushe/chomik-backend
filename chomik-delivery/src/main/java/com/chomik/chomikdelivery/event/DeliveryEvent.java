package com.chomik.chomikdelivery.event;

import com.chomik.eventservice.event.ChomikEvent;

public abstract class DeliveryEvent extends ChomikEvent {
    protected final String shipmentId;

    public DeliveryEvent(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }
}
