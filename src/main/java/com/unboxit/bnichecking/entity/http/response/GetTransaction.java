package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetTransaction {
    @JsonProperty("transaction_Id")
    private long transactionId;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;
    private long amount;
    private String note;
    private LocalDateTime createdAt;

    public GetTransaction(long transactionId, String accountNumberSource, String accountNumberDestination, long amount, String note, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.accountNumberSource = accountNumberSource;
        this.accountNumberDestination = accountNumberDestination;
        this.amount = amount;
        this.note = note;
        this.createdAt = createdAt;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumberSource() {
        return accountNumberSource;
    }

    public void setAccountNumberSource(String accountNumberSource) {
        this.accountNumberSource = accountNumberSource;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
