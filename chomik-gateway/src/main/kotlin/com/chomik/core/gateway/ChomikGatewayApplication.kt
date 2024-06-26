package com.chomik.core.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChomikGatewayApplication

fun main(args: Array<String>) {
    runApplication<ChomikGatewayApplication>(*args)
}
