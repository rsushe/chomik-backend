package com.chomik.core.gateway.controller

import com.chomik.storage.client.dto.SaveAdvertRequest
import com.chomik.storage.client.service.AdvertClient
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/advert")
class AdvertController(private val advertClient: AdvertClient) {

    @GetMapping
    fun getAllAdverts(): ResponseEntity<String> = advertClient.getAllAdverts()

    @GetMapping("/{id}")
    fun getAdvertById(@PathVariable id: String): ResponseEntity<String> = advertClient.getAdvertById(id)

    @GetMapping("/seller/{sellerId}")
    fun getAdvertsBySellerId(@PathVariable sellerId: String): ResponseEntity<String> =
        advertClient.getAdvertsBySellerId(sellerId)

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_seller')")
    fun createAdvert(@Valid @RequestBody request: SaveAdvertRequest): ResponseEntity<String> =
        advertClient.createAdvert(request)

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_seller')")
    fun updateAdvert(
        @PathVariable id: String,
        @Valid @RequestBody updateRequest: SaveAdvertRequest
    ): ResponseEntity<String> = advertClient.updateAdvert(id, updateRequest)
}
