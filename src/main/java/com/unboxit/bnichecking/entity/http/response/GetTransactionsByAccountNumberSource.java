package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetTransactionsByAccountNumberSource {

    @JsonProperty("transaction_id")
    private long transactionId;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_destination_owner_name")
    private String accountDestinationOwnerName;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_source_owner_name")
    private String accountSourceOwnerName;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("transaction_type")
    private String transactionType = "Transfer BNI";

    @JsonProperty("transaction_time")
    private LocalDateTime createdAt;

    @JsonProperty("is_reported")
    private boolean isReported;

    public GetTransactionsByAccountNumberSource(long transactionId, String accountNumberDestination, String accountDestinationOwnerName, String accountNumberSource, String accountSourceOwnerName, long amount, String transactionType, LocalDateTime createdAt, boolean isReported) {
        this.transactionId = transactionId;
        this.accountNumberDestination = accountNumberDestination;
        this.accountDestinationOwnerName = accountDestinationOwnerName;
        this.accountNumberSource = accountNumberSource;
        this.accountSourceOwnerName = accountSourceOwnerName;
        this.amount = amount;
        this.transactionType = transactionType;
        this.createdAt = createdAt;
        this.isReported = isReported;
    }
}
