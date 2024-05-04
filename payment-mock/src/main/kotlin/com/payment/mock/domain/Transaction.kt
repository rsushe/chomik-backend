package com.payment.mock.domain

import com.payment.mock.model.TransactionStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    val id: String? = null,
    val charge: Int,
    val callbackUrl: String,
    val token: String,
    @Enumerated(EnumType.STRING)
    val status: TransactionStatus,
)
