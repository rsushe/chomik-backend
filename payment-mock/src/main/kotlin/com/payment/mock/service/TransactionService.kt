package com.payment.mock.service

import com.payment.mock.domain.PaymentStatus
import com.payment.mock.domain.Transaction
import com.payment.mock.repository.TransactionRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class TransactionService(
    private val entityManager: EntityManager,
    private val transactionRepository: TransactionRepository,
) {
    fun save(charge: Int) = transactionRepository.save(Transaction(charge = charge, status = PaymentStatus.CREATED))

    fun process(transactionId: String): Transaction {
        var transaction = transactionRepository.findById(transactionId).orElseThrow {
            IllegalArgumentException("There is no transaction with id $transactionId")
        }

        entityManager.clear()

        while (transaction.status == PaymentStatus.CREATED) {
            Thread.sleep(Duration.ofSeconds(5))
            transaction = transactionRepository.findById(transactionId).get()
            entityManager.clear()
        }

        return transaction
    }
}
