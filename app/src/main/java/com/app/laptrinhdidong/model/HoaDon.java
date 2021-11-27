package com.app.laptrinhdidong.model;


import java.util.Date;

public class HoaDon {
    private int id;
    private int tongthanhtoan;
    private int chiphivanchuyen;
    private Date ngaythanhtoan;
    private Date ngaytao;
    private String trangthai;
    private PhuongThucTT phuongthucTT;
    private PhuongThucGH phuongthucGH;
    private KhachHang khachhang;

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

    public PhuongThucGH getPhuongThucGH() {
        return phuongthucGH;
    }

    public void setPhuongThucGH(PhuongThucGH phuongthucGH) {
        this.phuongthucGH = phuongthucGH;
    }
}

