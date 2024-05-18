package com.chomik.payment.service.kafka

import com.chomik.payment.client.dto.PaymentDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class PaymentResultMessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, PaymentDto>,
    @Value("\${spring.kafka.producer.topic}") private val topic: String,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun producePaymentResultMessage(paymentDto: PaymentDto) {
        kafkaTemplate.send(topic, paymentDto).whenComplete { result, ex ->
            log.info("PaymentResultMessageProducer: produce response result: {}", result)
            log.info("PaymentResultMessageProducer: produce response error: {}", ex.stackTraceToString())
        }
    }
}
