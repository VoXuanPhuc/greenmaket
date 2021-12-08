package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class giaodien4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien4);
        ImageButton nextbtn;
        Button bnskip;
        nextbtn = (ImageButton) findViewById(R.id.next);
        bnskip = (Button) findViewById(R.id.btnskip) ;

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(giaodien4.this, DanhMucActivity.class));
            }
        });

        bnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(giaodien4.this, DanhMucActivity.class));
            }
        });
    }
}