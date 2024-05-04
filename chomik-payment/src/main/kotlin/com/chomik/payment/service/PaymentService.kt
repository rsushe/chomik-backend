package com.chomik.payment.service

import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.payment.client.dto.PaymentDto
import com.chomik.payment.domain.Payment
import com.chomik.payment.client.dto.PaymentStatus
import com.chomik.payment.repository.PaymentRepository
import com.chomik.payment.service.extention.toDto
import com.payment.mock.client.PaymentMockClient
import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.model.ProcessedTransactionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMockClient: PaymentMockClient,
    @Value("\${gateway.host}") private val gatewayHost: String,
) {
    @Transactional
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreatePaymentResponse {
        val request = CreateTransactionRequest(
            callbackUrl = "$gatewayHost/api/v1/payment/callback",
            charge = createPaymentRequest.charge,
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
    fun processBankCallback(processedTransactionResponse: ProcessedTransactionResponse) : PaymentDto {
        val paymentStatus = PaymentStatus.valueOf(processedTransactionResponse.status.name)

        val payment =
            paymentRepository.updatePaymentStatus(processedTransactionResponse.transactionId, paymentStatus.name)[0]
        return payment.toDto()
    }
}
