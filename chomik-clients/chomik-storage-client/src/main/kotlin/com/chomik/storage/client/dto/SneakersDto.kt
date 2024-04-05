package com.chomik.storage.client.dto

data class SneakersDto(
    val id: String,
    val model: String,
    val brand: Brand,
    val size: Double,
    val color: Color?,
    val condition: Condition
)
