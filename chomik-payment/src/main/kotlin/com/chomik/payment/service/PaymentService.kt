package com.chomik.payment.service

import com.chomik.orders.client.OrderClient
import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.payment.domain.Payment
import com.chomik.payment.domain.PaymentStatus
import com.chomik.payment.repository.PaymentRepository
import com.payment.mock.client.PaymentMockClient
import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.client.dto.ProcessedTransactionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentMockClient: PaymentMockClient,
    private val orderClient: OrderClient,
    @Value("\${balancer.host}") private val balancerHost: String,
) {
    @Transactional
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreatePaymentResponse {
        val request = CreateTransactionRequest(
            callbackUrl = "$balancerHost/api/v1/payment/callback",
            charge = createPaymentRequest.charge
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
    fun processBankCallback(processedTransactionResponse: ProcessedTransactionResponse) {
        val paymentStatus = PaymentStatus.valueOf(processedTransactionResponse.status.name)

        val payment =
            paymentRepository.updatePaymentStatus(processedTransactionResponse.transactionId, paymentStatus.name)[0]

        if (paymentStatus == PaymentStatus.SUCCESS) {
            //TODO decrement sneaker count in storage
            orderClient.updateOrderPaymentFinish(payment.orderId)
        }
    }
}
