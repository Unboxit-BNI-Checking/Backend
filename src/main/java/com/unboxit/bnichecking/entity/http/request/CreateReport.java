package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class CreateReport {
    @JsonProperty("transaction_id")
    private long transactionId;
    @Column(columnDefinition = "TEXT")
    private String chrolonogy;

    public CreateReport(long transactionId, String chrolonogy) {
        this.transactionId = transactionId;
        this.chrolonogy = chrolonogy;
    }

    public CreateReport() {

    }
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    public String getChrolonogy() {
        return chrolonogy;
    }

    public void setChrolonogy(String chrolonogy) {
        this.chrolonogy = chrolonogy;
    }
}
