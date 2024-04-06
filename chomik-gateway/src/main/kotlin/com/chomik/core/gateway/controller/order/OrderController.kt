package com.chomik.core.gateway.controller.order

import com.chomik.orders.client.OrderClient
import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/order")
class OrderController(private val orderClient: OrderClient) {

    @GetMapping("/{orderId}")
    fun getOrderById(@PathVariable orderId: String): ResponseEntity<OrderDto> = orderClient.findById(orderId)

    @PostMapping
    fun createNewOrder(
        authentication: Authentication,
        @Valid @RequestBody createOrderRequest: CreateOrderRequest
    ): ResponseEntity<OrderDto> {
        if (authentication.name != createOrderRequest.buyerId) {
            throw IllegalArgumentException("userId in request doesn't equal userId in token")
        }
        return orderClient.createOrder(createOrderRequest)
    }
}
