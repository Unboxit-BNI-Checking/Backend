package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class CreateTransaction {
    @JsonProperty("account_number_source")
    private String accountNumberSource;

    @JsonProperty("account_number_destination")
    private String accountNumberDestination;
    private long amount;
    private String Note;
    private String name;
    @JsonProperty("is_favourite")
    private boolean isFavourite;

    public String getAccountNumberSource() {return accountNumberSource;}
    public void setAccountNumberSource(String accountNumberSource) {this.accountNumberSource = accountNumberSource;}
    public String getAccountNumberDestination() {return accountNumberDestination;}
    public void setAccountNumberDestination(String accountNumberDestination) {this.accountNumberDestination = accountNumberDestination;}
    public long getAmount() {return amount;}
    public void setAmount(long amount) {this.amount = amount;}
    public String getNote() {return Note;}
    public void setNote(String note) {Note = note;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public boolean isFavourite() {return isFavourite;}
    public void setFavourite(boolean favourite) {isFavourite = favourite;}
}
