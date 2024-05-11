package com.chomik.storage.controller

import com.chomik.storage.client.dto.CreateSaleRequest
import com.chomik.storage.repository.sales.SalesRepository
import com.chomik.storage.service.SalesService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sales")
class SalesController(
    private val salesService: SalesService,
) {
    @PostMapping
    fun createSale(@RequestBody createSaleRequest: CreateSaleRequest) {
        salesService.createSaleOnAdvert(createSaleRequest)
    }
}
