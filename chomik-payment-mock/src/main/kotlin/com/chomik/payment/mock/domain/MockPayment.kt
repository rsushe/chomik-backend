package com.chomik.payment.mock.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "mock_payment")
data class MockPayment(
    @Id
    val transactionId: String,
    val charge: Int,
    val status: PaymentStatus,
)
