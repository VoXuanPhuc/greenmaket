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

public class GioHangActivity extends AppCompatActivity {
    ArrayList<ChiTietGioHang> chiTietGioHangs;
    ListView listView;
    Button buttonThanhToan;
    TextView textViewTongTienhang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        buttonThanhToan = findViewById(R.id.button);
        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHangActivity.this,UI_saunutmuangayActivity.class));
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