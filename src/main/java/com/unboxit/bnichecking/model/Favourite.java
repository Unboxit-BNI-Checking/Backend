package com.unboxit.bnichecking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class Favourite implements TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long favouriteId;

    @ManyToOne
    @JoinColumn(name="accountId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @Column(name="favourite_account_number", nullable = false)
    private String favouriteAccountNumber;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="genre")
    private String genre;




    @Override
    public LocalDateTime getCreatedAt() {
        return null;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {

    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return null;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {

    }
}
