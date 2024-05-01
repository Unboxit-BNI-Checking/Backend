package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ValidateTransactionResponse {
    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_name_destination")
    private String accountNameDestination;

    @JsonProperty("bank_destination")
    private String bankDestination = "BNI";

    @JsonProperty("account_number_destination_status")
    private int accountNumberDestinationStatus;

    @JsonProperty("account_name_source")
    private String accountNameSource;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("fee")
    private long fee = 0;

    @JsonProperty("total_amount")
    private long totalAmount;

    public ValidateTransactionResponse(String accountNumberDestination, String accountNameDestination, String bankDestination, int accountNumberDestinationStatus, String accountNameSource, String accountNumberSource, long amount, long fee, long totalAmount) {
        this.accountNumberDestination = accountNumberDestination;
        this.accountNameDestination = accountNameDestination;
        this.bankDestination = bankDestination;
        this.accountNumberDestinationStatus = accountNumberDestinationStatus;
        this.accountNameSource = accountNameSource;
        this.accountNumberSource = accountNumberSource;
        this.amount = amount;
        this.fee = fee;
        this.totalAmount = totalAmount;
    }
}
