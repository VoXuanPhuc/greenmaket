package com.app.laptrinhdidong;

import java.util.Date;

public class nongsanClass {

    private String ten;
    private danhmucClass danhmuc;
    private String mieuTa;
    private Date ngayNhap;
    private String diaChiSx;
    private Double gia;
    private Integer donvi;
    private Integer hinh;

    public nongsanClass(String ten, danhmucClass danhmuc, String mieuTa,
                        Double gia, Integer donvi, Integer hinh) {
        this.ten = ten;
        this.danhmuc = danhmuc;
        this.mieuTa = mieuTa;
        this.gia = gia;
        this.donvi = donvi;
        this.hinh = hinh;
    }

    public nongsanClass(String ten, danhmucClass danhmuc, String mieuTa,
                        Date ngayNhap, String diaChiSx, Double gia, Integer donvi, Integer hinh) {
        this.ten = ten;
        this.danhmuc = danhmuc;
        this.mieuTa = mieuTa;
        this.ngayNhap = ngayNhap;
        this.diaChiSx = diaChiSx;
        this.gia = gia;
        this.donvi = donvi;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public danhmucClass getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(danhmucClass danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getDiaChiSx() {
        return diaChiSx;
    }

    public void setDiaChiSx(String diaChiSx) {
        this.diaChiSx = diaChiSx;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getDonvi() {
        return donvi;
    }

    public void setDonvi(Integer donvi) {
        this.donvi = donvi;
    }

    public Integer getHinh() {
        return hinh;
    }

    public void setHinh(Integer hinh) {
        this.hinh = hinh;
    }
}
