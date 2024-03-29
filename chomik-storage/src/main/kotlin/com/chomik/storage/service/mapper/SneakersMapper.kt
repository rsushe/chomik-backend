package com.chomik.storage.service.mapper

import com.chomik.storage.client.dto.SaveSneakersRequest
import com.chomik.storage.domain.Sneakers

import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface SneakersMapper {
    fun toSneakers(updateRequest: SaveSneakersRequest): Sneakers
}