package com.chomik.core.chomikgateway.controller

import com.chomik.core.chomikgateway.domain.AuthorizationUserDetails
import com.chomik.core.chomikgateway.controller.dto.UserLoginRequest
import com.chomik.core.chomikgateway.controller.dto.UserRegisterRequest
import com.chomik.core.chomikgateway.service.JwtService
import com.chomik.core.chomikgateway.service.RegistrationService

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val registrationService: RegistrationService,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
) {

    @PostMapping("/v1/register")
    fun registerUser(@RequestBody userRegisterRequest: UserRegisterRequest) =
        registrationService.registerUser(userRegisterRequest)

    @PostMapping("/v1/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest): String {
        val authenticationToken = UsernamePasswordAuthenticationToken(userLoginRequest.name, userLoginRequest.password)
        val userDetails = authenticationManager.authenticate(authenticationToken).principal as AuthorizationUserDetails

        return jwtService.generateToken(userDetails)
    }
}
