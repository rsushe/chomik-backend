package com.fakecdek.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.winter", "com.fakecdek"})
public class DeliveryMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryMockApplication.class, args);
	}

}
