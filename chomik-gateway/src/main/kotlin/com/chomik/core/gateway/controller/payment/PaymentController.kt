package com.chomik.core.gateway.controller.payment

import com.chomik.core.gateway.domain.UserAuthority.Companion.PROCESS_BANK_CALLBACK_AUTHORITY_NAME
import com.chomik.core.gateway.service.AuthorizationUserDetailsService
import com.chomik.core.gateway.service.JwtService
import com.chomik.core.gateway.service.payment.BankCallbackHandler
import com.chomik.delivery.client.DeliveryClient
import com.chomik.orders.client.OrderClient
import com.chomik.payment.client.PaymentClient
import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.payment.mock.model.ProcessTransactionResponse
import org.springframework.beans.factory.annotation.Value

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    private val bankCallbackHandler: BankCallbackHandler,
    private val userDetailsService: AuthorizationUserDetailsService,
    private val jwtService: JwtService,
    @Value("\${bank.user.id}")
    private val bankUserId: String
) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest, authentication: Authentication): ResponseEntity<CreatePaymentResponse> {
        createPaymentRequest.bankToken = jwtService.generateToken(userDetailsService.findById(bankUserId))

        deliveryClient.getUserAddress(createPaymentRequest.addressId, authentication.name)
        orderClient.updateOrderUserAddressTo(createPaymentRequest.orderId, createPaymentRequest.addressId)
        orderClient.updateOrderPaymentCreate(createPaymentRequest.orderId)
        return paymentClient.createPayment(createPaymentRequest)
    }

    @PostMapping("/callback")
    @PreAuthorize("hasAuthority('$PROCESS_BANK_CALLBACK_AUTHORITY_NAME')")
    fun processBankCallback(@RequestBody processTransactionResponse: ProcessTransactionResponse) {
        bankCallbackHandler.handleTransactionResponse(processTransactionResponse)
    }
}
