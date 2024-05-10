package com.payment.mock.model;

public class ProcessTransactionResponse {
    private String transactionId;
    private TransactionStatus status;

    public ProcessTransactionResponse() {
    }

    public ProcessTransactionResponse(String transactionId, TransactionStatus status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
