package com.chomik.payment.configuration

import com.chomik.orders.client.OrderClient
import com.payment.mock.client.PaymentMockClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {
    @Bean
    fun paymentMockClient(@Value("\${payment.mock.url}") paymentMockUrl: String) = PaymentMockClient(paymentMockUrl)

    @Bean
    fun orderClient(@Value("\${chomik.order.url}") orderUrl: String) = OrderClient(orderUrl)
}
