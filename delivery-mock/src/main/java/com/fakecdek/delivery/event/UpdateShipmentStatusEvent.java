package com.fakecdek.delivery.event;


import com.fakecdek.delivery.mock.model.dto.DeliveryStatus;

public class UpdateShipmentStatusEvent extends DeliveryMockEvent {

    private final DeliveryStatus status;
    public UpdateShipmentStatusEvent(String shipmentId, DeliveryStatus status) {
        super(shipmentId);
        this.status = status;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UpdateShipmentStatusEvent{" +
                "status=" + status +
                ", shipmentId='" + shipmentId + '\'' +
                '}';
    }
}
