package com.chomik.payment.client.dto

data class CreatePaymentRequest(
    val orderId: String,
    val addressId: String,
    val charge: Long,
    var bankToken: String? = null,
)
