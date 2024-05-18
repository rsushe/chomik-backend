package com.chomik.orders.facade

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderStatus
import com.chomik.orders.client.dto.UpdateUserAddressToRequest
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
    fun findById(orderId: String): Order = orderService.findById(orderId)

    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val availableSneakerCount = advertClient.getSneakersCountById(createOrderRequest.advertId).body
            ?: throw IllegalArgumentException("Couldn't find advert with id ${createOrderRequest.advertId}")

        if (createOrderRequest.sneakerCount > availableSneakerCount) {
            throw InabilityLockingOrderException("Maximum available sneaker count to create order is $availableSneakerCount")
        }

        val lockedSneakersOfAdvert = orderService.countWaitingPaymentOrders(createOrderRequest.advertId)

        if (createOrderRequest.sneakerCount + lockedSneakersOfAdvert > availableSneakerCount) {
            throw InabilityLockingOrderException("There are only ${availableSneakerCount - lockedSneakersOfAdvert} number of sneakers available right now")
        }

        return orderService.createNewOrder(createOrderRequest)
    }

    @Transactional
    fun updateOrderPaymentCreate(orderId: String): Order {
        val order = orderService.findById(orderId)

        if (order.status !in listOf(OrderStatus.WAIT_PAYMENT, OrderStatus.PAYMENT_IN_PROGRESS)) {
            throw IllegalArgumentException("Order status is invalid: ${order.status}")
        }

        return orderService.updateOrderStatus(order, OrderStatus.PAYMENT_IN_PROGRESS)
    }

    @Transactional
    fun updateOrderPaymentFinish(orderId: String): Order {
        val order = orderService.findById(orderId)

        if (order.status != OrderStatus.PAYMENT_IN_PROGRESS) {
            throw IllegalArgumentException("Bank sent payment callback, but order not in PAYMENT_IN_PROGRESS state. Something very strange...")
        }

        return orderService.updateOrderStatus(order, OrderStatus.IN_DELIVERY)
    }

    @Transactional
    fun updateOrderUserAddressTo(orderId: String, updateUserAddressToRequest: UpdateUserAddressToRequest) : Order {
        val order = orderService.findById(orderId)

        if (order.status !in listOf(OrderStatus.WAIT_PAYMENT, OrderStatus.PAYMENT_IN_PROGRESS)) {
            throw IllegalArgumentException("Couldn't set user address cause order already payed or expired")
        }

        return orderService.updateOrderUserAddressTo(order, updateUserAddressToRequest.userAddressTo)
    }
}
