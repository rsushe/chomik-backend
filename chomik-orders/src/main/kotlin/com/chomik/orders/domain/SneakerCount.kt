package com.chomik.orders.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sneaker_count")
data class SneakerCount(
    @Id
    val sneakerId: String,
    val count: Int
)
