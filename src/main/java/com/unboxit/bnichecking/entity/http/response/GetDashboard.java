package com.unboxit.bnichecking.entity.http.response;

public class GetDashboard {
    private long total_laporan;
    private long total_investigate;
    private long total_laporan_sosmed;
    private long avg_waktu_penanganan_laporan;
    private long total_laporan_selesai;

    private long total_laporan_belum_selesai;
    public long getTotal_laporan() {
        return total_laporan;
    }

    public void setTotal_laporan(long total_laporan) {
        this.total_laporan = total_laporan;
    }

    public long getTotal_investigate() {
        return total_investigate;
    }

    public void setTotal_investigate(long total_investigate) {
        this.total_investigate = total_investigate;
    }

    public long getTotal_laporan_sosmed() {
        return total_laporan_sosmed;
    }

    public void setTotal_laporan_sosmed(long total_laporan_sosmed) {
        this.total_laporan_sosmed = total_laporan_sosmed;
    }

    public long getAvg_waktu_penanganan_laporan() {
        return avg_waktu_penanganan_laporan;
    }

    public void setAvg_waktu_penanganan_laporan(long avg_waktu_penanganan_laporan) {
        this.avg_waktu_penanganan_laporan = avg_waktu_penanganan_laporan;
    }

    public long getTotal_laporan_selesai() {
        return total_laporan_selesai;
    }

    public void setTotal_laporan_selesai(long total_laporan_selesai) {
        this.total_laporan_selesai = total_laporan_selesai;
    }

    public long getTotal_laporan_belum_selesai() {
        return total_laporan_belum_selesai;
    }

    public void setTotal_laporan_belum_selesai(long total_laporan_belum_selesai) {
        this.total_laporan_belum_selesai = total_laporan_belum_selesai;
    }

    public GetDashboard() {
    }
}
