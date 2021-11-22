package com.app.laptrinhdidong.model;

public class DanhMuc {
    int id;
    private String tenDM;

    public int getId() {
        return id;
    }

    String anhDanhMuc;

    public String getAnhDanhMuc() {
        return anhDanhMuc;
    }

    public void setAnhDanhMuc(String anhDanhMuc) {
        this.anhDanhMuc = anhDanhMuc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
}
