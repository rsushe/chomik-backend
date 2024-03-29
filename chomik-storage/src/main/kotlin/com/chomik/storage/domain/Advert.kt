package com.chomik.storage.domain

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "advert")
data class Advert(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String? = null,

    @Column(name = "sneaker_id", nullable = false)
    val sneakerId: String,

    @Column(name = "seller_id", nullable = false)
    val sellerId: String,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    val status: AdvertStatus,

    @Column(name = "price", nullable = false)
    val price: Double,

    @Column(name = "active", nullable = false)
    val active: Boolean,

    @Column(name = "sneaker_count", nullable = false)
    val sneakerCount: Int,

    @Column(name = "description", nullable = true)
    val description: String? = null
)