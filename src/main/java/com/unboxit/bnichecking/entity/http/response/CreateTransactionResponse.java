package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CreateTransactionResponse {
    @JsonProperty("transaction_id")
    private long transactionId;

    @JsonProperty("is_transaction_success")
    private boolean transactionSuccess;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_name_destination")
    private String accountNameDestination;

    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;

    @JsonProperty("email")
    private String email;

    @JsonProperty("bank_destination")
    private String bankDestination = "BNI";

    @JsonProperty("account_number_destination_status")
    private int accountNumberDestinationStatus;

    @JsonProperty("account_name_source")
    private String accountNameSource;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("note")
    private String note;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("fee")
    private long fee = 0;

    @JsonProperty("total_amount")
    private long totalAmount;

    public CreateTransactionResponse(long transactionId, boolean transactionSuccess, String accountNumberDestination, String accountNameDestination, LocalDateTime transactionTime, String email, String bankDestination, int accountNumberDestinationStatus, String accountNameSource, String accountNumberSource, String note, long amount, long fee, long totalAmount) {
        this.transactionId = transactionId;
        this.transactionSuccess = transactionSuccess;
        this.accountNumberDestination = accountNumberDestination;
        this.accountNameDestination = accountNameDestination;
        this.transactionTime = transactionTime;
        this.email = email;
        this.bankDestination = bankDestination;
        this.accountNumberDestinationStatus = accountNumberDestinationStatus;
        this.accountNameSource = accountNameSource;
        this.accountNumberSource = accountNumberSource;
        this.note = note;
        this.amount = amount;
        this.fee = fee;
        this.totalAmount = totalAmount;
    }
}
