package com.chomik.orders.client

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class OrderClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun findById(orderId: String): ResponseEntity<OrderDto> {
        val uri = createBuilder("api/v1/order/${orderId}")

        return restTemplate.getForEntity(uri.toUriString(), OrderDto::class.java)
    }

    fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderDto> {
        val uri = createBuilder("api/v1/order")

        return restTemplate.postForEntity(uri.toUriString(), createOrderRequest, OrderDto::class.java)
    }

    fun updateOrderPaymentCreate(orderId: String): ResponseEntity<OrderDto> {
        val uri = createBuilder("api/v1/order/$orderId/payment/create")

        return restTemplate.exchange(uri.toUriString(), HttpMethod.PUT, null, OrderDto::class.java)
    }

    fun updateOrderPaymentFinish(orderId: String): ResponseEntity<OrderDto> {
        val uri = createBuilder("api/v1/order/$orderId/payment/finish")

        return restTemplate.exchange(uri.toUriString(), HttpMethod.PUT, null, OrderDto::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
