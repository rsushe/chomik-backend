package com.chomik.orders.service

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.domain.Order
import com.chomik.orders.extension.toOrder
import com.chomik.orders.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order =
        orderRepository.save(createOrderRequest.toOrder())

    @Transactional
    fun cancelOrderWhereIdIn(ids: List<String>) = orderRepository.cancelOrderWhereIdIn(ids)
}
