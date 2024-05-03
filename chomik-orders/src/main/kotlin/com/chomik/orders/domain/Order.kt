package com.chomik.orders.domain

import com.chomik.orders.client.dto.OrderStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.time.Instant

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

    val creationTime: Instant = Instant.now(),

    @Column(name = "user_address_to", nullable = true)
    val userAddressTo: String? = null,
)
