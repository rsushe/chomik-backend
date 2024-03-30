package com.chomik.orders.domain

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.Instant

@Entity
@Table(name = "advert_lock")
data class AdvertLock(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    var id: String? = null,

    var orderId: String,

    val advertId: String,

    var lockTime: Instant = Instant.now(),

    @Column(name = "is_active")
    var active: Boolean,

    var lockedCount: Int
)
