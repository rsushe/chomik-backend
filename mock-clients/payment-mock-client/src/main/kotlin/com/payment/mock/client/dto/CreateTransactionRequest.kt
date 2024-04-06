package com.payment.mock.client.dto

data class CreateTransactionRequest(
    val charge: Int,
    val callbackUrl: String
)
