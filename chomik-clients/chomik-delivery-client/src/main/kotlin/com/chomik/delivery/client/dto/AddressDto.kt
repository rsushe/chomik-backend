package com.chomik.delivery.client.dto

import jakarta.validation.constraints.NotBlank

data class AddressDto (
    val id: String? = null,
    @field:NotBlank(message = "country must not be null or blank")
    val country:  String,
    @field:NotBlank(message = "city must not be null or blank")
    val city: String,
    @field:NotBlank(message = "street must not be null or blank")
    val street: String,
    @field:NotBlank(message = "house must not be null or blank")
    val house: String,
    val floor: Int? = null,
    val flat: String? = null,
    val extraInfo: String? = null
)