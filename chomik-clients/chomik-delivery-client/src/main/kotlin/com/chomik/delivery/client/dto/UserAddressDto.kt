package com.chomik.delivery.client.dto

import jakarta.validation.constraints.NotNull


data class UserAddressDto (
    val id: String? = null,
    @field:NotNull(message = "userId must not be null")
    @field:NotNull(message = "userId must not be blank")
    val userId: String,
    @field:NotNull(message = "country must not be null")
    @field:NotNull(message = "country must not be blank")
    val country:  String,
    @field:NotNull(message = "city must not be null")
    @field:NotNull(message = "city must not be blank")
    val city: String,
    @field:NotNull(message = "street must not be null")
    @field:NotNull(message = "street must not be blank")
    val street: String,
    @field:NotNull(message = "house must not be null")
    @field:NotNull(message = "house must not be blank")
    val house: String,
    val floor: Int? = null,
    val flat: String? = null,
    val extraInfo: String? = null
)