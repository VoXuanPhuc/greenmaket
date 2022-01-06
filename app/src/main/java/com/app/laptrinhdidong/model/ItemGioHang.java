package com.app.laptrinhdidong.model;

public class ItemGioHang {
    private String id;
    private int soLuong;
    private String url;
    private  int gia;
    private String tenNS;

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

    public ItemGioHang(){}


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
