package com.chomik.core.gateway.service

import com.chomik.core.gateway.controller.dto.UserRegisterRequest
import com.chomik.core.gateway.extension.toUser
import com.chomik.core.gateway.repository.UserRepository

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegistrationService(private val passwordEncoder: PasswordEncoder, private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(userRegisterRequest.toUser(passwordEncoder))
}
