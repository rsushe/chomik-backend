package com.chomik.core.gateway.service

import com.chomik.core.gateway.domain.AuthorizationUserDetails
import com.chomik.core.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AuthorizationUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(name: String): UserDetails =
        userRepository.findByName(name)
            ?.let { AuthorizationUserDetails(it) }
            ?: throw IllegalArgumentException("User with id $name doesn't exists")

    fun findById(userId: String) = userRepository.findById(userId)
        .orElseThrow{IllegalArgumentException("No user found with id $userId")}
}
