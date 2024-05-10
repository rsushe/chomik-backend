package com.payment.mock.client.dto

data class CreateTransactionRequest(
    val charge: Long,
    val accountTo: String,
    val callbackUrl: String,
    val token: String
)
