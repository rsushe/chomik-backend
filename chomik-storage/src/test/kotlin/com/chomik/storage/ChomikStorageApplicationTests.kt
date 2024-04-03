package com.chomik.storage

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ChomikStorageApplication::class])
@TestPropertySource("classpath:application-test.yml")
class ChomikStorageApplicationTests {

    @Test
    fun contextLoads() {
    }

}
