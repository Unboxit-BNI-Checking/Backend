package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class GetAllReports {
    @JsonProperty("report_id")
    private long reportId;

    @JsonProperty("transaction_id")
    private long transactionId;

    @Column(columnDefinition = "TEXT")
    private String chronology;

    @JsonProperty("reported_account_id")
    private long reportedAccountId;

    public long getReportedAccountId() {
        return reportedAccountId;
    }

    public void setReportedAccountId(long reported_account_id) {
        this.reportedAccountId = reported_account_id;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
    }
}
