package com.chomik.core.gateway.controller.payment

import com.chomik.delivery.client.DeliveryClient
import com.chomik.orders.client.OrderClient
import com.chomik.payment.client.PaymentClient
import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/payment")
class PaymentController(
    private val deliveryClient: DeliveryClient,
    private val orderClient: OrderClient,
    private val paymentClient: PaymentClient,
) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest): ResponseEntity<CreatePaymentResponse> {
        deliveryClient.getUserAddress(createPaymentRequest.addressId)
        orderClient.updateOrderToPayment(createPaymentRequest.orderId)
        return paymentClient.createPayment(createPaymentRequest)
    }
}
