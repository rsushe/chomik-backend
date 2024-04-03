package com.chomik.storage.extension

import com.chomik.storage.client.dto.SneakersDto
import com.chomik.storage.domain.Sneakers

fun Sneakers.toDto(): SneakersDto = SneakersDto(
    id = this.id!!,
    model = this.model,
    brand = this.brand,
    size = this.size,
    color = this.color,
    condition = this.condition
)
