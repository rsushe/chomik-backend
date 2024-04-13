package com.chomik.chomikdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.chomik.chomikdelivery", "com.winter"})
public class ChomikDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChomikDeliveryApplication.class, args);
    }

}
