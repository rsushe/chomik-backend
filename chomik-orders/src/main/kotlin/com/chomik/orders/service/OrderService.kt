package com.chomik.orders.service

import com.chomik.orders.domain.Order
import com.chomik.orders.domain.OrderStatus
import com.chomik.orders.repository.OrderRepository
import com.chomik.orders.service.dto.CreateOrderRequest
import org.springframework.stereotype.Service


@Service
class OrderService (private val orderRepository: OrderRepository) {

    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val order = Order(
            buyerId = createOrderRequest.buyerId,
            advertId = createOrderRequest.advertId,
            status = OrderStatus.WAIT_PAYMENT
        )
        return orderRepository.save(order);
    }

}