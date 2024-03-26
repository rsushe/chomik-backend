package com.chomik.core.chomikgateway.service

import com.chomik.core.chomikgateway.domain.AuthorizationUserDetails
import com.chomik.core.chomikgateway.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AuthorizationUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(uuid: String): UserDetails =
        userRepository.findById(uuid)
            .map { AuthorizationUserDetails(it) }
            .orElseThrow { IllegalArgumentException("User with id $uuid doesn't exists") }
}
