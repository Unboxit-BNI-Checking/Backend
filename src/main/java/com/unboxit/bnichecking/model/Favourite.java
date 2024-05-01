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
@Table(name = "favourites", indexes = @Index(name = "idx_user_id", columnList = "user_id"))
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
    @JoinColumn(name="user_id", referencedColumnName="userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("user_id")
    private User userId;

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

    public Favourite(Account favouriteAccount, String name, User user) {
        this.favouriteAccount = favouriteAccount;
        this.name = name;
        this.userId = user;
    }

    public void setFavouriteId(long favouriteId) {
        this.favouriteId = favouriteId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    public User getAccount() {
        return userId;
    }

    public void setAccount(User userId) {
        this.userId = userId;
    }

}
