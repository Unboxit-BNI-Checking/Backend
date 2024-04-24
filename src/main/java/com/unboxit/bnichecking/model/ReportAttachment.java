package com.unboxit.bnichecking.model;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_attachments", indexes = @Index(name = "idx_report_id", columnList = "reportId"))
@EntityListeners(AuditingEntityListener.class)
public class ReportAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportAttachmentId;

    @ManyToOne
    @JoinColumn(name="report_id", referencedColumnName="reportId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Reports reportId;

    @Column(name = "file_path", nullable = false)
    private String filePath;
    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    public long getReportAttachmentId() {
        return reportAttachmentId;
    }

    public void setReportAttachmentId(long reportAttachmentId) {
        this.reportAttachmentId = reportAttachmentId;
    }

    public Reports getReportId() {
        return reportId;
    }

    public void setReportId(Reports reportId) {
        this.reportId = reportId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public ReportAttachment() {
    }
}
