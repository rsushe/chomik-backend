package com.chomik.storage.extension

import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.domain.Advert

fun Advert.toDto(): AdvertDto = AdvertDto(
    id = this.id!!,
    sneakerId = this.sneakerId,
    sellerId = this.sellerId,
    status = this.status,
    price = this.price,
    active = this.active,
    sneakerCount = this.sneakerCount,
    sellerAddressId = this.sellerAddressId,
    description = this.description
)
