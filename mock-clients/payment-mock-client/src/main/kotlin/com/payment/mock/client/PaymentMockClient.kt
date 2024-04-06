package com.payment.mock.client

import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.client.dto.CreateTransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class PaymentMockClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun createTransaction(request: CreateTransactionRequest): ResponseEntity<CreateTransactionResponse> {
        val uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path("api/v1/transaction")

        return restTemplate.postForEntity(uri.toUriString(), request, CreateTransactionResponse::class.java)
    }
}
