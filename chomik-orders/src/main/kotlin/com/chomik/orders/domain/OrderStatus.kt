package com.chomik.orders.domain

enum class OrderStatus {
    WAIT_PAYMENT,
    CANCELED,
    IN_DELIVERY,
    COMPLETED
}