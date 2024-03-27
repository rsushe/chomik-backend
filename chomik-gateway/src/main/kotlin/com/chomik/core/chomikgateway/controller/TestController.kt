package com.chomik.core.chomikgateway.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/all")
    fun endpointAllowedToAll(authentication: Authentication): String = authentication.name

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('SCOPE_seller')")
    fun endpointToSeller(authentication: Authentication): String {
        return authentication.name
    }
}
