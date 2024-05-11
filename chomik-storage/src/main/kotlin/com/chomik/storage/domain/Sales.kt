package com.chomik.storage.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull


@Entity
@Table(name = "sales")
data class Sales(
    @Id
    @Column(name = "id")
    val id: String,

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

