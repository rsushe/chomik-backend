package com.chomik.core.gateway.service.payment

import com.chomik.payment.client.dto.PaymentDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class PaymentResultMessageHandler(
    private val bankCallbackHandler: BankCallbackHandler,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["chomik_bank_callback_response"])
    fun handlePaymentResult(paymentDto: PaymentDto) {
        log.info("PaymentResultMessageHandler: handlePaymentResult {}", paymentDto.toString())

        bankCallbackHandler.handlePaymentResponse(paymentDto)
    }
}
