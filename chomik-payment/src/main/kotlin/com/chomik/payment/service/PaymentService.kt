package com.chomik.payment.service

import com.chomik.payment.repository.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {
}
