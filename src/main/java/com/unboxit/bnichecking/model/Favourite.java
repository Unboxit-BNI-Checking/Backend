package com.unboxit.bnichecking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "favourites", indexes = @Index(name = "idx_account_id", columnList = "account_id"))
@EntityListeners(AuditingEntityListener.class)
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long favouriteId;

    @Column(name="favourite_account_number", nullable = false)
    private String favouriteAccountNumber;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="accountId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;


    public Favourite() {
    }

    public long getFavouriteId() {
        return favouriteId;
    }

    public String getFavouriteAccountNumber() {
        return favouriteAccountNumber;
    }

    public void setFavouriteAccountNumber(String favouriteAccountNumber) {
        this.favouriteAccountNumber = favouriteAccountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
