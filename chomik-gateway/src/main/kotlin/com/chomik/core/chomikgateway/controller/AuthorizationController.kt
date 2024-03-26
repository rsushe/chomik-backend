package com.chomik.core.chomikgateway.controller

import com.chomik.core.chomikgateway.domain.AuthorizationUserDetails
import com.chomik.core.chomikgateway.domain.UserLoginRequest
import com.chomik.core.chomikgateway.domain.UserRegisterRequest
import com.chomik.core.chomikgateway.service.AuthorizationUserDetailsService
import com.chomik.core.chomikgateway.service.JwtService
import com.chomik.core.chomikgateway.service.RegistrationService

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationController(
    private val registrationService: RegistrationService,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val authorizationUserDetailsService: AuthorizationUserDetailsService
) {

    @PostMapping("/v1/register/user")
    fun registerUser(@RequestBody userRegisterRequest: UserRegisterRequest) =
        registrationService.registerUser(userRegisterRequest).id

    @PostMapping("/v1/register/seller")
    fun registerSeller(@RequestBody userRegisterRequest: UserRegisterRequest) =
        registrationService.registerSeller(userRegisterRequest).id

    @PostMapping("/v1/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest): String {
        val authenticationToken = UsernamePasswordAuthenticationToken(userLoginRequest.name, userLoginRequest.password)
        authenticationManager.authenticate(authenticationToken)

        val user = authorizationUserDetailsService.loadUserByUsername(userLoginRequest.name) as AuthorizationUserDetails
        return jwtService.generateToken(user)
    }
}
