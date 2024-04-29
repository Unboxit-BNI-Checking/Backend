package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReportAttachment {
    @JsonProperty("report_id")
    private long reportId;
    @JsonProperty("file_path")
    private String filePath;

    public CreateReportAttachment(long reportId, String filePath) {
        this.reportId = reportId;
        this.filePath = filePath;
    }

    public CreateReportAttachment() {

    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
