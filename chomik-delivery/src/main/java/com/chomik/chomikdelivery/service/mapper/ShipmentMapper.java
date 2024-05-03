package com.chomik.chomikdelivery.service.mapper;


import com.chomik.chomikdelivery.domain.Shipment;
import com.fakecdek.delivery.mock.model.dto.ShipmentDto;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentMapper {
    Shipment toEntity(ShipmentDto dto);
    ShipmentDto toDto(Shipment entity);
}
