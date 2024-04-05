package com.chomik.orders.client

import org.springframework.web.client.RestTemplate

class PaymentClient(
    private val baseUrl: String,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

}
