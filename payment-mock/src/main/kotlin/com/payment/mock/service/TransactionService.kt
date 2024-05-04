package com.payment.mock.service

import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.domain.Transaction
import com.payment.mock.model.ProcessTransactionRequest
import com.payment.mock.model.ProcessTransactionResponse
import com.payment.mock.model.TransactionStatus
import com.payment.mock.model.UpdateBalanceRequest
import com.payment.mock.repository.TransactionRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class TransactionService(
    private val accountService: AccountService,
    private val transactionRepository: TransactionRepository,
) {
    private val restTemplate: RestTemplate = RestTemplate()

    fun save(createTransactionRequest: CreateTransactionRequest) =
        transactionRepository.save(
            Transaction(
                charge = createTransactionRequest.charge,
                accountTo = createTransactionRequest.accountTo,
                callbackUrl = createTransactionRequest.callbackUrl,
                token = createTransactionRequest.token,
                status = TransactionStatus.CREATED
            )
        )

    @Transactional
    fun process(transactionId: String, processTransactionRequest: ProcessTransactionRequest): TransactionStatus {
        val transaction = transactionRepository.findById(transactionId).orElseThrow {
            IllegalArgumentException("There is no transaction with id $transactionId")
        }

        val account = accountService.findAccountByCardNumberAndCvv(
            processTransactionRequest.cardNumber,
            processTransactionRequest.cvv
        ) ?: throw IllegalArgumentException("Account with input data doesn't exists")

        if (account.balance < transaction.charge) {
            //TODO maybe change status to NEDOSTATOCHO_DENEG
            val updatedTransaction = transaction.copy(status = TransactionStatus.FAIL)
            return transactionRepository.save(updatedTransaction).status
        }

        accountService.updateBalance(UpdateBalanceRequest(account.id, -transaction.charge))
        accountService.updateBalance(UpdateBalanceRequest(transaction.accountTo, transaction.charge))

        val updatedTransaction = transaction.copy(status = TransactionStatus.SUCCESS)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ${transaction.token}")

        val entity = HttpEntity(
            ProcessTransactionResponse(
                transactionId,
                transaction.status
            ), headers
        )

        restTemplate.exchange(transaction.callbackUrl, HttpMethod.POST, entity, Any::class.java)

        return transactionRepository.save(updatedTransaction).status
    }
}
