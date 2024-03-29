package com.chomik.core.gateway.extension

import com.chomik.core.gateway.controller.dto.UserRegisterRequest
import com.chomik.core.gateway.domain.User
import org.springframework.security.crypto.password.PasswordEncoder

fun UserRegisterRequest.toUser(passwordEncoder: PasswordEncoder) = User(
    name = this.name,
    password = passwordEncoder.encode(this.password),
    email = this.email,
    phoneNumber = this.phoneNumber,
    deleted = false,
    userType = this.userType,
)
