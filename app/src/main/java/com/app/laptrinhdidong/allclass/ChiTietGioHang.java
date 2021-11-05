package com.app.laptrinhdidong.allclass;

public class ChiTietGioHang {

    double donGia;
    int soLuong;
    String TenSP;
    int image;


    public ChiTietGioHang(double donGia, int soLuong, String tenSP,int image) {
        this.donGia = donGia;
        this.soLuong = soLuong;
        TenSP = tenSP;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
