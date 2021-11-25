package com.app.laptrinhdidong.model;

import java.sql.Date;

public class HoaDon {
    int id;
    int tongthanhtoan;
    int chiphivanchuyen;
    Date ngaythanhtoan;
    Date ngaytao;
    String trangthai;
    PhuongThucTT phuongthucTT;
    KhachHang khachhang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTongthanhtoan() {
        return tongthanhtoan;
    }

    public void setTongthanhtoan(int tongthanhtoan) {
        this.tongthanhtoan = tongthanhtoan;
    }

    public int getChiphivanchuyen() {
        return chiphivanchuyen;
    }

    public void setChiphivanchuyen(int chiphivanchuyen) {
        this.chiphivanchuyen = chiphivanchuyen;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public PhuongThucTT getPhuongthucTT() {
        return phuongthucTT;
    }

    public void setPhuongthucTT(PhuongThucTT phuongthucTT) {
        this.phuongthucTT = phuongthucTT;
    }

    public KhachHang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(KhachHang khachhang) {
        this.khachhang = khachhang;
    }
}

