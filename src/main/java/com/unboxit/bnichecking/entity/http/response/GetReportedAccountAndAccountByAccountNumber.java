package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetReportedAccountAndAccountByAccountNumber {

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_owner_name")
    private String accountOwnerName;

    @JsonProperty("status")
    private String status;

    @JsonProperty("reports_count")
    private long reportsCount;

    @JsonProperty("reports_twitter_count")
    private long reportsTwitterCount;

    public GetReportedAccountAndAccountByAccountNumber(String reportedAccountNumber, String customerName, long status, long reports, long reportsTwitter) {
        this.accountNumber = reportedAccountNumber;
        this.accountOwnerName = customerName;
        if(status == 1){
            this.status = "Aman Bos";
        } else if(status == 2){
            this.status = "Gak Aman Bos";
        }
        this.reportsCount = reports;
        this.reportsTwitterCount = reportsTwitter;
    }

//    @JsonProperty("twitters_count")
//    private long twittersCount;

    public long getReportsCount() {
        return reportsCount;
    }

    public void setReportsCount(long reportsCount) {
        this.reportsCount = reportsCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getReportsTwitterCount() {
        return reportsTwitterCount;
    }

    public void setReportsTwitterCount(long reportsTwitterCount) {
        this.reportsTwitterCount = reportsTwitterCount;
    }
}
