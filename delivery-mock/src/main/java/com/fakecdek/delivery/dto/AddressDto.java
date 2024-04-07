package com.fakecdek.delivery.dto;

import jakarta.validation.constraints.NotNull;

public record AddressDto(
        @NotNull String country,
        @NotNull String city,
        @NotNull String street,
        @NotNull String house,
        int floor,
        String flat,
        String extraInfo
) {
}
