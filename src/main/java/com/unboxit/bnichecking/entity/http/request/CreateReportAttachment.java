package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateReportAttachment {
    @JsonProperty("report_id")
    private String reportId;
    @JsonProperty("file_path")
    private String filePath;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
