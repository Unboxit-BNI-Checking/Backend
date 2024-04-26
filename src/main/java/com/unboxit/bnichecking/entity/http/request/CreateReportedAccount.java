package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReportedAccount {
    @JsonProperty("reported_account_number")
    private String reportedAccountNumber;
    private int status;

    public String getReportedAccountNumber() {
        return reportedAccountNumber;
    }

    public void setReportedAccountNumber(String reportedAccountNumber) {
        this.reportedAccountNumber = reportedAccountNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
