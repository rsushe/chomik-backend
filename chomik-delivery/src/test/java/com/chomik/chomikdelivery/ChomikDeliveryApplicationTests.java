package com.chomik.chomikdelivery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ChomikDeliveryApplication.class)
@TestPropertySource("classpath:application-test.yml")
class ChomikDeliveryApplicationTests {

	@Test
	void contextLoads() {
	}

}
