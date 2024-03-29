package com.chomik.core.gateway.domain

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
    @Column(name = "id")
    val id: String? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "email")
    val email: String?,
    @Column(name = "phone_number")
    val phoneNumber: String?,
    @Column(name = "deleted")
    val deleted: Boolean,
    @Column(name = "rating")
    val rating: Float? = null,
    @Column(name = "registration_date")
    val registrationDate: Instant = Instant.now(),
    @Column(name = "user_type")
    val userType: UserType,
)
