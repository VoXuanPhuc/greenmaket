package com.app.laptrinhdidong.model;

public class ItemGioHang {
    private int id;
    private int soLuong;
    private String url;
    private  int gia;

    public ItemGioHang(int id, int soLuong, String url, int gia) {
        this.id = id;
        this.soLuong = soLuong;
        this.url = url;
        this.gia = gia;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public ItemGioHang(){}
    public ItemGioHang(int id, int soLuong) {
        this.id = id;
        this.soLuong = soLuong;
    }

    public ItemGioHang(int id, int soLuong, String url) {
        this.id = id;
        this.soLuong = soLuong;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
