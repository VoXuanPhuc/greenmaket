package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.laptrinhdidong.API.ApiService;

import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.ItemGioHang;
import com.app.laptrinhdidong.model.NongSan;
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

public class GioHangActivity extends AppCompatActivity {


    ArrayList<ItemGioHang> itemGioHangs;

    ListView listView;
    Button buttonThanhToan;
    TextView textViewTongTienhang;
    BottomNavigationView bottomNavigationView;
    TextView tongTienTanhToan;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);


//        SET COOKIE
        preferences = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = preferences.edit();

//        editor.putString("cart","[{ \"id\" :2001, \"soLuong\" :30 }, {\"id\" :2002, \"soLuong\" :20 }]");
//        editor.apply();

        tongTienTanhToan = findViewById(R.id.tongTienTanhToan);
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            itemGioHangs = (ArrayList<ItemGioHang>) objectMapper.readValue(preferences.getString("giohang", "[]"), new TypeReference<ArrayList<ItemGioHang>>() {
            });


        } catch (Exception e) {
            System.out.println("ket qua : That bai");
        }

        buttonThanhToan = findViewById(R.id.button);
        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHangActivity.this, UI_saunutmuangayActivity.class));
            }
        });

        bottomNavigationView = findViewById(R.id.menubottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(GioHangActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card:
                        startActivity(new Intent(GioHangActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(GioHangActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(GioHangActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });


        ItemGiohangAdapter itemGiohangAdapter = new ItemGiohangAdapter();

        listView = findViewById(R.id.listViewSAnPhamGioHang);

        listView.setAdapter(itemGiohangAdapter);

        textViewTongTienhang = findViewById(R.id.tongTienTanhToan);


    }

    void setCookie() {
        Gson gson = new Gson();
        editor.putString("giohang", gson.toJson(itemGioHangs));// or put anything you want in this with String type
        editor.apply();
    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }


    class Result extends Thread {

    }

    class ItemGiohangAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return itemGioHangs.size();
        }

        @Override
        public Object getItem(int position) {
            return itemGioHangs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.layout_dongsanpham_giohang, null);

            ImageView imageView;
            TextView ten;
            TextView tongTien;
            TextView soLuong;

            imageView = view.findViewById(R.id.imageView_giohang);
            ten = view.findViewById(R.id.ten_hoadon);
            tongTien = view.findViewById(R.id.giohang_giaSP);
            soLuong = view.findViewById(R.id.giohang_soLuong);
            final NongSan[] nongSan = new NongSan[1];
            ApiService.apiService.getNongSanById(itemGioHangs.get(position).getId())
                    .enqueue(new Callback<NongSan>() {
                        @Override
                        public void onResponse(Call<NongSan> call, Response<NongSan> response) {
                            nongSan[0] = response.body();
                            ten.setText(nongSan[0].getTenNS());
                            soLuong.setText(String.valueOf(itemGioHangs.get(position).getSoLuong()));
                            tongTien.setText(String.valueOf(itemGioHangs.get(position).getSoLuong() * nongSan[0].getGia()));
                            tongTienTanhToan.setText(String.valueOf(Integer.parseInt(tongTienTanhToan.getText().toString()) + Integer.valueOf(tongTien.getText().toString())));

                            itemGioHangs.get(position).setGia(nongSan[0].getGia());
                        }

                        @Override
                        public void onFailure(Call<NongSan> call, Throwable t) {

                        }
                    });

            ApiService.apiService.getAnhNongSanByIdNongSan(itemGioHangs.get(position).getId()).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();
                    itemGioHangs.get(position).setUrl(anhNongSans.get(0).getTen());
                    if (anhNongSans.size() != 0)
                        Picasso.with(GioHangActivity.this).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.trailuu)
                                .into(imageView);


                    setCookie();
                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });


//            SỰ Kiện TĂNG GIẢM
            ImageView btnThem = view.findViewById(R.id.imageView11);
            ImageView btnGiam = view.findViewById(R.id.btnGiam);

            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemGioHangs.get(position).setSoLuong(itemGioHangs.get(position).getSoLuong() + 1);
                    soLuong.setText(String.valueOf(itemGioHangs.get(position).getSoLuong()));

                    tongTien.setText(String.valueOf(nongSan[0].getGia() * itemGioHangs.get(position).getSoLuong()));
                    tongTienTanhToan.setText(String.valueOf(Integer.parseInt(tongTienTanhToan.getText().toString()) + nongSan[0].getGia()));
                    setCookie();
                }
            });

            btnGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(soLuong.getText().toString()) == 1) {
                        tongTienTanhToan.setText("0");
                        itemGioHangs.remove(position);
                        Gson gson = new Gson();

                        notifyDataSetChanged();
                    } else {
                        itemGioHangs.get(position).setSoLuong(itemGioHangs.get(position).getSoLuong() - 1);

                        soLuong.setText(String.valueOf(itemGioHangs.get(position).getSoLuong()));
                        tongTien.setText(String.valueOf(nongSan[0].getGia() * itemGioHangs.get(position).getSoLuong()));
                        tongTienTanhToan.setText(String.valueOf(Integer.parseInt(tongTienTanhToan.getText().toString()) - nongSan[0].getGia()));

                    }
                    setCookie();

                }
            });


            return view;
        }


    }


}

