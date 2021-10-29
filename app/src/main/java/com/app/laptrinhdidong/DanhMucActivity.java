package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DanhMucActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        break;
                    case R.id.card :
                        break;
                    case R.id.notification:
                        break;
                    case R.id.profile:
                        startActivity(new Intent(DanhMucActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });
    }

    public void open(View view) {
        Intent intent = new Intent(this,SanPhamTheoDanhMucActivity.class);
        startActivity(intent);
    }
}