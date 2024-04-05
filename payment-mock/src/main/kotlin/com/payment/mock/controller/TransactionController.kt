package com.payment.mock.controller

import com.payment.mock.controller.dto.ClientTransactionDto
import com.payment.mock.domain.Transaction
import com.payment.mock.service.TransactionService

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
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
    fun createTransaction(@RequestBody charge: Int): ResponseEntity<ClientTransactionDto> {
        val transaction = transactionService.save(charge)

        return ResponseEntity.ok(
            ClientTransactionDto(
                paymentUrl = "$balancerUrl/api/v1/transaction/${transaction.id}",
                transactionId = transaction.id!!
            )
        )
    }

    @PostMapping("/{transactionId}")
    fun processTransaction(@PathVariable transactionId: String): ResponseEntity<Transaction> =
        ResponseEntity.ok(transactionService.process(transactionId))
}
