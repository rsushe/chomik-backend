package com.chomik.orders.controller

import com.chomik.orders.domain.Order
import com.chomik.orders.service.OrderService
import com.chomik.orders.service.dto.CreateOrderRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createNewOrder(@Valid @RequestBody createOrderRequest: CreateOrderRequest): ResponseEntity<Order> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createNewOrder(createOrderRequest))
    }
}
