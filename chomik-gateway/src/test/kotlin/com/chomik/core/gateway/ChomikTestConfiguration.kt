package com.chomik.core.gateway

import com.chomik.core.gateway.config.UserAuthorities
import com.chomik.core.gateway.domain.AuthorizationUserDetails
import com.chomik.core.gateway.domain.User
import com.chomik.core.gateway.domain.UserType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import java.time.Instant
import java.time.temporal.ChronoUnit

@Configuration
class ChomikTestConfiguration(
    private val userAuthorities: UserAuthorities
) {

    @Bean
    @Primary
    fun userDetailsService(): UserDetailsService {
        val user = User(
            name = "user",
            email = "user@company.com",
            password = "password",
            userType = UserType.CLIENT
        )
        val userDetails = AuthorizationUserDetails(user, userAuthorities)

        val seller = User(
            name = "seller",
            email = "seller@company.com",
            password = "password",
            userType = UserType.SELLER
        )
        val sellerDetails = AuthorizationUserDetails(seller, userAuthorities)

        return InMemoryUserDetailsManager(listOf(userDetails, sellerDetails))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = object : PasswordEncoder {
        override fun encode(rawPassword: CharSequence?): String = rawPassword as String

        override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean =
            rawPassword == encodedPassword
    }

    @Bean
    fun jwtEncoder(): JwtEncoder =
        JwtEncoder { Jwt("token", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), emptyMap(), emptyMap()) }
}
