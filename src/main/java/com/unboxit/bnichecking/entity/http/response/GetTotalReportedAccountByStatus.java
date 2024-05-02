package com.unboxit.bnichecking.entity.http.response;

public class GetTotalReportedAccountByStatus {
    private long status;
    private long jumlah;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }

    public GetTotalReportedAccountByStatus() {
    }
}
