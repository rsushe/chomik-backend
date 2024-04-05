package com.payment.mock.client

import com.payment.mock.client.dto.CreateTransactionDto
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class PaymentMockClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {
    fun createTransaction(charge: Int): ResponseEntity<CreateTransactionDto> {
        val uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path("api/v1/transaction")

        return restTemplate.postForEntity(uri.toUriString(), charge, CreateTransactionDto::class.java)
    }
}
