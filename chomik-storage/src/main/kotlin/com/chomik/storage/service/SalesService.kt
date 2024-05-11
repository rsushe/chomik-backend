package com.chomik.storage.service

import com.chomik.storage.client.dto.CreateSaleRequest
import com.chomik.storage.domain.Sales
import com.chomik.storage.domain.Sneakers
import com.chomik.storage.repository.sales.SalesRepository
import com.chomik.storage.repository.storage.AdvertRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SalesService(
    private val salesRepository: SalesRepository,
    private val advertService: AdvertService
) {


    @Transactional(rollbackFor = [Exception::class])
    fun createSaleOnAdvert(createSaleRequest: CreateSaleRequest) {
        val advert = advertService.getAdvertById(createSaleRequest.advertId)!!

        val oldPrice = advert.price
        val newPrice = oldPrice * (100 - createSaleRequest.salePercent) / 100

        val sale = Sales(
            advertId = createSaleRequest.advertId,
            oldPrice = oldPrice, newPrice = newPrice,
            salePercent = createSaleRequest.salePercent)
        salesRepository.save(sale)

        val updatedPriceAdvert = advert.copy(price = newPrice)
        advertService.save(advert)
    }
}