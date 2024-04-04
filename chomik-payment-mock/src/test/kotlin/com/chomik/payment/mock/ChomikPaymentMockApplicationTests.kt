package com.chomik.payment.mock

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikPaymentMockApplication::class])
@TestPropertySource("classpath:application-test.yml")
class ChomikPaymentMockApplicationTests {

	@Test
	fun contextLoads() {
	}

}
