package com.chomik.payment.domain

enum class PaymentStatus {
    WAIT_BANK_CALLBACK,
    SUCCESSFUL,
    FAIL
}