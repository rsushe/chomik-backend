package com.chomik.payment.mock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChomikPaymentMockApplication

fun main(args: Array<String>) {
	runApplication<ChomikPaymentMockApplication>(*args)
}
