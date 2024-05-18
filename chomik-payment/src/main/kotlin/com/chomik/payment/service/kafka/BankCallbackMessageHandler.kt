package com.chomik.payment.service.kafka

import com.chomik.payment.service.PaymentService
import com.payment.mock.model.ProcessTransactionResponse
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class BankCallbackMessageHandler(
    private val paymentService: PaymentService,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["chomik_bank_callback_request"])
    fun handleBankCallback(processTransactionResponse: ProcessTransactionResponse) {
        log.info(
            "BankCallbackMessageHandler: handle transaction response from topic: {}",
            processTransactionResponse.toString()
        )

        paymentService.processBankCallback(processTransactionResponse)
    }
}
