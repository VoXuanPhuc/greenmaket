package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.laptrinhdidong.alladapter.HoaDonAdapter;
import com.app.laptrinhdidong.allclass.HoaDon;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DanhSachHoaDonActivity extends AppCompatActivity {



    ListView listView;
    ArrayList<HoaDon> hoaDonList;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoa_don);


        bottomNavigationView  = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(DanhSachHoaDonActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(DanhSachHoaDonActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });




        listView = findViewById(R.id.listViewHoaDon);
        hoaDonList = new ArrayList<>();

        hoaDonList.add(new HoaDon("#HD000001","12/11/2021",270000,"Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000002","12/11/2021",270000,"Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000003","12/11/2021",270000,"Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000004","12/11/2021",270000,"Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000005","12/11/2021",270000,"Đã thanh toán"));

        HoaDonAdapter hoaDonAdapter;
        hoaDonAdapter = new HoaDonAdapter(DanhSachHoaDonActivity.this, R.layout.dong_hoa_don, hoaDonList);
        listView.setAdapter(hoaDonAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moChiTietHoaDon();
            }
        });



    }
    public void moChiTietHoaDon() {
        Intent intent = new Intent(this,ChiTietHoaDonActivity.class);
        startActivity(intent);
    }


}