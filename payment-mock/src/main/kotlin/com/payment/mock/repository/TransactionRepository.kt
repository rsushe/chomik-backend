package com.payment.mock.repository

import com.payment.mock.domain.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<Transaction, String> {
}
