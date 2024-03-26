package com.chomik.core.chomikgateway.service

import com.chomik.core.chomikgateway.domain.User
import com.chomik.core.chomikgateway.domain.UserRegisterRequest
import com.chomik.core.chomikgateway.domain.UserType
import com.chomik.core.chomikgateway.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class RegistrationService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(buildUser(userRegisterRequest, UserType.CLIENT))

    @Transactional
    fun registerSeller(userRegisterRequest: UserRegisterRequest) =
        userRepository.save(buildUser(userRegisterRequest, UserType.SELLER, 5f, LocalDateTime.now()))

    private fun buildUser(
        userRegisterRequest: UserRegisterRequest,
        userType: UserType,
        rating: Float? = null,
        sellsFrom: LocalDateTime? = null
    ) = User(
        name = userRegisterRequest.name,
        password = userRegisterRequest.password,
        email = userRegisterRequest.email,
        phoneNumber = userRegisterRequest.phoneNumber,
        deleted = false,
        rating = rating,
        sellsFrom = sellsFrom,
        userType = userType,
    )
}
