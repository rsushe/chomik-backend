package com.payment.mock.service

import com.payment.mock.domain.PaymentStatus
import com.payment.mock.domain.Transaction
import com.payment.mock.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {
    fun save(charge: Int) = transactionRepository.save(Transaction(charge = charge, status = PaymentStatus.CREATED))
}
