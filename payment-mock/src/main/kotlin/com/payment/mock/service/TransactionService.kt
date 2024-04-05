package com.payment.mock.service

import com.payment.mock.domain.PaymentStatus
import com.payment.mock.domain.Transaction
import com.payment.mock.repository.TransactionRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val SLEEP_TIME = 5000L

@Service
class TransactionService(
    private val entityManager: EntityManager,
    private val transactionRepository: TransactionRepository,
) {
    fun save(charge: Int) = transactionRepository.save(Transaction(charge = charge, status = PaymentStatus.CREATED))

    @Transactional
    fun process(transactionId: String): Transaction {
        val transaction = transactionRepository.findById(transactionId).orElseThrow {
            IllegalArgumentException("There is no transaction with id $transactionId")
        }

        while (transaction.status == PaymentStatus.CREATED) {
            Thread.sleep(SLEEP_TIME)
            entityManager.refresh(transaction)
        }

        return transaction
    }
}
