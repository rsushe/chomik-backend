package com.chomik.chomikdelivery.config;

import com.fakecdek.deliverymockclient.DeliveryMockClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationConfiguration {
    @Bean
    public DeliveryMockClient deliveryMockClient(@Value("${delivery.mock.url}") String deliveryMockUrl){
        return new DeliveryMockClient(deliveryMockUrl, new RestTemplate());
    }

}
