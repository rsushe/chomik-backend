package com.chomik.chomikdelivery.service.mapper;


import com.chomik.chomikdelivery.domain.UserAddress;
import com.chomik.delivery.client.dto.UserAddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserAddressMapper {
    UserAddressDto convertTo(UserAddress entity);
    UserAddress convertTo(UserAddressDto dto);
}
