package com.chomik.orders.client

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class OrderClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderDto> {
        val uri = createBuilder("api/v1/order")

        return restTemplate.postForEntity(uri.toUriString(), createOrderRequest, OrderDto::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
