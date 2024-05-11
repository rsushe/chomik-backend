package com.chomik.core.gateway.service

import com.chomik.core.gateway.config.UserAuthorities
import com.chomik.core.gateway.domain.AuthorizationUserDetails
import com.chomik.core.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AuthorizationUserDetailsService(
    private val userRepository: UserRepository,
    private val userAuthorities: UserAuthorities,
) : UserDetailsService {
    override fun loadUserByUsername(name: String): UserDetails =
        userRepository.findByName(name)
            ?.let { AuthorizationUserDetails(it, userAuthorities) }
            ?: throw IllegalArgumentException("User with id $name doesn't exists")

    fun findById(userId: String): AuthorizationUserDetails = userRepository.findById(userId)
        .map { AuthorizationUserDetails(it, userAuthorities) }
        .orElseThrow { IllegalArgumentException("No user found with id $userId") }
}
