package com.unboxit.bnichecking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "admins")
@EntityListeners(AuditingEntityListener.class)
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("admin_id")
    private long adminId;
    @Column(nullable = false)
    @JsonProperty("username")
    private String username;
    @Column(name = "hashed_password", nullable = false)
    @JsonProperty("hashed_password")
    private String hashedPassword;
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
    @OneToMany(mappedBy = "admins")
    @JsonProperty("owned_reported_account")
    private List<ReportedAccount> ownedReportedAccount;

    public Admins() {
    }

    public Admins(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public List<ReportedAccount> getOwnedReportedAccount() {
        return ownedReportedAccount;
    }

    public void setOwnedReportedAccount(List<ReportedAccount> ownedReportedAccount) {
        this.ownedReportedAccount = ownedReportedAccount;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
}
