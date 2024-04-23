package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.ReportAttachment;
import com.unboxit.bnichecking.repository.ReportAttachmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportAttachmentService {
    @Autowired
    private ReportAttachmentJpaRepository repository;

    public List<ReportAttachment> getAllReportAttachment(){
        return repository.findAll();
    }

    public List<ReportAttachment> findReportAttachmentByReportId(Long report_id){
        return repository.findByReportId(report_id);
    }
}
