package com.chomik.storage.service.dto

import com.chomik.storage.domain.AdvertStatus

data class SaveAdvertRequest(
    val sneakerId: String?,
    val sellerId: String?,
    val status: AdvertStatus?,
    val price: Double?,
    val active: Boolean?
)