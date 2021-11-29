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

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        int idNongSan = intent.getIntExtra("maNS",0);

        tenNSTV  =  findViewById(R.id.tenNongsanctns);
        tenNSTV.setText(ten);

        moTaNSTV = findViewById(R.id.danhmucnongsanctns);
        moTaNSTV.setText(moTaNs);

        giaTV = findViewById(R.id.gianongsanctns);
        giaTV.setText(String.valueOf(gia)+" đ");


        imageIG = findViewById(R.id.hinhAnhNongSan);
//        if(url.length() != 0){
//
//            Picasso.with(activity_chitietnongsan.this).load(url)
//                    .placeholder(R.drawable.chuoi)
//                    .into(imageIG);
//        }

        try {
            ApiService.apiService.getAnhNongSanByIdKhachHang(idNongSan).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();

                    if (anhNongSans.size() != 0)
                        Picasso.with(activity_chitietnongsan.this).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.chuoi)
                                .into(imageIG);
                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });
        }catch (Exception e){

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