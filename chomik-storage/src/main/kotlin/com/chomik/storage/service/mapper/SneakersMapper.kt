package com.chomik.storage.service.mapper

import com.chomik.storage.domain.Sneakers
import com.chomik.storage.service.dto.SaveSneakersRequest

import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface SneakersMapper {
    fun toSneakers(updateRequest: SaveSneakersRequest): Sneakers
}