package com.payment.mock.service

import com.payment.mock.domain.Account
import com.payment.mock.model.CreateAccountRequest
import com.payment.mock.model.TransferMoneyRequest
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
            expireAt = Date.from(Instant.now().plus(365, ChronoUnit.DAYS)),
            balance = 0
        )
    )

    @Transactional
    fun transferMoney(transferMoneyRequest: TransferMoneyRequest) {
        val accountFrom = accountRepository.findById(transferMoneyRequest.accountFromId).orElseThrow {
            IllegalArgumentException("Account with id ${transferMoneyRequest.accountFromId} doesn't exists")
        }
        val accountTo = accountRepository.findById(transferMoneyRequest.accountToId).orElseThrow {
            IllegalArgumentException("Account with id ${transferMoneyRequest.accountToId} doesn't exists")
        }

        if (accountFrom.balance < transferMoneyRequest.amount) {
            throw IllegalStateException("Account ${transferMoneyRequest.accountFromId} hasn't enough money to transfer")
        }

        val updatedAccountFrom = accountFrom.copy(balance = accountFrom.balance - transferMoneyRequest.amount)
        val updatedAccountTo = accountTo.copy(balance = accountTo.balance + transferMoneyRequest.amount)

        accountRepository.save(updatedAccountFrom)
        accountRepository.save(updatedAccountTo)
    }

    private fun generateCardNumber(): String {
        var cardNumber = ""
        for (i in 1..16) {
            cardNumber += (0..9).random()
        }
        return cardNumber
    }
}
