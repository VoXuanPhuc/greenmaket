package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SanhamTheoDanhMucActivity extends AppCompatActivity {
    ConstraintLayout theSanPham1;

    BottomNavigationView bottomNavigationView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanham_theo_danh_muc);

        bottomNavigationView  = findViewById(R.id.menubottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });




        theSanPham1 = findViewById(R.id.ctheSanPham1);
//        theSanPham1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class));
//
//            }
//        });


    }


    public void moChiTietNongSan(View view) {
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class));

    }
}