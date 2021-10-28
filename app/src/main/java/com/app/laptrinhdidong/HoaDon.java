package com.app.laptrinhdidong;

public class HoaDon {

    String maHoaDon,ngay,thanhTien,trangThai;

    public HoaDon(String maHoaDon, String ngay, String thanhTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngay = ngay;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
