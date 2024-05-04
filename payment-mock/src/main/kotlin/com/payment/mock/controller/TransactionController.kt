package com.payment.mock.controller

import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.client.dto.CreateTransactionResponse
import com.payment.mock.model.TransactionStatus
import com.payment.mock.service.TransactionService

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(
    private val transactionService: TransactionService,
    @Value("\${balancer.url}") private val balancerUrl: String,
) {
    @PostMapping
    fun createTransaction(@RequestBody createTransactionRequest: CreateTransactionRequest): ResponseEntity<CreateTransactionResponse> {
        val transaction = transactionService.save(createTransactionRequest)

        return ResponseEntity.ok(
            CreateTransactionResponse(
                paymentUrl = "$balancerUrl/api/v1/transaction/${transaction.id}",
                transactionId = transaction.id!!
            )
        )
    }

    @GetMapping("/{transactionId}")
    fun processTransaction(@PathVariable transactionId: String): ResponseEntity<TransactionStatus> =
        ResponseEntity.ok(transactionService.process(transactionId))
}
