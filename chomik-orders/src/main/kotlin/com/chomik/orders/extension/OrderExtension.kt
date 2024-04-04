package com.chomik.orders.extension

import com.chomik.orders.client.dto.CreateOrderRequest
import com.chomik.orders.client.dto.OrderDto
import com.chomik.orders.client.dto.OrderStatus
import com.chomik.orders.domain.Order

fun Order.toDto(): OrderDto = OrderDto(
    id = this.id!!,
    buyerId = this.buyerId,
    advertId = this.advertId,
    status = this.status,
    sneakerCount = this.sneakerCount,
)

fun CreateOrderRequest.toOrder(orderStatus: OrderStatus = OrderStatus.WAIT_PAYMENT) = Order(
    buyerId = this.buyerId,
    advertId = this.advertId,
    status = orderStatus,
    sneakerCount = this.sneakerCount
)