package com.payment.mock

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [PaymentMockApplication::class])
@TestPropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
class PaymentMockApplicationTests {

	@Test
	fun contextLoads() {
	}

}
