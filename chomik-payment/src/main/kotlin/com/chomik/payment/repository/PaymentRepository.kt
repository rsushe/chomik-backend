package com.chomik.payment.repository

import com.chomik.payment.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<Payment, String> {
    @Modifying
    @Query(
        value = "UPDATE payment SET status = :paymentStatus WHERE transaction_id = :transactionId RETURNING *",
        nativeQuery = true
    )
    fun updatePaymentStatus(transactionId: String, paymentStatus: String): List<Payment>
}
