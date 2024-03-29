package com.chomik.core.gateway.controller

import com.chomik.storage.client.service.AdvertService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/advert")
@PreAuthorize("hasAuthority('SCOPE_seller')")
class AdvertController(private val advertService: AdvertService) {

    @GetMapping
    fun getAllAdverts(): ResponseEntity<*> = advertService.getAllAdverts()
}
