package com.payment.mock.service

import com.payment.mock.domain.Account
import com.payment.mock.model.CreateAccountRequest
import com.payment.mock.model.UpdateBalanceRequest
import com.payment.mock.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findAccountByCardNumberAndCvv(cardNumber: String, cvv: Int) =
        accountRepository.findAccountByCardNumberAndCvv(cardNumber, cvv)

    @Transactional
    fun createAccount(createAccountRequest: CreateAccountRequest): Account = accountRepository.save(
        Account(
            name = createAccountRequest.accountName,
            cardNumber = generateCardNumber(),
            cvv = (100..999).random(),
            expireAt = Date.from(Instant.now().plus(2, ChronoUnit.YEARS)),
            balance = 0
        )
    )

    @Transactional
    fun updateBalance(updateBalanceRequest: UpdateBalanceRequest): Account {
        val account = accountRepository.findById(updateBalanceRequest.accountId).orElseThrow {
            IllegalArgumentException("Account with id ${updateBalanceRequest.accountId} doesn't exists")
        }

        val updatedAccount = account.copy(balance = account.balance + updateBalanceRequest.amountToAdd)
        return accountRepository.save(updatedAccount)
    }

    private fun generateCardNumber(): String {
        var cardNumber = ""
        for (i in 1..16) {
            cardNumber += (0..9).random()
        }
        return cardNumber
    }
}
