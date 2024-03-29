package com.chomik.orders

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ChomikOrdersApplication

fun main(args: Array<String>) {
    runApplication<ChomikOrdersApplication>(*args)
}
