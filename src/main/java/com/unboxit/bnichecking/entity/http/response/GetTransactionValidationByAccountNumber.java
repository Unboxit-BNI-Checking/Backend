package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionValidationByAccountNumber {
    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_destination_owner_name")
    private String accountDestinationOwnerName;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_source_owner_name")
    private String accountSourceOwnerName;

    @JsonProperty("status")
    private long status;

    public GetTransactionValidationByAccountNumber(String accountNumberDestination, String accountDestinationOwnerName, String accountNumberSource, String accountSourceOwnerName, long status) {
        this.accountNumberDestination = accountNumberDestination;
        this.accountDestinationOwnerName = accountDestinationOwnerName;
        this.accountNumberSource = accountNumberSource;
        this.accountSourceOwnerName = accountSourceOwnerName;
        this.status = status;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public String getAccountDestinationOwnerName() {
        return accountDestinationOwnerName;
    }

    public void setAccountDestinationOwnerName(String accountDestinationOwnerName) {
        this.accountDestinationOwnerName = accountDestinationOwnerName;
    }

    public String getAccountNumberSource() {
        return accountNumberSource;
    }

    public void setAccountNumberSource(String accountNumberSource) {
        this.accountNumberSource = accountNumberSource;
    }

    public String getAccountSourceOwnerName() {
        return accountSourceOwnerName;
    }

    public void setAccountSourceOwnerName(String accountSourceOwnerName) {
        this.accountSourceOwnerName = accountSourceOwnerName;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
