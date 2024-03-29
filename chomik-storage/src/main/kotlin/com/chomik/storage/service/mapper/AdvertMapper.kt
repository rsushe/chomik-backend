package com.chomik.storage.service.mapper

import com.chomik.storage.domain.Advert
import com.chomik.storage.service.dto.SaveAdvertRequest
import org.mapstruct.Mapper
import org.springframework.stereotype.Component


@Mapper(componentModel = "spring")
@Component
interface AdvertMapper {
    fun toAdvert(request: SaveAdvertRequest): Advert
}