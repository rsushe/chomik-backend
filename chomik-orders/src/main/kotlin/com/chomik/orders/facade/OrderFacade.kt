package com.chomik.orders.facade

import com.chomik.orders.domain.Order
import com.chomik.orders.service.AdvertLockService
import com.chomik.orders.service.OrderService
import com.chomik.orders.service.SneakerCountService
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OrderFacade(
    private val orderService: OrderService,
    private val sneakerCountService: SneakerCountService,
    private val advertLockService: AdvertLockService,
) {
    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val availableSneakerCount = sneakerCountService.getSneakerCount(createOrderRequest.advertId)

        if (createOrderRequest.sneakerCount > availableSneakerCount) {
            throw IllegalArgumentException("Maximum available sneaker count to create order is $availableSneakerCount")
        }

        sneakerCountService.updateCount(
            createOrderRequest.advertId,
            availableSneakerCount - createOrderRequest.sneakerCount
        )

        advertLockService.save(createOrderRequest)

        return orderService.createNewOrder(createOrderRequest)
    }
}
