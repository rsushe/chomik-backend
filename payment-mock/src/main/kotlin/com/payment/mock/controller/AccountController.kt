package com.payment.mock.controller

import com.payment.mock.domain.Account
import com.payment.mock.model.CreateAccountRequest
import com.payment.mock.model.TransferMoneyRequest
import com.payment.mock.service.AccountService

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account")
class AccountController(private val accountService: AccountService) {
    @PostMapping
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequest): Account =
        accountService.createAccount(createAccountRequest)

    @PostMapping("/transfer")
    fun transferMoney(@RequestBody transferMoneyRequest: TransferMoneyRequest) =
        accountService.transferMoney(transferMoneyRequest)
}
