package com.unboxit.bnichecking.entity.http.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccount {
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("user_id")
    private long userId;
    private Long balance;

    @JsonProperty(required = false)
    private Boolean blocked = false;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
}
