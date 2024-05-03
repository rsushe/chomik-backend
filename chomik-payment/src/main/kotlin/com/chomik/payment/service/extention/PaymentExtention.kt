package com.chomik.payment.service.extention

import com.chomik.payment.client.dto.PaymentDto
import com.chomik.payment.domain.Payment

fun Payment.toDto() = PaymentDto(
    id = id,
    transactionId = transactionId,
    orderId = orderId,
    status = status
)
