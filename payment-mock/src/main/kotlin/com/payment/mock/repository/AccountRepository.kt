package com.payment.mock.repository

import com.payment.mock.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, String> {
    fun findAccountByCardNumberAndCvv(cardNumber: String, cvv: Int): Account?
}
