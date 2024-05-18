package com.chomik.core.gateway.service.payment

import com.payment.mock.model.ProcessTransactionResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class BankCallbackMessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, ProcessTransactionResponse>,
    @Value("\${spring.kafka.producer.topic}") private val topic: String,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun produceTransactionResponse(processTransactionResponse: ProcessTransactionResponse) {
        kafkaTemplate.send(topic, processTransactionResponse).whenComplete { result, ex ->
            log.info("PaymentKafkaProducer: produce response result: {}", result.toString())
            log.info("PaymentKafkaProducer: produce response error: {}", ex.stackTraceToString())
        }
    }
}
