package com.chomik.payment.controller

import com.chomik.orders.client.dto.CreatePaymentRequest
import com.chomik.payment.service.PaymentService
import com.payment.mock.client.dto.CreateTransactionResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/payment")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest): CreateTransactionResponse =
        paymentService.createPayment(createPaymentRequest)
}
