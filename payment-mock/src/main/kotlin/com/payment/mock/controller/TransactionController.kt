package com.payment.mock.controller

import com.payment.mock.domain.Transaction
import com.payment.mock.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(private val transactionService: TransactionService) {
    @PostMapping
    fun createTransaction(@RequestBody charge: Int): ResponseEntity<Transaction> =
        ResponseEntity.ok(transactionService.save(charge))
}
