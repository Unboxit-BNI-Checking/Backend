package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;

import java.time.LocalDateTime;

public class GetMyAccount {
    @JsonProperty("account_id")
    private long accountId;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("balance")
    private Long balance;

    @JsonProperty("blocked")
    private Boolean blocked;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    public GetMyAccount(Account account) {
        User user = account.getUserId();
        this.accountId = account.getAccountId();
        this.accountNumber = account.getAccountNumber();
        this.userId = user.getUserId();
        this.customerName = user.getCustomerName();
        this.balance = account.getBalance();
        this.blocked = account.getBlocked();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
        this.deletedAt = account.getDeletedAt();
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
