package com.chomik.orders.client.dto

data class CreatePaymentRequest(
    val orderId: String,
    val charge: Int
)
