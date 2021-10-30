package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_profile extends AppCompatActivity {

    Button editProfile;
    Button managerBaiDang;
    Button hoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfile = (Button) findViewById(R.id.editProfile);
        managerBaiDang = (Button) findViewById(R.id.quanlybd);
        hoaDon = (Button) findViewById(R.id.hoadon);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_profile.this, com.app.laptrinhdidong.editProfile.class));
            }
        });
        managerBaiDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_profile.this, com.app.laptrinhdidong.activity_quanlynongsan.class));
            }
        });

        hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_profile.this,com.app.laptrinhdidong.DanhSachHoaDonActivity.class));
            }
        });




    }
}