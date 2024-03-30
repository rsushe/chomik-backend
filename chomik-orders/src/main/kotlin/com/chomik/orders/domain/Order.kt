package com.chomik.orders.domain

import com.chomik.orders.client.dto.OrderStatus
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "\"order\"")
data class Order(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    var id: String? = null,

    var buyerId: String,

    var advertId: String,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus,

    @Column(name = "sneaker_count", nullable = false)
    val sneakerCount: Int,
)