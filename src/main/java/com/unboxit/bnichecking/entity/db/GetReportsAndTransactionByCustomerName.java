package com.unboxit.bnichecking.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class GetReportsAndTransactionByCustomerName {
    @JsonProperty("reports_id")
    private long reportsId;

    @JsonProperty("created_at_reports")
    private LocalDateTime createdAtReports;

    @Column(columnDefinition = "TEXT")
    private String chronology;

    @JsonProperty("status")
    private long status;

    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_source_username")
    private String accountNumberSourceUsername;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;

    @JsonProperty("account_number_destination_username")
    private String accountNumberDestinationUsername;

    private long amount;

    @JsonProperty("created_at_transaction")
    private LocalDateTime createdAtTransaction;

    public String getAccountNumberSourceUsername() {
        return accountNumberSourceUsername;
    }

    public void setAccountNumberSourceUsername(String accountNumberSourceUsername) {
        this.accountNumberSourceUsername = accountNumberSourceUsername;
    }

    public String getAccountNumberDestinationUsername() {
        return accountNumberDestinationUsername;
    }

    public void setAccountNumberDestinationUsername(String accountNumberDestinationUsername) {
        this.accountNumberDestinationUsername = accountNumberDestinationUsername;
    }

    public long getReportsId() {
        return reportsId;
    }

    public void setReportsId(long reportsId) {
        this.reportsId = reportsId;
    }

    public LocalDateTime getCreatedAtReports() {
        return createdAtReports;
    }

    public void setCreatedAtReports(LocalDateTime createdAtReports) {
        this.createdAtReports = createdAtReports;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getAccountNumberSource() {
        return accountNumberSource;
    }

    public void setAccountNumberSource(String accountNumberSource) {
        this.accountNumberSource = accountNumberSource;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAtTransaction() {
        return createdAtTransaction;
    }

    public void setCreatedAtTransaction(LocalDateTime createdAtTransaction) {
        this.createdAtTransaction = createdAtTransaction;
    }
}
