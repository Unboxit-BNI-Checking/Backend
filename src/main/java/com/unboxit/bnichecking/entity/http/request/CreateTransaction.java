package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CreateTransaction {
    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;
    private long amount;
    private String Note;

    public String getAccountNumberSource() {return accountNumberSource;}
    public void setAccountNumberSource(String accountNumberSource) {this.accountNumberSource = accountNumberSource;}
    public String getAccountNumberDestination() {return accountNumberDestination;}
    public void setAccountNumberDestination(String accountNumberDestination) {this.accountNumberDestination = accountNumberDestination;}
    public long getAmount() {return amount;}
    public void setAmount(long amount) {this.amount = amount;}
    public String getNote() {return Note;}
    public void setNote(String note) {Note = note;}
}
