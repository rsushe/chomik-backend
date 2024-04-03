package com.chomik.storage.client.dto

data class AdvertDto(
    val id: String,
    val sneakerId: String,
    val sellerId: String,
    val status: AdvertStatus,
    val price: Double,
    val active: Boolean,
    val sneakerCount: Int,
    val description: String? = null
)
