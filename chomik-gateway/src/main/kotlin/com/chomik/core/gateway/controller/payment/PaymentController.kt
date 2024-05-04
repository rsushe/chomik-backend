package com.chomik.core.gateway.controller.payment

import com.chomik.core.gateway.domain.User
import com.chomik.core.gateway.service.AuthorizationUserDetailsService
import com.chomik.delivery.client.DeliveryClient
import com.chomik.delivery.client.dto.CreateShipmentRequest
import com.chomik.orders.client.OrderClient
import com.chomik.orders.client.dto.OrderDto
import com.chomik.payment.client.PaymentClient
import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.storage.client.AdvertClient
import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.UpdateSneakersCountRequest
import com.payment.mock.model.ProcessedTransactionResponse
import com.payment.mock.model.TransactionStatus

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
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
    private val bankCallbackHandler: BankCallbackHandler
) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest, authentication: Authentication): ResponseEntity<CreatePaymentResponse> {
        deliveryClient.getUserAddress(createPaymentRequest.addressId, authentication.name)
        orderClient.updateOrderUserAddressTo(createPaymentRequest.orderId, createPaymentRequest.addressId)
        orderClient.updateOrderPaymentCreate(createPaymentRequest.orderId)
        return paymentClient.createPayment(createPaymentRequest)
    }

    @PostMapping("/callback")
    fun processBankCallback(@RequestBody processedTransactionResponse: ProcessedTransactionResponse) {
        bankCallbackHandler.handle(processedTransactionResponse)
    }
}
