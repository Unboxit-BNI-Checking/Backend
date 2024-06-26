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
@Table(name = "twitter_reports", indexes = @Index(name = "idx_tr_reported_account_number", columnList = "reported_account_number"))
@EntityListeners(AuditingEntityListener.class)
public class TwitterReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("twitter_report_id")
    private long twitterReportId;

    @Column(name = "post_date", nullable = false)
    @JsonProperty("post_date")
    private LocalDateTime postDate;

    @Column(name = "twitter_username", nullable = false)
    @JsonProperty("twitter_username")
    private String twitterUsername;

    @Column(name = "tweet_link", nullable = false)
    @JsonProperty("tweet_link")
    private String tweetLink;

    @ManyToOne
    @JoinColumn(name="reported_account_number", referencedColumnName="account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account reportedAccountNumber;

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

    public long getTwitterReportId() {
        return twitterReportId;
    }

    public void setTwitterReportId(long twitterReportId) {
        this.twitterReportId = twitterReportId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getTweetLink() {
        return tweetLink;
    }

    public void setTweetLink(String tweetLink) {
        this.tweetLink = tweetLink;
    }

    public Account getReportedAccountNumber() {
        return reportedAccountNumber;
    }

    public void setReportedAccountNumber(Account reportedAccountNumber) {
        this.reportedAccountNumber = reportedAccountNumber;
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

    public TwitterReport() {
    }

    public TwitterReport(LocalDateTime postDate, String twitterUsername, String tweetLink, Account reportedAccountNumber, LocalDateTime deletedAt) {
        this.postDate = postDate;
        this.twitterUsername = twitterUsername;
        this.tweetLink = tweetLink;
        this.reportedAccountNumber = reportedAccountNumber;
        this.deletedAt = deletedAt;
    }
}
