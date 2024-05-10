package com.payment.mock.model;

public record ProcessTransactionRequest(String cardNumber, Integer cvv) {
}
