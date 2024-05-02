package com.unboxit.bnichecking.entity.http.response;

public class GetTotalReportCompleted {
    private String bulan;
    private long jumlah;

    public String getBulan(String s) {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }

    public GetTotalReportCompleted() {
    }
}
