package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_dangnhap extends AppCompatActivity {

    Button btnDangNhap;
    TextView tvDangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        btnDangNhap = (Button) findViewById(R.id.btnDangnhap);
        tvDangky = (TextView) findViewById(R.id.dangky);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_dangnhap.this, activity_profile.class));
            }
        });

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_dangnhap.this, activity_dangky.class));
            }
        });
    }
}