package com.chomik.payment.domain

import com.chomik.payment.client.dto.PaymentStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String? = null,
    val transactionId: String,
    val orderId: String,
    @Enumerated(EnumType.STRING)
    val status: PaymentStatus
)
