package com.app.laptrinhdidong.model;


import java.time.Instant;
import java.util.Date;

public class HoaDon {
    private String  id;
    private int tongthanhtoan;
    private int chiphivanchuyen;
    private String ngaythanhtoan;
    private String ngaytao;
    private String trangthai;
    private PhuongThucTT phuongthucTT = new PhuongThucTT();
    private PhuongThucGH phuongthucGH = new PhuongThucGH();
    private KhachHang khachhang = new KhachHang();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
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

    public PhuongThucGH getPhuongThucGH() {
        return phuongthucGH;
    }

    public void setPhuongThucGH(PhuongThucGH phuongthucGH) {
        this.phuongthucGH = phuongthucGH;
    }
}

