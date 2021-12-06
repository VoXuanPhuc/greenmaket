package com.app.laptrinhdidong.model;

import java.io.Serializable;
import java.util.Date;

public class KhachHang {

    private String id;
    private String hoTenKH;
    private String tenDangNhap;
    private String matkhau;
    private String email;
    private String sdt;
    private String ngaySinh;
    private String gioitinh;
    private String chitietdiachi;
    private XaPhuong xa;

    public KhachHang() {
    }

    public KhachHang(String id, String hoTenKH, String tenDangNhap, String matkhau,
                     String email, String sdt, String ngaysinh, String gioitinh,
                     String chitietdiachi, XaPhuong diaChi) {
        this.id = id;
        this.hoTenKH = hoTenKH;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.email = email;
        this.sdt = sdt;
        this.ngaySinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.chitietdiachi = chitietdiachi;
        this.xa = diaChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaysinh() {
        return ngaySinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaySinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getChitietdiachi() {
        return chitietdiachi;
    }

    public void setChitietdiachi(String chitietdiachi) {
        this.chitietdiachi = chitietdiachi;
    }

    public XaPhuong getDiaChi() {
        return xa;
    }

    public void setDiaChi(XaPhuong diaChi) {
        this.xa = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "id=" + id +
                ", hoTenKH='" + hoTenKH + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioitinh='" + gioitinh + '\'' +
                ", chitietdiachi='" + chitietdiachi + '\'' +
                ", xa=" + xa +
                '}';
    }
}
