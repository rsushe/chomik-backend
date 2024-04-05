package com.chomik.payment.service

import com.chomik.orders.client.dto.CreatePaymentRequest
import com.chomik.payment.domain.Payment
import com.chomik.payment.domain.PaymentStatus
import com.chomik.payment.repository.PaymentRepository
import com.payment.mock.client.PaymentMockClient
import com.payment.mock.client.dto.CreateTransactionResponse
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMockClient: PaymentMockClient
) {
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreateTransactionResponse {
        val createTransactionResponse = paymentMockClient.createTransaction(createPaymentRequest.charge).body
            ?: throw IllegalStateException("Bank is dead")

        paymentRepository.save(Payment(
            transactionId = createTransactionResponse.transactionId,
            orderId = createPaymentRequest.orderId,
            status = PaymentStatus.WAIT_BANK_CALLBACK
        ))

        return createTransactionResponse
    }
}
