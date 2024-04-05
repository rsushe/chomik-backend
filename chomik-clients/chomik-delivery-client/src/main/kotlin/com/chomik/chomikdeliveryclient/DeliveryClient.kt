package com.chomik.chomikdeliveryclient

import com.chomik.chomikdeliveryclient.dto.UserAddressDto
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class DeliveryClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getUserAddress(userAddressId: String): ResponseEntity<UserAddressDto> {
        val uri = createBuilder("api/v1/delivery/address")

        return restTemplate.getForEntity(uri.toUriString(), UserAddressDto::class.java, userAddressId)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}