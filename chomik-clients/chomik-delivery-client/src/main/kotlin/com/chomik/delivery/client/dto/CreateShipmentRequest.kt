package com.chomik.delivery.client.dto

import jakarta.validation.constraints.NotBlank

data class CreateShipmentRequest (
    @field:NotBlank(message = "orderId must not be null or blank")
    val orderId: String,
    @field:NotBlank(message = "userAddressFrom must not be null or blank")
    val userAddressFrom: String,
    @field:NotBlank(message = "userAddressTo must not be null or blank")
    val userAddressTo: String,
    @field:NotBlank(message = "userFromPhone must not be null or blank")
    val userFromPhone: String,
    @field:NotBlank(message = "userToPhone must not be null or blank")
    val userToPhone: String
)
