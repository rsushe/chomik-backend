package com.chomik.orders

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikOrdersApplication::class])
@TestPropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
class ChomikOrdersApplicationTests {

    @Test
    fun contextLoads() {
    }

}
