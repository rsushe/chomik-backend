package com.chomik.payment.client.dto

data class PaymentDto (
    val id: String? = null,
    val transactionId: String,
    val orderId: String,
    val status: PaymentStatus
){}