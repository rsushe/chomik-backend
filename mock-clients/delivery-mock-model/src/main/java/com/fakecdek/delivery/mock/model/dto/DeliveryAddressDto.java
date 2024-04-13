package com.fakecdek.delivery.mock.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DeliveryAddressDto (
        @NotBlank(message = "country must not be null or blank")
        String country,
        @NotBlank(message = "city must not be null or blank")
        String city,
        @NotBlank(message = "street must not be null or blank")
        String street,
        @NotBlank(message = "house must not be null or blank")
        String house,
        Integer floor,
        String flat,
        String extraInfo
)
{}