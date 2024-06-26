package com.unboxit.bnichecking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "blacklist_twitter_username")
@EntityListeners(AuditingEntityListener.class)
public class BlacklistTwitterUsername {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("blacklist_twitter_username_id")
    private long blacklistTwitterUsernameId;
    @JsonProperty("username")
    private String username;
    @Column(name = "is_blocked", nullable = false)
    @JsonProperty("is_blocked")
    private Boolean isBlocked;

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

    public BlacklistTwitterUsername() {
    }

    public BlacklistTwitterUsername(String username, Boolean isBlocked) {
        this.username = username;
        this.isBlocked = isBlocked;
    }

    public long getBlacklistTwitterUsernameId() {
        return blacklistTwitterUsernameId;
    }

    public void setBlacklistTwitterUsernameId(long blacklistTwitterUsernameId) {
        this.blacklistTwitterUsernameId = blacklistTwitterUsernameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
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
