package com.chomik.core.gateway.controller.delivery

import com.chomik.delivery.client.DeliveryClient
import com.chomik.delivery.client.dto.CreateUserAddressRequest
import com.chomik.delivery.client.dto.UserAddressDto

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/delivery")
class DeliveryController(private val deliveryClient: DeliveryClient) {
    @GetMapping("/address/{addressId}/user/{userId}")
    fun getUserAddress(
        @PathVariable addressId: String,
        @PathVariable userId: String,
        authentication: Authentication
    ): ResponseEntity<UserAddressDto> {
        if (authentication.name != userId) {
            throw IllegalArgumentException("userId in request doesn't equal userId in token")
        }
        return deliveryClient.getUserAddress(addressId, userId)
    }

    @GetMapping("/address/user/{userId}")
    fun getUserAddresses(
        @PathVariable userId: String,
        authentication: Authentication
    ): ResponseEntity<List<UserAddressDto>> {
        if (authentication.name != userId) {
            throw IllegalArgumentException("userId in request doesn't equal userId in token")
        }
        return deliveryClient.getUserAddresses(userId)
    }

    @PostMapping("/address")
    fun createUserAddress(
        @RequestBody createUserAddressRequest: CreateUserAddressRequest,
        authentication: Authentication
    ): ResponseEntity<UserAddressDto> {
        if (authentication.name != createUserAddressRequest.userId) {
            throw IllegalArgumentException("userId in request doesn't equal userId in token")
        }
        return deliveryClient.createUserAddress(createUserAddressRequest)
    }

    @DeleteMapping("/address/{addressId}/user/{userId}")
    fun deleteUserAddress(
        @PathVariable addressId: String,
        @PathVariable userId: String,
        authentication: Authentication
    ) {
        if (authentication.name != userId) {
            throw IllegalArgumentException("userId in request doesn't equal userId in token")
        }
        return deliveryClient.deleteUserAddress(addressId, userId)
    }
}
