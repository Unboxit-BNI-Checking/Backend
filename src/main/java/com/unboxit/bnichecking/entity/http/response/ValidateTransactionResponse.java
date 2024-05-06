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
    private long accountNumberDestinationStatus;

    @JsonProperty("account_name_source")
    private String accountNameSource;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("is_favourite")
    private long isFavourite;

    @JsonProperty("is_favourite_description")
    private String isFavouriteDescription;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("note")
    private String note;

    @JsonProperty("fee")
    private long fee = 0;

    @JsonProperty("total_amount")
    private long totalAmount;

    public ValidateTransactionResponse(String accountNumberDestination, String accountNameDestination, String bankDestination, long accountNumberDestinationStatus, String accountNameSource, String accountNumberSource, long isFavourite, String isFavouriteDescription, long amount, String note, long fee, long totalAmount) {
        this.accountNumberDestination = accountNumberDestination;
        this.accountNameDestination = accountNameDestination;
        this.bankDestination = bankDestination;
        this.accountNumberDestinationStatus = accountNumberDestinationStatus;
        this.accountNameSource = accountNameSource;
        this.accountNumberSource = accountNumberSource;
        this.isFavourite = isFavourite;
        this.isFavouriteDescription = isFavouriteDescription;
        this.amount = amount;
        this.note = note;
        this.fee = fee;
        this.totalAmount = totalAmount;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public String getAccountNameDestination() {
        return accountNameDestination;
    }

    public void setAccountNameDestination(String accountNameDestination) {
        this.accountNameDestination = accountNameDestination;
    }

    public String getBankDestination() {
        return bankDestination;
    }

    public void setBankDestination(String bankDestination) {
        this.bankDestination = bankDestination;
    }

    public long getAccountNumberDestinationStatus() {
        return accountNumberDestinationStatus;
    }

    public void setAccountNumberDestinationStatus(long accountNumberDestinationStatus) {
        this.accountNumberDestinationStatus = accountNumberDestinationStatus;
    }

    public String getAccountNameSource() {
        return accountNameSource;
    }

    public void setAccountNameSource(String accountNameSource) {
        this.accountNameSource = accountNameSource;
    }

    public String getAccountNumberSource() {
        return accountNumberSource;
    }

    public void setAccountNumberSource(String accountNumberSource) {
        this.accountNumberSource = accountNumberSource;
    }

    public long getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(long isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getIsFavouriteDescription() {
        return isFavouriteDescription;
    }

    public void setIsFavouriteDescription(String isFavouriteDescription) {
        this.isFavouriteDescription = isFavouriteDescription;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
