package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetAllReportedAccount {
    @JsonProperty("reported_account_id")
    private long reportAccountId;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("reports_count")
    private long reportsCount;

    @JsonProperty("status")
    private long status;

    @JsonProperty("admin")
    private String admin;

    @JsonProperty("time_finished")
    private LocalDateTime timeFinished;

    public long getReportAccountId() {
        return reportAccountId;
    }

    public void setReportAccountId(long reportAccountId) {
        this.reportAccountId = reportAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getReportsCount() {
        return reportsCount;
    }

    public void setReportsCount(long reportsCount) {
        this.reportsCount = reportsCount;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public LocalDateTime getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(LocalDateTime timeFinished) {
        this.timeFinished = timeFinished;
    }
}
