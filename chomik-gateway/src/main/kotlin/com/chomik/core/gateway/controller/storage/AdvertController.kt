package com.chomik.core.gateway.controller.storage

import com.chomik.core.gateway.domain.UserType.Companion.SELLER_AUTHORITY_NAME
import com.chomik.delivery.client.DeliveryClient
import com.chomik.storage.client.dto.SaveAdvertRequest
import com.chomik.storage.client.AdvertClient
import com.chomik.storage.client.dto.AdvertDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/advert")
class AdvertController(
    private val advertClient: AdvertClient,
    private val deliveryClient: DeliveryClient
) {

    @GetMapping
    fun getAllAdverts(): ResponseEntity<List<AdvertDto>> = advertClient.getAllAdverts()

    @GetMapping("/{id}")
    fun getAdvertById(@PathVariable id: String): ResponseEntity<AdvertDto> = advertClient.getAdvertById(id)

    @GetMapping("/seller/{sellerId}")
    fun getAdvertsBySellerId(@PathVariable sellerId: String): ResponseEntity<AdvertDto> =
        advertClient.getAdvertsBySellerId(sellerId)

    @PostMapping
    @PreAuthorize("hasAuthority($SELLER_AUTHORITY_NAME)")
    fun createAdvert(@Valid @RequestBody request: SaveAdvertRequest, authentication: Authentication): ResponseEntity<AdvertDto> {
        deliveryClient.getUserAddress(request.sellerAddressId, authentication.name)
        return advertClient.createAdvert(request)
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority($SELLER_AUTHORITY_NAME)")
    fun updateAdvert(
        @PathVariable id: String,
        @Valid @RequestBody updateRequest: SaveAdvertRequest
    ): ResponseEntity<AdvertDto> = advertClient.updateAdvert(id, updateRequest)
}
