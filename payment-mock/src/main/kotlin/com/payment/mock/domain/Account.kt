package com.payment.mock.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.Date

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String? = null,
    val name: String,
    @Column(name = "card_number")
    val cardNumber: String,
    val cvv: Int,
    val expireAt: Date,
    val balance: Long,
)
