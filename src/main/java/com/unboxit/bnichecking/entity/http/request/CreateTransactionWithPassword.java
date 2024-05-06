package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unboxit.bnichecking.model.Account;

public class CreateTransactionWithPassword {
    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;
    private long amount;
    private String Note;
    private String password;

    public CreateTransactionWithPassword(String accountNumberSource, String accountNumberDestination, long amount, String note, String password) {
        this.accountNumberSource = accountNumberSource;
        this.accountNumberDestination = accountNumberDestination;
        this.amount = amount;
        Note = note;
        this.password = password;
    }

    public String getAccountNumberSource() {return accountNumberSource;}
    public void setAccountNumberSource(String accountNumberSource) {this.accountNumberSource = accountNumberSource;}
    public String getAccountNumberDestination() {return accountNumberDestination;}
    public void setAccountNumberDestination(String accountNumberDestination) {this.accountNumberDestination = accountNumberDestination;}
    public long getAmount() {return amount;}
    public void setAmount(long amount) {this.amount = amount;}
    public String getNote() {return Note;}
    public void setNote(String note) {Note = note;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
