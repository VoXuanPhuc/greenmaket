package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DanhMucActivity extends AppCompatActivity {
    LinearLayout traiCayTuoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        traiCayTuoi = findViewById(R.id.traicaytuoi);

        traiCayTuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhMucActivity.this, SanhamTheoDanhMucActivity.class));

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        break;
                    case R.id.card :
                        startActivity(new Intent(DanhMucActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(DanhMucActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(DanhMucActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });
    }


}