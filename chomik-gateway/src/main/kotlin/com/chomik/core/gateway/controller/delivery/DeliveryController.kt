package com.chomik.core.gateway.controller.delivery

import com.chomik.delivery.client.DeliveryClient
import com.chomik.delivery.client.dto.CreateUserAddressRequest
import com.chomik.delivery.client.dto.UserAddressDto
import org.springframework.http.ResponseEntity
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
    @GetMapping("/address/{userAddressId}")
    fun getUserAddress(@PathVariable userAddressId: String): ResponseEntity<UserAddressDto> =
        deliveryClient.getUserAddress(userAddressId)

    @GetMapping("/address/user/{userId}")
    fun getUserAddressed(@PathVariable userId: String): ResponseEntity<List<UserAddressDto>> =
        deliveryClient.getUserAddresses(userId)

    @PostMapping("/address")
    fun createUserAddress(@RequestBody createUserAddressRequest: CreateUserAddressRequest): ResponseEntity<UserAddressDto> =
        deliveryClient.createUserAddress(createUserAddressRequest)

    @DeleteMapping("/address/{addressId}")
    fun deleteUserAddress(@PathVariable addressId: String) = deliveryClient.deleteUserAddress(addressId)
}
