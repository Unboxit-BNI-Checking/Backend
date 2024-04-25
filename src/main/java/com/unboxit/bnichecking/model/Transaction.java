package com.unboxit.bnichecking.model;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", indexes = {@Index(name = "idx_account_number_source", columnList = "account_number_source"),@Index(name = "idx_account_number_destination", columnList = "account_number_destination") })
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @ManyToOne
    @JoinColumn(name="account_number_source", referencedColumnName="account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account accountNumberSource;

    @ManyToOne
    @JoinColumn(name="account_number_destination", referencedColumnName="account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account accountNumberDestination;

    @Column(nullable = false)
    private long amount;
    @Column(columnDefinition = "TEXT")
    private String note;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "transaction")
    private Reports reports;

    public Transaction(){}
    
    public Transaction(Account accountNumberSource, Account accountNumberDestination, long amount, String note) {
        this.accountNumberSource = accountNumberSource;
        this.accountNumberDestination = accountNumberDestination;
        this.amount = amount;
        this.note = note;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccountNumberSource() {
        return accountNumberSource;
    }

    public void setAccountNumberSource(Account accountNumberSource) {
        this.accountNumberSource = accountNumberSource;
    }

    public Account getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(Account accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
