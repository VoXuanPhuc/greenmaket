package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.ItemGioHang;
import com.app.laptrinhdidong.model.KhachHang;
import com.app.laptrinhdidong.model.NongSan;
import com.app.laptrinhdidong.model.YeuThich;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_chitietnongsan extends AppCompatActivity {
    TextView tenNSTV;
    TextView moTaNSTV;
    TextView giaTV;
    ImageView imageIG;

    ArrayList<ItemGioHang> itemGioHangs;
    ImageButton add;
    ImageButton minus;
    ImageButton love;
    TextView number;
    Integer sl;
    int tym1;
    int idNongSan;
    int gia;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Intent intent;
String idYeuThich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnongsan);

        preferences = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = preferences.edit();

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            itemGioHangs = (ArrayList<ItemGioHang>) objectMapper.readValue(preferences.getString("giohang", "[]"), new TypeReference<ArrayList<ItemGioHang>>() {
            });

            System.out.println("ket qua : Thanh cong" + itemGioHangs);
        } catch (Exception e) {
            System.out.println("ket qua : That bai");
        }


        tym1 = R.drawable.tym_icon;
        add = (ImageButton) findViewById(R.id.them);
        minus = (ImageButton) findViewById(R.id.giam);
        love = (ImageButton) findViewById(R.id.yeuthichbaidang);
        number = (TextView) findViewById(R.id.soluongctns);
        intent = getIntent();

        String ten = intent.getStringExtra("tenNS");
        String moTaNs = intent.getStringExtra("moTaNS");
        gia = intent.getIntExtra("gia", 0);
        idNongSan = intent.getIntExtra("maNS", 0);

        tenNSTV = findViewById(R.id.tenNongsanctns);
        tenNSTV.setText(ten);

        moTaNSTV = findViewById(R.id.danhmucnongsanctns);
        moTaNSTV.setText(moTaNs);

        giaTV = findViewById(R.id.gianongsanctns);

        giaTV.setText(withLargeIntegers(gia)+" đ");


        imageIG = findViewById(R.id.hinhAnhNongSan);
//        if(url.length() != 0){
//
//            Picasso.with(activity_chitietnongsan.this).load(url)
//                    .placeholder(R.drawable.chuoi)
//                    .into(imageIG);
//        }
        Picasso.with(activity_chitietnongsan.this).load(intent.getStringExtra("URL"))
                                .placeholder(R.drawable.loading)
                                .into(imageIG);
//        try {
//            ApiService.apiService.getAnhNongSanByIdKhachHang(idNongSan).enqueue(new Callback<ArrayList<AnhNongSan>>() {
//                @Override
//                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
//                    ArrayList<AnhNongSan> anhNongSans = response.body();
//
//                    if (anhNongSans.size() != 0)
//                        Picasso.with(activity_chitietnongsan.this).load(anhNongSans.get(0).getTen())
//                                .placeholder(R.drawable.chuoi)
//                                .into(imageIG);
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {
//
//                }
//            });
//        }catch (Exception e){
//
//        }



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
                    if(idYeuThich != null){
                        callApiRemoveYeuThich();
                    }

                    tym1 = R.drawable.tymtym;
                } else {
                    callApiCallLove();
                    tym1 = R.drawable.tym_icon;
                }
                love.setImageResource(tym1);
            }
        });
        initOnClickForButtonAddCart();


    }
    public void callApiRemoveYeuThich(){
ApiService.apiService.deleteYeuThich(idYeuThich).enqueue(
        new Callback<YeuThich>() {
            @Override
            public void onResponse(Call<YeuThich> call, Response<YeuThich> response) {
                Toast.makeText(activity_chitietnongsan.this, "Đã xóa nông sản này ra khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<YeuThich> call, Throwable t) {

            }
        }
);
    }
    public void callApiCallLove(){
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        YeuThich yeuThich = new YeuThich();
        yeuThich.setId(null);
        KhachHang khachHang = new KhachHang();
        khachHang.setId(preferences.getString("idKhachHang",""));
        NongSan nongSan = new NongSan() ;
        nongSan.setId(idNongSan);

        yeuThich.setKhachhang(khachHang);
        yeuThich.setNongsan(nongSan);


        ApiService.apiService.postYeuThich(yeuThich).enqueue(new Callback<YeuThich>() {
            @Override
            public void onResponse(Call<YeuThich> call, Response<YeuThich> response) {
                if(response.body() != null){
                    Toast.makeText(activity_chitietnongsan.this, "Đã thêm nông sản này vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
idYeuThich = response.body().getId();
                }else{
                    Toast.makeText(activity_chitietnongsan.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    System.out.println("mes loi"+response);
                }
            }

            @Override
            public void onFailure(Call<YeuThich> call, Throwable t) {
                Toast.makeText(activity_chitietnongsan.this, "Thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void initOnClickForButtonAddCart() {
        Button btnThemGioHang = findViewById(R.id.btnThemGioHang);
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ketQuaTimKiem = false;
                ItemGioHang itemGioHang = new ItemGioHang();
                itemGioHang.setId(String.valueOf(intent.getIntExtra("ID", 0)));
                itemGioHang.setSoLuong(Integer.parseInt(number.getText().toString()));
                itemGioHang.setGia(gia);
                Gson gson = new Gson();
                int i = 0;
                for (i = 0; i < itemGioHangs.size(); i++) {
                    if (itemGioHangs.get(i).getId().equals(String.valueOf(intent.getIntExtra("ID", 0)))) {
                        ketQuaTimKiem = true;
                        break;
                    }
                }

                if (ketQuaTimKiem == true) {
                    itemGioHangs.get(i).setSoLuong(itemGioHangs.get(i).getSoLuong() + Integer.parseInt(number.getText().toString()));
                    editor.putString("giohang", gson.toJson(itemGioHangs));// or put anything you want in this with String type
                    editor.apply();
                } else {
                    itemGioHangs.add(itemGioHang);
                    editor.putString("giohang", gson.toJson(itemGioHangs));// or put anything you want in this with String type
                    editor.apply();
                }

                finish();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(activity_chitietnongsan.this, DanhMucActivity.class));

                        break;
                    case R.id.card:
                        startActivity(new Intent(activity_chitietnongsan.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(activity_chitietnongsan.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(activity_chitietnongsan.this, activity_profile.class));
                        break;
                }
                return true;
            }
        });
    }
    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
    public void finish(View view) {
        finish();
    }
}