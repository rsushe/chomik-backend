package com.chomik.delivery.client.dto

data class CreateShipmentRequest (
    val orderId: String,
    val userAddressFrom: String,
    val userAddressTo: String,
    val userFromPhone: String,
    val userToPhone: String
)