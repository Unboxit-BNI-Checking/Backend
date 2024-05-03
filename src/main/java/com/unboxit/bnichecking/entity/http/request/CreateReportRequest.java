package com.unboxit.bnichecking.entity.http.request;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class CreateReportRequest {
    private Long transactionId;
    private String chronology;
    private List<MultipartFile> files;

    // getters and setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getChronology() {
        return chronology;
    }

    public void setChronology(String chronology) {
        this.chronology = chronology;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
