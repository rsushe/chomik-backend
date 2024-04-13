package com.chomik.chomikdelivery.service.mapper;

import com.chomik.chomikdelivery.domain.Address;
import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto;
import org.mapstruct.Mapper;


@Mapper
public interface AddressMapper {
    DeliveryAddressDto convertTo(Address entity);
    Address convertTo(DeliveryAddressDto dto);
}
