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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    ArrayList<ChiTietGioHang> chiTietGioHangs;
    ListView listView;
    Button buttonThanhToan;
    TextView textViewTongTienhang;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        bottomNavigationView  = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.card);

        buttonThanhToan = findViewById(R.id.button);
        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHangActivity.this,UI_saunutmuangayActivity.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(GioHangActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(GioHangActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(GioHangActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(GioHangActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });




        chiTietGioHangs = new ArrayList<>();


        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Dưa hấu Bắc Mỹ",R.drawable.duahau));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Chuối ngự",R.drawable.bananas));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Cam Vinh",R.drawable.oranges));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Nho Bình Thuận",R.drawable.grape));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Táo Cao Bằng",R.drawable.apple));
        chiTietGioHangs.add(new ChiTietGioHang(15000,3,"Dứa Bình Định",R.drawable.pineapple));

        ChiTietGiohangAdapter chiTietHoaDonAdapter = new ChiTietGiohangAdapter(GioHangActivity.this,R.layout.layout_dongsanpham_giohang,chiTietGioHangs);
        DongTinhTienGioHangAdapter dongTinhTienGioHangAdapter = new DongTinhTienGioHangAdapter(GioHangActivity.this,R.layout.layout_dongtinhtien_giohang,chiTietGioHangs);

        listView  = findViewById(R.id.listViewSAnPhamGioHang);

        listView.setAdapter(chiTietHoaDonAdapter);

        textViewTongTienhang = findViewById(R.id.textView10);
        double tongTienhang = 0;

        for (ChiTietGioHang chiTietGioHang :
                chiTietGioHangs) {
            tongTienhang+=chiTietGioHang.donGia*chiTietGioHang.soLuong;
        }



        textViewTongTienhang.setText(withLargeIntegers(tongTienhang)+" đ");



    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

}