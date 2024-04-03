package com.chomik.core.gateway

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikGatewayApplication::class])
@TestPropertySource("classpath:application-test.yml")
class ChomikGatewayApplicationTests {

    @Test
    fun contextLoads() {
    }
}
