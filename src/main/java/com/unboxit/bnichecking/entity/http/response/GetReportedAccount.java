package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GetReportedAccount {
    @JsonProperty("reported_account_id")
    private long reportedAccountId;

    @JsonProperty("reported_account_number")
    private String reportedAccountNumber;

    @JsonProperty("time_finished")
    private LocalDateTime time_finished;

    private int status;
    private LocalDateTime createdAt;

    public GetReportedAccount(){

    }
    public GetReportedAccount(long reportedAccountId, String reportedAccountNumber, LocalDateTime timeFinished, int status, LocalDateTime createdAt) {
        this.reportedAccountId = reportedAccountId;
        this.reportedAccountNumber = reportedAccountNumber;
        this.time_finished = timeFinished;
        this.status = status;
        this.createdAt = createdAt;
    }

    public long getReportedAccountId() {
        return reportedAccountId;
    }

    public void setReportedAccountId(long reportedAccountId) {
        this.reportedAccountId = reportedAccountId;
    }

    public String getReportedAccountNumber() {
        return reportedAccountNumber;
    }

    public void setReportedAccountNumber(String reportedAccountNumber) {
        this.reportedAccountNumber = reportedAccountNumber;
    }

    public LocalDateTime getTime_finished() {
        return time_finished;
    }

    public void setTime_finished(LocalDateTime time_finished) {
        this.time_finished = time_finished;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
