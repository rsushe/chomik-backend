package com.payment.mock.service

import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.domain.Transaction
import com.payment.mock.model.ProcessedTransactionResponse
import com.payment.mock.model.TransactionStatus
import com.payment.mock.repository.TransactionRepository
import jakarta.persistence.EntityManager
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

private const val SLEEP_TIME = 5000L

@Service
class TransactionService(
    private val entityManager: EntityManager,
    private val transactionRepository: TransactionRepository,
) {
    private val restTemplate: RestTemplate = RestTemplate()

    fun save(createTransactionRequest: CreateTransactionRequest) =
        transactionRepository.save(
            Transaction(
                charge = createTransactionRequest.charge,
                callbackUrl = createTransactionRequest.callbackUrl,
                token = createTransactionRequest.token,
                status = TransactionStatus.CREATED
            )
        )

    @Transactional
    fun process(transactionId: String): TransactionStatus {
        val transaction = transactionRepository.findById(transactionId).orElseThrow {
            IllegalArgumentException("There is no transaction with id $transactionId")
        }

        while (transaction.status == TransactionStatus.CREATED) {
            Thread.sleep(SLEEP_TIME)
            entityManager.refresh(transaction)
        }

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${transaction.token}")

        val entity = HttpEntity(ProcessedTransactionResponse(transactionId, transaction.status), headers)

        restTemplate.exchange(transaction.callbackUrl, HttpMethod.POST, entity, Any::class.java)

        return transaction.status
    }
}
