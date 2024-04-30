package com.unboxit.bnichecking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("favuorite_id")
    private long favouriteId;

    @ManyToOne
    @JoinColumn(name="favourite_account_number", referencedColumnName="account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("favourite_account")
    private Account favouriteAccount;

    @Column(name="name", nullable = false)
    @JsonProperty("name")
    private String name;

    @ManyToOne
    @JoinColumn(name="account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("account_id")
    private Account account;

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


    public Favourite() {
    }

    public Favourite(Account favouriteAccount, String name, Account account) {
        this.favouriteAccount = favouriteAccount;
        this.name = name;
        this.account = account;
    }

    public long getFavouriteId() {
        return favouriteId;
    }

    public Account getFavouriteAccount() {
        return favouriteAccount;
    }

    public void setFavouriteAccount(Account favouriteAccount) {
        this.favouriteAccount = favouriteAccount;
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
