package com.chomik.delivery.client.dto

data class CreateShipmentRequest (
    val orderId: String,
    val addressFrom: String,
    val addressTo: String,
    val userFromPhone: String,
    val userToPhone: String
)