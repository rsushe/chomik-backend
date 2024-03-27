package com.chomik.core.chomikgateway.service

import com.chomik.core.chomikgateway.controller.dto.UserRegisterRequest
import com.chomik.core.chomikgateway.extension.toUser
import com.chomik.core.chomikgateway.repository.UserRepository

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegistrationService(private val passwordEncoder: PasswordEncoder, private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(userRegisterRequest.toUser(passwordEncoder))
}
