package com.chomik.orders.service

import com.chomik.orders.domain.Order
import com.chomik.orders.domain.OrderStatus
import com.chomik.orders.repository.OrderRepository
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository, private val sneakerCountService: SneakerCountService) {

    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val availableSneakerCount = sneakerCountService.getSneakerCount(createOrderRequest.advertId)

        if (createOrderRequest.sneakerCount > availableSneakerCount) {
            throw IllegalArgumentException("Maximum available sneaker count to create order is $availableSneakerCount")
        }

        val order = Order(
            buyerId = createOrderRequest.buyerId,
            advertId = createOrderRequest.advertId,
            status = OrderStatus.WAIT_PAYMENT,
            sneakerCount = createOrderRequest.sneakerCount
        )
        return orderRepository.save(order)
    }
}