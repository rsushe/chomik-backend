package com.chomik.storage.domain

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "sneakers")
data class Sneakers(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String,

    @Column(name = "model", nullable = false)
    val model: String,

    @Column(name = "brand", nullable = false)
    @Enumerated(EnumType.STRING)
    val brand: Brand,

    @Column(name = "size", nullable = false)
    val size: Double,

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    val color: Color?,

    @Column(name = "condition", nullable = false)
    val condition: String
)