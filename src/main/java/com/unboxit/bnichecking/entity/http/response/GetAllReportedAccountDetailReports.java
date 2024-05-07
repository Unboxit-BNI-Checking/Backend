package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class GetAllReportedAccountDetailReports {

    @JsonProperty("account_number_reported")
    private String accountNumberReported;

    @JsonProperty("account_username_reported")
    private String accountUsernameReported;

    @JsonProperty("status")
    private long status;

    @JsonProperty("reported_id")
    private long reportId;

    @JsonProperty("reports_created_at")
    private LocalDateTime reportsCreatedAt;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_username")
    private String accountUsername;

    @JsonProperty("transaction_created_at")
    private LocalDateTime transactionCreatedAt;

    @JsonProperty("transaction_note")
    private String transactionNote;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("chronology")
    @Column(columnDefinition = "TEXT")
    private String chronology;

    @JsonProperty("attachment")
    private List<String> attachment;

    @JsonProperty("twitter_reports_count")
    private long twitterReportsCount;

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public String getAccountNumberReported() {
        return accountNumberReported;
    }

    public void setAccountNumberReported(String accountNumberReported) {
        this.accountNumberReported = accountNumberReported;
    }

    public String getAccountUsernameReported() {
        return accountUsernameReported;
    }

    public void setAccountUsernameReported(String accountUsernameReported) {
        this.accountUsernameReported = accountUsernameReported;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public LocalDateTime getReportsCreatedAt() {
        return reportsCreatedAt;
    }

    public void setReportsCreatedAt(LocalDateTime reportsCreatedAt) {
        this.reportsCreatedAt = reportsCreatedAt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public LocalDateTime getTransactionCreatedAt() {
        return transactionCreatedAt;
    }

    public void setTransactionCreatedAt(LocalDateTime transactionCreatedAt) {
        this.transactionCreatedAt = transactionCreatedAt;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
    }

    public List<String> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<String> attachment) {
        this.attachment = attachment;
    }

    public long getTwitterReportsCount() {
        return twitterReportsCount;
    }

    public void setTwitterReportsCount(long twitterReportsCount) {
        this.twitterReportsCount = twitterReportsCount;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }
}
