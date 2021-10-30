package com.app.laptrinhdidong;

public class ChiTietGioHang {

    double donGia;
    int soLuong;
    String TenSP;


    public ChiTietGioHang(double donGia, int soLuong, String tenSP) {
        this.donGia = donGia;
        this.soLuong = soLuong;
        TenSP = tenSP;
    }


    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }
}
