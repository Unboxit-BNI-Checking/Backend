package com.unboxit.bnichecking.model;
//
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "reports", indexes = {
        @Index(name = "idx_transaction_id", columnList = "transaction_id"),
        @Index(name = "idx_reported_account_id", columnList = "reported_account_id")
})
@EntityListeners(AuditingEntityListener.class)
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;

    @Column(name="chronology", nullable = false, columnDefinition = "TEXT")
    private String chronology;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name="transaction_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name="reported_account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ReportedAccount reportedAccount;

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public ReportedAccount getReportedAccount() {
        return reportedAccount;
    }

    public void setReportedAccount(ReportedAccount reportedAccount) {
        this.reportedAccount = reportedAccount;
    }
}
