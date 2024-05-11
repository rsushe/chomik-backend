package com.chomik.storage.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "sales")
data class Sales(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String? = null,

    @Column(name = "advert_id")
    @get:NotNull
    val advertId: String,

    @Column(name = "old_price")
    @get:NotNull
    val oldPrice: Double,

    @Column(name = "new_price")
    @get:NotNull
    val newPrice: Double,

    @Column(name = "sale_percent")
    @get:NotNull
    val salePercent: Double
)

