package com.chomik.chomikdelivery.event;

import com.chomik.delivery.client.dto.AddressDto;

public class ApplyForDeliveryEvent extends DeliveryEvent {
    private final AddressDto addressFrom;
    private final AddressDto addressTo;
    private final String userFromMobile;
    private final String userToMobile;


    public ApplyForDeliveryEvent(String shipmentId, AddressDto addressFrom, AddressDto addressTo, String userFromMobile, String userToMobile) {
        super(shipmentId);
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.userFromMobile = userFromMobile;
        this.userToMobile = userToMobile;
    }

    public AddressDto getAddressFrom() {
        return addressFrom;
    }

    public AddressDto getAddressTo() {
        return addressTo;
    }

    public String getUserFromMobile() {
        return userFromMobile;
    }

    public String getUserToMobile() {
        return userToMobile;
    }
}
