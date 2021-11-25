package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class activity_chitietnongsan extends AppCompatActivity {
    TextView tenNSTV;
    TextView moTaNSTV;
    TextView giaTV;
    ImageView imageIG;


    ImageButton add;
    ImageButton minus;
    ImageButton love;
    TextView number;
    Integer sl;
    int tym1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnongsan);
        tym1 = R.drawable.tym_icon;
        add = (ImageButton) findViewById(R.id.them);
        minus = (ImageButton) findViewById(R.id.giam);
        love = (ImageButton) findViewById(R.id.yeuthichbaidang);
        number = (TextView) findViewById(R.id.soluongctns);
        Intent intent = getIntent();

        String ten = intent.getStringExtra("tenNS");
        String moTaNs = intent.getStringExtra("moTaNS");
        int gia = intent.getIntExtra("gia",0);
        String url = intent.getStringExtra("url");

        tenNSTV  =  findViewById(R.id.tenNongsanctns);
        tenNSTV.setText(ten);

        moTaNSTV = findViewById(R.id.danhmucnongsanctns);
        moTaNSTV.setText(moTaNs);

        giaTV = findViewById(R.id.gianongsanctns);
        giaTV.setText(String.valueOf(gia)+" đ");


        imageIG = findViewById(R.id.hinhAnhNongSan);
        System.out.println(url+"111111111111111111111111111111111111111111111111111111111111111");
        if(url.length() != 0){

            Picasso.with(activity_chitietnongsan.this).load(url)
                    .placeholder(R.drawable.chuoi)
                    .into(imageIG);
        }





        sl = Integer.parseInt(number.getText().toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl += 1;
                number.setText(sl.toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sl > 1) {
                    sl -= 1;
                    number.setText(sl.toString());
                } else {
                    Toast.makeText(activity_chitietnongsan.this, "Số lượng tối thiểu là 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tym1 == R.drawable.tym_icon) {
                    tym1 = R.drawable.traitim2;
                } else {
                    tym1 = R.drawable.tym_icon;
                }
                love.setImageResource(tym1);
            }
        });

    }
}