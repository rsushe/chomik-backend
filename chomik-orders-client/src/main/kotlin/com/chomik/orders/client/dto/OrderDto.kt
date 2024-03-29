package com.chomik.orders.client.dto

data class OrderDto (
    var id: String,
    var buyerId: String,
    var advertId: String,
    var status: OrderStatus,
    val sneakerCount: Int,
)
