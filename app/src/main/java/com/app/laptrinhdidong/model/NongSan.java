package com.app.laptrinhdidong.model;

import androidx.annotation.NonNull;

public class NongSan {
    int id;
    String tenNS;
    int gia;
    int soluongNhap;
    int soluongCon;
    String noiSanXuat;
    String moTaNS;
    DanhMuc danhmuc;
    nhacc nhacc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluongNhap() {
        return soluongNhap;
    }

    public void setSoluongNhap(int soluongNhap) {
        this.soluongNhap = soluongNhap;
    }

    public int getSoluongCon() {
        return soluongCon;
    }

    public void setSoluongCon(int soluongCon) {
        this.soluongCon = soluongCon;
    }

    public String getNoiSanXuat() {
        return noiSanXuat;
    }

    public void setNoiSanXuat(String noiSanXuat) {
        this.noiSanXuat = noiSanXuat;
    }

    public String getMoTaNS() {
        return moTaNS;
    }

    public void setMoTaNS(String moTaNS) {
        this.moTaNS = moTaNS;
    }

    public DanhMuc getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(DanhMuc danhmuc) {
        this.danhmuc = danhmuc;
    }

    public com.app.laptrinhdidong.model.nhacc getNhacc() {
        return nhacc;
    }

    public void setNhacc(com.app.laptrinhdidong.model.nhacc nhacc) {
        this.nhacc = nhacc;
    }

}
