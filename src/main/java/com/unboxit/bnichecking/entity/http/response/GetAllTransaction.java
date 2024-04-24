package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetAllTransaction {
    @JsonProperty("transaction_Id")
    private long transactionId;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;
    private long amount;
    private String Note;
    private LocalDateTime createdAt;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumberSource() {return accountNumberSource;}
    public void setAccountNumberSource(String accountNumberSource) {this.accountNumberSource = accountNumberSource;}
    public String getAccountNumberDestination() {return accountNumberDestination;}
    public void setAccountNumberDestination(String accountNumberDestination) {this.accountNumberDestination = accountNumberDestination;}
    public long getAmount() {return amount;}
    public void setAmount(long amount) {this.amount = amount;}
    public String getNote() {return Note;}
    public void setNote(String note) {Note = note;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
