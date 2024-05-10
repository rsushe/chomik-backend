package com.payment.mock.model;

public record TransferMoneyRequest(
    String accountFromId,
    String accountToId,
    Long amount
) {}
