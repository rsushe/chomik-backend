package com.fakecdek.deliverymockclient.dto;

import jakarta.validation.constraints.NotNull;

public record ApplyForDeliveryRequest(
        @NotNull DeliveryAddressDto userFromAddress,
        @NotNull String userFromPhone,
        @NotNull DeliveryAddressDto userToAddress,
        @NotNull String userToPhone
) {

}