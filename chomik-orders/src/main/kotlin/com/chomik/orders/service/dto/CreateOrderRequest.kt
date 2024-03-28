package com.chomik.orders.service.dto


import jakarta.validation.constraints.NotBlank

data class CreateOrderRequest (

    @field:NotBlank(message = "Price must not be blank")
    val buyerId: String,

    @field:NotBlank(message = "Price must not be null")
    val advertId: String,

)