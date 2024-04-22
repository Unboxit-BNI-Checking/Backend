package com.unboxit.bnichecking.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@EntityListeners(AuditingEntityListener.class)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
    @Column(nullable = false)
    private String username;
    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;
    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
}
