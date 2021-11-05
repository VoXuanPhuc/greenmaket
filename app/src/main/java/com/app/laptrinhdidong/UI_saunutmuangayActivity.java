package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.laptrinhdidong.alladapter.ChiTietGiohangAdapter;
import com.app.laptrinhdidong.allclass.ChiTietGioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI_saunutmuangayActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ChiTietGioHang> chiTietGioHangs;
    TextView textViewTongTienThanhToan;
    Button btnThanhToan;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_saunutmuangay);

        bottomNavigationView  = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.card);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(UI_saunutmuangayActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(UI_saunutmuangayActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });




        chiTietGioHangs = new ArrayList<>();
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Dưa hấu Bắc Mỹ",R.drawable.dualeo_image));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Chuối ngự",R.drawable.bananas));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Cam Vinh",R.drawable.oranges));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Nho Bình Thuận",R.drawable.grape));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Táo Cao Bằng",R.drawable.apple));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Dứa Bình Định",R.drawable.pineapple));

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