package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.ReportAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportAttachmentJpaRepository extends JpaRepository<ReportAttachment, Long> {
    @Query(value="SELECT * FROM report_attachments WHERE report_id=?1", nativeQuery = true)
    List<ReportAttachment> findByReportId(Long reportId);

    ReportAttachment findByReportAttachmentId(Long reportAttachmentId);
}
