package com.unboxit.bnichecking.entity.http.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccount {
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("customer_name")
    private String customerName;
    private Long balance;

    @JsonProperty(required = false)
    private Boolean blocked = false;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
