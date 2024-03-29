package com.chomik.orders.domain

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "order_table")
data class Order(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    var id: String? = null,

    var buyerId: String,

    var advertId: String,

    var status: OrderStatus,

    @Column(name = "sneaker_count", nullable = false)
    val sneakerCount: Int,
)