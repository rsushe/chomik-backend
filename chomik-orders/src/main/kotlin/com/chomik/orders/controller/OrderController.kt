package com.chomik.orders.controller

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import com.chomik.orders.extension.toDto
import com.chomik.orders.facade.OrderFacade
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/order")
class OrderController(private val orderFacade: OrderFacade) {

    @GetMapping("/{orderId}")
    fun findById(@PathVariable orderId: String): ResponseEntity<OrderDto> =
        ResponseEntity.ok(orderFacade.findById(orderId).toDto())

    @PostMapping
    fun createNewOrder(@Valid @RequestBody createOrderRequest: CreateOrderRequest): ResponseEntity<OrderDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderFacade.createNewOrder(createOrderRequest).toDto())
    }

    @PutMapping("{orderId}/payment/create")
    fun updateOrderPaymentCreate(@PathVariable orderId: String): ResponseEntity<OrderDto> {
        return ResponseEntity.ok(orderFacade.updateOrderPaymentCreate(orderId).toDto())
    }

    @PutMapping("{orderId}/payment/finish")
    fun updateOrderPaymentFinish(@PathVariable orderId: String): ResponseEntity<OrderDto> {
        return ResponseEntity.ok(orderFacade.updateOrderPaymentFinish(orderId).toDto())
    }
}
