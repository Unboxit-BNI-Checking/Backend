package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CreateReportResponse {
    @JsonProperty("transaction_id")
    private long transactionId;
    @JsonProperty("reported_account_id")
    private long reportedAccountId;
    @Column(columnDefinition = "TEXT")
    private String chronology;
    @JsonProperty("create_at")
    private LocalDateTime createAt;

    public CreateReportResponse(long transactionId, long reportedAccountId, String chronology, LocalDateTime createAt) {
        this.transactionId = transactionId;
        this.reportedAccountId = reportedAccountId;
        this.chronology = chronology;
        this.createAt = createAt;
    }

    public CreateReportResponse() {

    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getReportedAccountId() {
        return reportedAccountId;
    }

    public void setReportedAccountId(long reportedAccountId) {
        this.reportedAccountId = reportedAccountId;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
    }
}
