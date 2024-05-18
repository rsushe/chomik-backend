package com.chomik.payment.service

import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.payment.client.dto.PaymentDto
import com.chomik.payment.domain.Payment
import com.chomik.payment.client.dto.PaymentStatus
import com.chomik.payment.repository.PaymentRepository
import com.chomik.payment.service.extention.toDto
import com.chomik.payment.service.kafka.PaymentResultMessageProducer
import com.payment.mock.client.PaymentMockClient
import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.model.ProcessTransactionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMockClient: PaymentMockClient,
    private val paymentResultMessageProducer: PaymentResultMessageProducer,
    @Value("\${gateway.host}") private val gatewayHost: String,
    @Value("\${chomik.bank.account.id}") private val bankAccountId: String,
) {
    @Transactional
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreatePaymentResponse {
        val request = CreateTransactionRequest(
            callbackUrl = "$gatewayHost/api/v1/payment/callback",
            charge = createPaymentRequest.charge,
            accountTo = bankAccountId,
            token = createPaymentRequest.bankToken!!
        )

        val createTransactionResponse = paymentMockClient.createTransaction(request).body
            ?: throw IllegalStateException("Bank is dead")

        paymentRepository.save(
            Payment(
                transactionId = createTransactionResponse.transactionId,
                orderId = createPaymentRequest.orderId,
                status = PaymentStatus.WAIT_BANK_CALLBACK
            )
        )

        return CreatePaymentResponse(createTransactionResponse.paymentUrl)
    }

    @Transactional
    fun processBankCallback(processTransactionResponse: ProcessTransactionResponse) : PaymentDto {
        val paymentStatus = PaymentStatus.valueOf(processTransactionResponse.status.name)

        val payment =
            paymentRepository.updatePaymentStatus(processTransactionResponse.transactionId, paymentStatus.name)[0]

        paymentResultMessageProducer.producePaymentResultMessage(payment.toDto())
        return payment.toDto()
    }
}
