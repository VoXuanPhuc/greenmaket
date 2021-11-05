package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_profile extends AppCompatActivity {

    Button editProfile;
    Button hoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editProfile = (Button) findViewById(R.id.editProfile);
        hoaDon = (Button) findViewById(R.id.hoadon);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_profile.this, com.app.laptrinhdidong.editProfile.class));
            }
        });

        hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_profile.this, DanhSachHoaDonActivity.class));
            }
        });




    }
}