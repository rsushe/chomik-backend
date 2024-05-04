package com.payment.mock.model;

public record UpdateBalanceRequest(
    String accountId,
    Long amountToAdd
) {}
