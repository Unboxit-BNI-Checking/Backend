package com.unboxit.bnichecking.model;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements TimestampedEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Column(name="account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name="customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String balance;

    @Column(name="is_blocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isBlocked;

    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "account")
    private List<Favourite> ownedFavourites;

    public Account(long accountId, String accountNumber, String customerName, String balance, boolean isBlocked) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
        this.isBlocked = isBlocked;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Favourite> getOwnedFavourites() {
        return ownedFavourites;
    }

    public void setOwnedFavourites(List<Favourite> ownedFavourites) {
        this.ownedFavourites = ownedFavourites;
    }



//    public Account() {}

//    public Account(@NonNull String userName, String userAddress, String userPhoneNumber, String userBio) {
//        this.userName = userName;
//        this.userAddress = userAddress;
//        this.userPhoneNumber = userPhoneNumber;
//        this.userBio = userBio;
//    }

}
