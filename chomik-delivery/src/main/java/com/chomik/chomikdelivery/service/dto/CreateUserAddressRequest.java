package com.chomik.chomikdelivery.service.dto;

import com.chomik.delivery.client.dto.AddressDto;

public record CreateUserAddressRequest (
        String userId,
        AddressDto address)
{}
