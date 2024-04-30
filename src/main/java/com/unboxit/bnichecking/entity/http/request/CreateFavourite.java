package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateFavourite {
    @JsonProperty("name")
    private String name;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("favourite_account_number")
    private String favouriteAccountNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFavouriteAccountNumber() {
        return favouriteAccountNumber;
    }

    public void setFavouriteAccountNumber(String favouriteAccountNumber) {
        this.favouriteAccountNumber = favouriteAccountNumber;
    }
}
