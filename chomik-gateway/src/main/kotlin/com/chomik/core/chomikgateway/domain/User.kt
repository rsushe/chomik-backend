package com.chomik.core.chomikgateway.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.time.Instant

@Table(name = "\"user\"")
@Entity
data class User(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    val id: String? = null,
    @Column(name = "name", nullable = false, unique = true)
    val name: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "email", nullable = true)
    val email: String?,
    @Column(name = "phone_number", nullable = true)
    val phoneNumber: String?,
    @Column(name = "deleted", nullable = false)
    val deleted: Boolean,
    @Column(name = "rating")
    val rating: Float? = null,
    @Column(name = "registration_date")
    val registrationDate: Instant = Instant.now(),
    @Column(name = "user_type", nullable = false)
    val userType: UserType,
)
