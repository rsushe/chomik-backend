package com.chomik.payment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikPaymentApplication::class])
@TestPropertySource("classpath:application-test.yml")
class ChomikPaymentApplicationTests {

    @Test
    fun contextLoads() {
    }

}
