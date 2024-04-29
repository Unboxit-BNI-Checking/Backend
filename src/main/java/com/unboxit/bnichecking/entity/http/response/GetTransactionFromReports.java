package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionFromReports {
    @JsonProperty("transaction_Id")
    private Long transactionId;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    public GetTransactionFromReports(Long transactionId, String accountNumberDestination) {
        this.transactionId = transactionId;
        this.accountNumberDestination = accountNumberDestination;
    }

    public GetTransactionFromReports() {

    }

    // Buat getter-setter sesuai kebutuhan
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }
}
