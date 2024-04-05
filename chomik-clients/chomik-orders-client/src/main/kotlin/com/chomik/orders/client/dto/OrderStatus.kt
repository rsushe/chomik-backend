package com.chomik.orders.client.dto

enum class OrderStatus {
    WAIT_PAYMENT,
    PAYMENT_IN_PROGRESS,
    EXPIRED,
    CANCELED,
    IN_DELIVERY,
    COMPLETED,
}
