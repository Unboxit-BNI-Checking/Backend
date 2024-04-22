package com.unboxit.bnichecking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
//@Table(name = "ReportedAccount", indexes = {
//        @Index(name = "idx_reported_account_number", columnList = "account_number"),
//})
@EntityListeners(AuditingEntityListener.class)
public class ReportedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportedAccountId;

    @Column(name="time_finished", nullable = false)
    private LocalDateTime time_finished;

    @Column(name="status", nullable = false)
    private int status;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName="account_number")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Account account;

    @OneToMany(mappedBy = "reportedAccount")
    private List<Reports> ownedReports;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Admins admins;
//
//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    private Admins admins;

//    public Admins getAdmins() {
//        return admins;
//    }
//
//    public void setAdmins(Admins admins) {
//        this.admins = admins;
//    }

    public LocalDateTime getTime_finished() {
        return time_finished;
    }

    public void setTime_finished(LocalDateTime time_finished) {
        this.time_finished = time_finished;
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

//    public List<Reports> getOwnedReport() {
//        return ownedReport;
//    }
//
//    public void setOwnedReport(List<Reports> ownedReport) {
//        this.ownedReport = ownedReport;
//    }
//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getReportedAccountId() {
        return reportedAccountId;
    }

    public void setReportedAccountId(long reportedAccountId) {
        this.reportedAccountId = reportedAccountId;
    }
}
