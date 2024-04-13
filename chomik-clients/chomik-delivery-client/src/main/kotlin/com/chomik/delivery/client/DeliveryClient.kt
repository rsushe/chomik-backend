package com.chomik.delivery.client

import com.chomik.delivery.client.dto.UserAddressDto
import com.fakecdek.delivery.mock.model.dto.UpdateShipmentStatusRequest
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class DeliveryClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getUserAddress(userAddressId: String): ResponseEntity<UserAddressDto> {
        val uri = createBuilder("api/v1/delivery/address/${userAddressId}")

        return restTemplate.getForEntity(uri.toUriString(), UserAddressDto::class.java)
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