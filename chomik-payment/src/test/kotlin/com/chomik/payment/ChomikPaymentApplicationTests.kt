package com.chomik.payment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikPaymentApplication::class])
@TestPropertySource("classpath:application-test.yml")
@EmbeddedKafka(
    topics = ["chomik_topic"],
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:12"
    ]
)
@ActiveProfiles("test")
class ChomikPaymentApplicationTests {

    @Test
    fun contextLoads() {
    }

}
