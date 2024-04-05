package com.payment.mock.client.dto

data class CreateTransactionDto(
    val paymentUrl: String,
    val transactionId: String
)
