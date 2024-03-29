package com.chomik.orders.service.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateOrderRequest (

    @field:NotBlank(message = "Price must not be blank")
    val buyerId: String,

    @field:NotBlank(message = "Price must not be null")
    val advertId: String,

    @field:NotNull(message = "sneakerCount must not be null")
    @field:Max(value = 100, message = "sneakerCount must be less than 100")
    @field:Min(value = 1, message = "sneakerCount must be more than 0")
    val sneakerCount: Int,

)
