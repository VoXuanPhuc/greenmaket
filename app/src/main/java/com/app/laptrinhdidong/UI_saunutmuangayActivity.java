package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI_saunutmuangayActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ChiTietGioHang> chiTietGioHangs;
    TextView textViewTongTienThanhToan;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_saunutmuangay);

        chiTietGioHangs = new ArrayList<>();
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Thông"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Xoài"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Trái Lựu"));

        ChiTietGiohangAdapter chiTietHoaDonAdapter = new ChiTietGiohangAdapter(UI_saunutmuangayActivity.this,R.layout.layout_dongsanpham_giohang,chiTietGioHangs);

        listView  = findViewById(R.id.listViewThanhToan);

        listView.setAdapter(chiTietHoaDonAdapter);
        
        textViewTongTienThanhToan = findViewById(R.id.tongTienThanhToan);
        
        
        
        
        double tongTien = 0;
        for (ChiTietGioHang chiTietGioHang :
                chiTietGioHangs) {
            tongTien += chiTietGioHang.getDonGia()*chiTietGioHang.getSoLuong();
            
        }
        
        textViewTongTienThanhToan.setText(withLargeIntegers(tongTien)+" đ");
        

        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UI_saunutmuangayActivity.this,ChiTietHoaDonActivity.class));
            }
        });
        
    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

}