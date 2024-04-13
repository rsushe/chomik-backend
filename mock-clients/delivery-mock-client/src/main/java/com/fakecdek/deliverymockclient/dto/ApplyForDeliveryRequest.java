package com.fakecdek.deliverymockclient.dto;

import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto;
import jakarta.validation.constraints.NotNull;

public record ApplyForDeliveryRequest(
        @NotNull DeliveryAddressDto userFromAddress,
        @NotNull String userFromPhone,
        @NotNull DeliveryAddressDto userToAddress,
        @NotNull String userToPhone
) {

}