package com.chomik.orders.client.dto

enum class OrderStatus {
    WAIT_PAYMENT,
    EXPIRED,
    CANCELED,
    IN_DELIVERY,
    COMPLETED,
}