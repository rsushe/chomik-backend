package com.chomik.payment.service

import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.payment.domain.Payment
import com.chomik.payment.domain.PaymentStatus
import com.chomik.payment.repository.PaymentRepository
import com.payment.mock.client.PaymentMockClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMockClient: PaymentMockClient
) {
    @Transactional
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreatePaymentResponse {
        val createTransactionResponse = paymentMockClient.createTransaction(createPaymentRequest.charge).body
            ?: throw IllegalStateException("Bank is dead")

        paymentRepository.save(Payment(
            transactionId = createTransactionResponse.transactionId,
            orderId = createPaymentRequest.orderId,
            status = PaymentStatus.WAIT_BANK_CALLBACK
        ))

        return CreatePaymentResponse(createTransactionResponse.paymentUrl)
    }
}
