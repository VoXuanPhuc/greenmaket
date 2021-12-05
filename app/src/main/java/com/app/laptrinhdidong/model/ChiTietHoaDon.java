package com.app.laptrinhdidong.model;

import java.util.ArrayList;

public class ChiTietHoaDon {
    String id;
    int gia;
    int soluong;
    NongSan nongsan;
    HoaDon hoadon;

    public NongSan getNongsan() {
        return nongsan;
    }

    public void setNongsan(NongSan nongsan) {
        this.nongsan = nongsan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }



    public HoaDon getHoadon() {
        return hoadon;
    }

    public void setHoadon(HoaDon hoadon) {
        this.hoadon = hoadon;
    }
}
