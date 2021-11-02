package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    ArrayList<ChiTietGioHang> chiTietGioHangs;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        chiTietGioHangs = new ArrayList<>();


        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Thông"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Xoài"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));

        ChiTietGiohangAdapter chiTietHoaDonAdapter = new ChiTietGiohangAdapter(GioHangActivity.this,R.layout.layout_dongsanpham_giohang,chiTietGioHangs);
        DongTinhTienGioHangAdapter dongTinhTienGioHangAdapter = new DongTinhTienGioHangAdapter(GioHangActivity.this,R.layout.layout_dongtinhtien_giohang,chiTietGioHangs);

        listView  = findViewById(R.id.listViewSAnPhamGioHang);

        listView.setAdapter(chiTietHoaDonAdapter);



    }
}