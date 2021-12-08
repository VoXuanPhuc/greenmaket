package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class giaodien2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien2);
        ImageButton nextbtn;
        Button bnskip;
        nextbtn = (ImageButton) findViewById(R.id.next);
        bnskip = (Button) findViewById(R.id.btnskip) ;

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(giaodien2.this, giaodien3.class));
            }
        });

        bnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(giaodien2.this, DanhMucActivity.class));
            }
        });

        
    }
}