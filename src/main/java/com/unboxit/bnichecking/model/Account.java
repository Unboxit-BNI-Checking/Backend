package com.unboxit.bnichecking.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts", indexes = @Index(name = "idx_account_number", columnList = "account_number"))
@EntityListeners(AuditingEntityListener.class)
public class Account  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("account_id")
    private long accountId;

    @Column(name="account_number", nullable = true, unique = true, length = 10)
    @JsonProperty("account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("user_id")
    private User user;

    @Column(nullable = false)
    @JsonProperty("balance")
    private Long balance;

    @Column(name="blocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    @JsonProperty("blocked")
    private Boolean blocked;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "favouriteAccount")
    private List<Favourite> ownedFavourites;

    @OneToMany(mappedBy = "reportedAccountNumber")
    private List<ReportedAccount> ownedReportedAccount;

    @OneToMany(mappedBy = "accountNumberSource")
    private List<Transaction> transactionsAsSource;

    @OneToMany(mappedBy = "accountNumberDestination")
    private List<Transaction> transactionsAsDestination;

    public Account() {}

    public Account(String accountNumber, User user, Long balance, Boolean blocked) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.blocked = blocked;
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Long getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
