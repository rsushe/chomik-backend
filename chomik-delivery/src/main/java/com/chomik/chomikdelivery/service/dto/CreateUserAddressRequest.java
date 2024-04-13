package com.chomik.chomikdelivery.service.dto;

import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserAddressRequest (
        @NotBlank(message = "userId must not be null or blank")
        String userId,
        @NotNull(message = "address must not be null or blank")
        @Valid
        DeliveryAddressDto address)
{}
