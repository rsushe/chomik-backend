package com.chomik.orders.service

import com.chomik.orders.domain.Order
import com.chomik.orders.domain.OrderStatus
import com.chomik.orders.repository.OrderRepository
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val order = Order(
            buyerId = createOrderRequest.buyerId,
            advertId = createOrderRequest.advertId,
            status = OrderStatus.WAIT_PAYMENT,
            sneakerCount = createOrderRequest.sneakerCount
        )
        return orderRepository.save(order)
    }
}