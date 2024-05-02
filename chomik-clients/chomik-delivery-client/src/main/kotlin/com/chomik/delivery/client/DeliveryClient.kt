package com.chomik.delivery.client

import com.chomik.delivery.client.dto.CreateUserAddressRequest
import com.chomik.delivery.client.dto.UserAddressDto
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class DeliveryClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getUserAddress(addressId: String, userId: String): ResponseEntity<UserAddressDto> {
        val uri = createBuilder("api/v1/delivery/address/$addressId/user/$userId")

        return restTemplate.getForEntity(uri.toUriString(), UserAddressDto::class.java)
    }

    fun getUserAddresses(userId: String): ResponseEntity<List<UserAddressDto>> {
        val uri = createBuilder("api/v1/delivery/address/user/$userId")

        return restTemplate.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<UserAddressDto>>() {})
    }

    fun createUserAddress(createUserAddressRequest: CreateUserAddressRequest): ResponseEntity<UserAddressDto> {
        val uri = createBuilder("api/v1/delivery/address")

        return restTemplate.postForEntity(uri.toUriString(), createUserAddressRequest, UserAddressDto::class.java)
    }

    fun deleteUserAddress(addressId: String, userId: String) {
        val uri = createBuilder("api/v1/delivery/address/$addressId/user/$userId")

        restTemplate.delete(uri.toUriString())
    }

    fun updateShipmentStatus(updateShipmentStatusRequest: UpdateShipmentStatusRequest): ResponseEntity<Any> {
        val uri = createBuilder("api/v1/delivery/shipment/status")

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(updateShipmentStatusRequest, headers)

        return restTemplate.exchange(uri.toUriString(), HttpMethod.PUT, entity, Any::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
