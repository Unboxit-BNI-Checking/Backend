package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.GetAllReportAttachments;
import com.unboxit.bnichecking.model.ReportAttachment;
import com.unboxit.bnichecking.repository.ReportAttachmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportAttachmentService {
    @Autowired
    private ReportAttachmentJpaRepository repository;

    public List<ReportAttachment> getAllReportAttachment(){
        return repository.findAll();
    }

    public ReportAttachment findReportAttachmentByReportAttachmentId(Long report_attachment_id){
        return repository.findByReportAttachmentId(report_attachment_id);
    }

    public List<GetAllReportAttachments> findReportAttachmentByReportId(Long report_id){
        List<GetAllReportAttachments> result = new ArrayList<>();
        List<ReportAttachment> attachments = repository.findByReportId(report_id);
        for(ReportAttachment attachment : attachments){
            GetAllReportAttachments getAllReportAttachments = new GetAllReportAttachments();
            getAllReportAttachments.setReportAttachmentId(attachment.getReportAttachmentId());
            getAllReportAttachments.setReportId(attachment.getReportId().getReportId());
            getAllReportAttachments.setFilePath(attachment.getFilePath());
            getAllReportAttachments.setCreatedAt(attachment.getCreatedAt());
            result.add(getAllReportAttachments);
        }
        return result;
    }

    public ReportAttachment createReportAttachment(ReportAttachment reportAttachment){
        return repository.save(reportAttachment);
    }
}
