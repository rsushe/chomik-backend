package com.fakecdek.delivery.mock.model.dto;

public class ShipmentDto {
    private String id;
    private String orderId;
    private String userAddressFrom;
    private String userAddressTo;
    private String trackLink;
    private DeliveryStatus status;

    public ShipmentDto() {}

    public ShipmentDto(String orderId, String userAddressFrom, String userAddressTo, DeliveryStatus status) {
        this.orderId = orderId;
        this.userAddressFrom = userAddressFrom;
        this.userAddressTo = userAddressTo;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserAddressFrom() {
        return userAddressFrom;
    }

    public void setUserAddressFrom(String userAddressFrom) {
        this.userAddressFrom = userAddressFrom;
    }

    public String getUserAddressTo() {
        return userAddressTo;
    }

    public void setUserAddressTo(String userAddressTo) {
        this.userAddressTo = userAddressTo;
    }

    public String getTrackLink() {
        return trackLink;
    }

    public void setTrackLink(String trackLink) {
        this.trackLink = trackLink;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
