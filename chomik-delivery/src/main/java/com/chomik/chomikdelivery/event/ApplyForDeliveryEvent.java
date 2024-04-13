package com.chomik.chomikdelivery.event;


import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto;

public class ApplyForDeliveryEvent extends DeliveryEvent {
    private final DeliveryAddressDto addressFrom;
    private final DeliveryAddressDto addressTo;
    private final String userFromMobile;
    private final String userToMobile;


    public ApplyForDeliveryEvent(String shipmentId, DeliveryAddressDto addressFrom, DeliveryAddressDto addressTo, String userFromMobile, String userToMobile) {
        super(shipmentId);
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.userFromMobile = userFromMobile;
        this.userToMobile = userToMobile;
    }

    public DeliveryAddressDto getAddressFrom() {
        return addressFrom;
    }

    public DeliveryAddressDto getAddressTo() {
        return addressTo;
    }

    public String getUserFromMobile() {
        return userFromMobile;
    }

    public String getUserToMobile() {
        return userToMobile;
    }
}
