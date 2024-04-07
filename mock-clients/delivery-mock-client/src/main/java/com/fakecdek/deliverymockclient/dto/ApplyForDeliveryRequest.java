package com.fakecdek.deliverymockclient.dto;

import jakarta.validation.constraints.NotNull;

public record ApplyForDeliveryRequest(
        @NotNull AddressDto userFromAddress,
        @NotNull String userFromPhone,
        @NotNull AddressDto userToAddress,
        @NotNull String userToPhone
) {

}