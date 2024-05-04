package com.chomik.payment.client

import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.payment.client.dto.PaymentDto
import com.payment.mock.model.ProcessTransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class PaymentClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun createPayment(createPaymentRequest: CreatePaymentRequest): ResponseEntity<CreatePaymentResponse> {
        val uri = createBuilder("/api/v1/payment")

        return restTemplate.postForEntity(uri.toUriString(), createPaymentRequest, CreatePaymentResponse::class.java)
    }

    fun processBankCallback(processTransactionResponse: ProcessTransactionResponse): ResponseEntity<PaymentDto> {
        val uri = createBuilder("/api/v1/payment/callback")

        return restTemplate.postForEntity(uri.toUriString(), processTransactionResponse, PaymentDto::class.java)
    }

    private fun createBuilder(method: String): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method)
    }
}
