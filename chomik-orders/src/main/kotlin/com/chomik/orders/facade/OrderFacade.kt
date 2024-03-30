package com.chomik.orders.facade

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import com.chomik.orders.extension.toAdvertLock
import com.chomik.orders.extension.toDto
import com.chomik.orders.service.AdvertLockService
import com.chomik.orders.service.OrderService
import com.chomik.orders.service.SneakerCountService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OrderFacade(
    private val orderService: OrderService,
    private val sneakerCountService: SneakerCountService,
    private val advertLockService: AdvertLockService,
) {
    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): OrderDto {
        val availableSneakerCount = sneakerCountService.getSneakerCount(createOrderRequest.advertId)

        if (createOrderRequest.sneakerCount > availableSneakerCount) {
            throw IllegalArgumentException("Maximum available sneaker count to create order is $availableSneakerCount")
        }

        sneakerCountService.updateCount(
            createOrderRequest.advertId,
            availableSneakerCount - createOrderRequest.sneakerCount
        )

        val order = orderService.createNewOrder(createOrderRequest)

        advertLockService.save(order.toAdvertLock())

        return order.toDto()
    }
}
