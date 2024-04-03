package com.chomik.core.gateway

import com.chomik.core.gateway.domain.AuthorizationUserDetails
import com.chomik.core.gateway.domain.User
import com.chomik.core.gateway.domain.UserType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class ChomikTestConfiguration {

    @Bean
    @Primary
    fun userDetailsService(): UserDetailsService {
        val user = User(
            name = "user",
            email = "user@company.com",
            password = "password",
            userType = UserType.CLIENT
        )
        val userDetails = AuthorizationUserDetails(user)

        val seller = User(
            name = "seller",
            email = "seller@company.com",
            password = "password",
            userType = UserType.SELLER)
        val sellerDetails = AuthorizationUserDetails(seller)

        return InMemoryUserDetailsManager(listOf(userDetails, sellerDetails))
    }
}
