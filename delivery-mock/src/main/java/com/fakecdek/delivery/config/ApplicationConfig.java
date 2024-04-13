package com.fakecdek.delivery.config;


import com.chomik.delivery.client.DeliveryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    public DeliveryClient deliveryClient(@Value("${chomik.delivery.url}") String deliveryUrl){
        return new DeliveryClient(deliveryUrl, new RestTemplate());
    }
}
