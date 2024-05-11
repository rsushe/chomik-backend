package com.chomik.core.gateway.config

import com.chomik.delivery.client.DeliveryClient
import com.chomik.orders.client.OrderClient
import com.chomik.payment.client.PaymentClient
import com.chomik.storage.client.AdvertClient
import com.chomik.storage.client.SneakersClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@EnableConfigurationProperties(UserAuthorities::class)
class ApplicationConfiguration {
    @Bean
    fun advertClient(@Value("\${chomik.storage.url}") storageUrl: String) = AdvertClient(storageUrl)

    @Bean
    fun sneakersClient(@Value("\${chomik.storage.url}") storageUrl: String) = SneakersClient(storageUrl)

    @Bean
    fun orderClient(@Value("\${chomik.order.url}") orderUrl: String) = OrderClient(orderUrl)

    @Bean
    fun paymentClient(@Value("\${chomik.payment.url}") paymentUrl: String) = PaymentClient(paymentUrl)

    @Bean
    fun deliveryClient(@Value("\${chomik.delivery.url}") deliveryUrl: String) = DeliveryClient(deliveryUrl)
}
