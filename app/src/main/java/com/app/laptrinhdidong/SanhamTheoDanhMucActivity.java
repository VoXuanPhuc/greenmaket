package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class SanhamTheoDanhMucActivity extends AppCompatActivity {

    ConstraintLayout theSanPham1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanham_theo_danh_muc);
        theSanPham1 = findViewById(R.id.ctheSanPham1);


    }
    public void moChiTietNongSan(View view) {
        startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class));

    }
}