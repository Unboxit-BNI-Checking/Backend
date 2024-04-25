package com.unboxit.bnichecking.model;


import jakarta.persistence.*;
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
    private long accountId;

    @Column(name="account_number", nullable = false, unique = true, length = 10)
    private String accountNumber;

    @Column(name="customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Long balance;

    @Column(name="blocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean blocked;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "account")
    private List<Favourite> ownedFavourites;

    @OneToMany(mappedBy = "reportedAccountNumber")
    private List<ReportedAccount> ownedReportedAccount;

    @OneToMany(mappedBy = "accountNumberSource")
    private List<Transaction> transactionsAsSource;

    @OneToMany(mappedBy = "accountNumberDestination")
    private List<Transaction> transactionsAsDestination;

    public Account() {}

    public Account(String accountNumber, String customerName, Long balance, Boolean blocked) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
        this.blocked = blocked;
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

//    public List<Favourite> getOwnedFavourites() {
//        return ownedFavourites;
//    }
//
//    public void setOwnedFavourites(List<Favourite> ownedFavourites) {
//        this.ownedFavourites = ownedFavourites;
//    }



//    public Account() {}

//    public Account(@NonNull String userName, String userAddress, String userPhoneNumber, String userBio) {
//        this.userName = userName;
//        this.userAddress = userAddress;
//        this.userPhoneNumber = userPhoneNumber;
//        this.userBio = userBio;
//    }

}
