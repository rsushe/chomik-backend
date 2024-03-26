package com.chomik.core.chomikgateway.service

import com.chomik.core.chomikgateway.domain.User
import com.chomik.core.chomikgateway.controller.dto.UserRegisterRequest
import com.chomik.core.chomikgateway.domain.UserType
import com.chomik.core.chomikgateway.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegistrationService(private val passwordEncoder: PasswordEncoder, private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(buildUser(userRegisterRequest, UserType.CLIENT))

    @Transactional
    fun registerSeller(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(buildUser(userRegisterRequest, UserType.SELLER))

    private fun buildUser(
        userRegisterRequest: UserRegisterRequest,
        userType: UserType,
    ) = User(
        name = userRegisterRequest.name,
        password = passwordEncoder.encode(userRegisterRequest.password),
        email = userRegisterRequest.email,
        phoneNumber = userRegisterRequest.phoneNumber,
        deleted = false,
        userType = userType,
    )
}
