package com.chomik.storage.controller

import com.chomik.storage.client.dto.CreateSaleRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sales")
class SalesController(

) {
    @PostMapping
    fun createSale(@RequestBody createSaleRequest: CreateSaleRequest) {

    }
}
