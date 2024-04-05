package com.chomik.orders.facade

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderStatus
import com.chomik.orders.domain.Order
import com.chomik.orders.exception.InabilityLockingOrderException
import com.chomik.orders.service.OrderService
import com.chomik.storage.client.AdvertClient
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OrderFacade(
    private val orderService: OrderService,
    private val advertClient: AdvertClient
) {
    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val availableSneakerCount = advertClient.getSneakersCountById(createOrderRequest.advertId).body
            ?: throw IllegalArgumentException("Couldn't find advert with id ${createOrderRequest.advertId}")

        if (createOrderRequest.sneakerCount > availableSneakerCount) {
            throw InabilityLockingOrderException("Maximum available sneaker count to create order is $availableSneakerCount")
        }

        val lockedSneakersOfAdvert = orderService.countWaitingPaymentOrders(createOrderRequest.advertId)

        if (createOrderRequest.sneakerCount + lockedSneakersOfAdvert >= availableSneakerCount) {
            throw InabilityLockingOrderException("There are only ${availableSneakerCount - lockedSneakersOfAdvert} number of sneakers available right now")
        }

        return orderService.createNewOrder(createOrderRequest)
    }

    @Transactional
    fun updateOrderToPayment(orderId: String): Order {
        val order = orderService.findById(orderId)

        if (order.status != OrderStatus.WAIT_PAYMENT) {
            throw IllegalArgumentException("Order status is invalid: ${order.status}")
        }

        return orderService.updateOrder(order, OrderStatus.PAYMENT_IN_PROGRESS)
    }
}
