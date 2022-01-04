package com.app.laptrinhdidong.model;

public class YeuThich {
    private String id;
    private KhachHang khachhang;
    private NongSan nongsan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KhachHang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(KhachHang khachhang) {
        this.khachhang = khachhang;
    }

    public NongSan getNongsan() {
        return nongsan;
    }

    public void setNongsan(NongSan nongsan) {
        this.nongsan = nongsan;
    }
}
