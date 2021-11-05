package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SanhamTheoDanhMucActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanham_theo_danh_muc);

        constraintLayout1 = (ConstraintLayout) findViewById(R.id.sanpham1);

        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class));
            }
        });
    }
}