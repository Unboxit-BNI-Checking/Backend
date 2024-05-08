package com.unboxit.bnichecking.entity.http.response;

public class GetTotalReportCompleted {
    private int bulan;
    private int jumlah;

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public GetTotalReportCompleted() {
    }
}
