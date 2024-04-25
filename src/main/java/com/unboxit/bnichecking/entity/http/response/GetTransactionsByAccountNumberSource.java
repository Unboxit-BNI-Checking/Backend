package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetTransactionsByAccountNumberSource {

    @JsonProperty("transaction_Id")
    private long transactionId;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_owner_name")
    private String accountOwnerName;
    private long amount;

    @JsonProperty("transaction_type")
    private String transactionType = "Transfer BNI";

    @JsonProperty("transaction_time")
    private LocalDateTime createdAt;

    public GetTransactionsByAccountNumberSource(long transactionId, String accountNumberDestination, String accountOwnerName, long amount, String transactionType, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.accountNumberDestination = accountNumberDestination;
        this.accountOwnerName = accountOwnerName;
        this.amount = amount;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
    }
}
