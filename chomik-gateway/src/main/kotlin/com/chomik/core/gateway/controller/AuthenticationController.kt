package com.chomik.core.gateway.controller

import com.chomik.core.gateway.domain.AuthorizationUserDetails
import com.chomik.core.gateway.controller.dto.UserRegisterRequest
import com.chomik.core.gateway.service.JwtService
import com.chomik.core.gateway.service.RegistrationService

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/authentication")
class AuthenticationController(
    private val registrationService: RegistrationService,
    private val jwtService: JwtService,
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userRegisterRequest: UserRegisterRequest) =
        registrationService.registerUser(userRegisterRequest)

    @PostMapping("/login")
    fun login(authentication: Authentication): String {
        val userDetails = authentication.principal as AuthorizationUserDetails

        return jwtService.generateToken(userDetails)
    }
}
