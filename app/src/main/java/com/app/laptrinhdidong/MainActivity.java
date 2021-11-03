package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(MainActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(MainActivity.this, GioHangActivity.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(MainActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });
    }

    public void moTrangHoaDon() {
        Intent intent = new Intent(this, DanhSachHoaDonActivity.class);
        startActivity(intent);
    }
}