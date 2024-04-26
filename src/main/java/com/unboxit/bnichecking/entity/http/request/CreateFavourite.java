package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateFavourite {
    @JsonProperty("name")
    private String name;

    @JsonProperty("account_id")
    private long accountId;

    @JsonProperty("favourite_account_number")
    private String favouriteAccountNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFavouriteAccountNumber() {
        return favouriteAccountNumber;
    }

    public void setFavouriteAccountNumber(String favouriteAccountNumber) {
        this.favouriteAccountNumber = favouriteAccountNumber;
    }
}
