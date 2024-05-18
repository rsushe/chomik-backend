package com.chomik.core.gateway

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@Import(ChomikTestConfiguration::class)
@SpringBootTest(classes = [ChomikGatewayApplication::class])
@TestPropertySource("classpath:application-test.yml")
@EmbeddedKafka(
    topics = ["chomik_topic"],
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:12"
    ]
)
@ActiveProfiles("test")
class ChomikGatewayApplicationTests {

    @Test
    fun contextLoads() {
    }
}
