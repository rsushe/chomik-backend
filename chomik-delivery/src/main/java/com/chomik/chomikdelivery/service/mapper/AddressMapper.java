package com.chomik.chomikdelivery.service.mapper;

import com.chomik.chomikdelivery.domain.Address;
import com.chomik.delivery.client.dto.AddressDto;
import org.mapstruct.Mapper;


@Mapper
public interface AddressMapper {
    AddressDto convertTo(Address entity);
    Address convertTo(AddressDto dto);
}
