package com.payment.mock.client.dto

data class ProcessedTransactionResponse(
    val transactionId: String,
    val status: TransactionStatus
)
