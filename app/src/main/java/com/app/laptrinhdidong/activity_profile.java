package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.KhachHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_profile extends AppCompatActivity {

    Button editProfile;
    Button hoaDon;
    TextView tvName;

    public static KhachHang khachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfile = (Button) findViewById(R.id.editProfile);
        hoaDon = (Button) findViewById(R.id.hoadon);
        tvName = (TextView) findViewById(R.id.name);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(activity_profile.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(activity_profile.this, GioHangActivity.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(activity_profile.this, activity_search.class));
                        break;
                    case R.id.profile:
                        break;
                }
                return true;
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_profile.this, com.app.laptrinhdidong.editProfile.class)
                .putExtra("username", tvName.getText().toString()));
            }
        });

        hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_profile.this, DanhSachHoaDonActivity.class));
            }
        });

        getKhachHangById((long) Long.parseLong(khachHang.getId()));

        Intent intentEditProfile = getIntent();
        if (intentEditProfile.getStringExtra("name") != null) {
            tvName.setText(intentEditProfile.getStringExtra("name"));
        }
    }



    public void getKhachHangById(long khachhangId) {
        ApiService.apiService.getKhachHangById(khachhangId).enqueue(
                new Callback<KhachHang>() {
                    @Override
                    public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                        if (response.isSuccessful()) {
                            activity_profile.khachHang = response.body();
                            tvName.setText(khachHang.getHoTenKH());
                        }
                    }

                    @Override
                    public void onFailure(Call<KhachHang> call, Throwable t) {

                    }
                }
        );

    }
}