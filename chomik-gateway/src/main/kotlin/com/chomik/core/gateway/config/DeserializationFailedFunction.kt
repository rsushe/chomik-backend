package com.chomik.core.gateway.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.kafka.support.serializer.FailedDeserializationInfo
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class DeserializationFailedFunction: Function<FailedDeserializationInfo, Any> {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun apply(t: FailedDeserializationInfo) {
        log.error("Error while deserialization. Message: ${String(t.data)}. Topic: ${t.topic}")
    }
}
