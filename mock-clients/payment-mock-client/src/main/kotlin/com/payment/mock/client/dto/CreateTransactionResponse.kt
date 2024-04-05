package com.payment.mock.client.dto

data class CreateTransactionResponse(
    val paymentUrl: String,
    val transactionId: String
)
