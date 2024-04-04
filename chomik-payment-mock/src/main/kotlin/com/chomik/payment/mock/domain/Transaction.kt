package com.chomik.payment.mock.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String,
    val charge: Int,
    val status: PaymentStatus,
)
