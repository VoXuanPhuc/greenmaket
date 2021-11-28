package com.app.laptrinhdidong.model;

public class ItemGioHang {
    private int id;
    private int soLuong;
    public ItemGioHang(){}
    public ItemGioHang(int id, int soLuong) {
        this.id = id;
        this.soLuong = soLuong;
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
