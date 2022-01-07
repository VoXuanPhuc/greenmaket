package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;


import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.ChiTietHoaDon;
import com.app.laptrinhdidong.model.HoaDon;
import com.app.laptrinhdidong.model.ItemGioHang;
import com.app.laptrinhdidong.model.NongSan;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UI_saunutmuangayActivity extends AppCompatActivity {
    ArrayList<ItemGioHang> itemGioHangs;
    ListView listView;

    TextView textViewTongTienThanhToan;
    Button btnThanhToan;
    BottomNavigationView bottomNavigationView;
    ArrayList<NongSan> nongSans;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_saunutmuangay);
        ObjectMapper objectMapper = new ObjectMapper();
        preferences = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = preferences.edit();
        try {
            itemGioHangs = (ArrayList<ItemGioHang>) objectMapper.readValue(preferences.getString("giohang", "[]"), new TypeReference<ArrayList<ItemGioHang>>() {
            });

        } catch (Exception e) {
            System.out.println("ket qua : That bai");
        }

//        for (ItemGioHang itemGioHang : itemGioHangs) {
//            ApiService.apiService.getNongSanById(itemGioHang.getId()).enqueue(
//                    new Callback<NongSan>() {
//                        @Override
//                        public void onResponse(Call<NongSan> call, Response<NongSan> response) {
//                            nongSans.add(response.body());
//                        }
//
//                        @Override
//                        public void onFailure(Call<NongSan> call, Throwable t) {
//
//                        }
//                    }
//            );
//        }

        listView = findViewById(R.id.listViewThanhToan);
        NongSanAdapter nongSanAdapter = new NongSanAdapter();
        listView.setAdapter(new NongSanAdapter());


        bottomNavigationView = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.card);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(UI_saunutmuangayActivity.this, activity_profile.class));
                        break;
                }
                return true;
            }
        });
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(null);
                hoaDon.setChiphivanchuyen(15000);
                hoaDon.getKhachhang().setId(activity_profile.khachHang.getId());
                hoaDon.setNgaythanhtoan(Instant.now().toString());
                hoaDon.setNgaytao(Instant.now().toString());
                hoaDon.getPhuongThucGH().setId(2152);
                hoaDon.getPhuongthucTT().setId(2201);
                hoaDon.setTongthanhtoan(tt);
                hoaDon.setTrangthai("Chưa Thanh Toán");


                ApiService.apiService.postHoaDon(hoaDon).enqueue(
                        new Callback<HoaDon>() {
                            @Override
                            public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                                HoaDon hoaDonNew = response.body();
                                Toast.makeText(UI_saunutmuangayActivity.this, "Post thành công", Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                System.out.println(gson.toJson(hoaDon) + "haizzz");

                                for (ItemGioHang itemGioHang :
                                        itemGioHangs) {

                                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                                    chiTietHoaDon.setHoadon(hoaDonNew);

                                    NongSan nongSan = new NongSan();
                                    nongSan.setId(Integer.valueOf(itemGioHang.getId()));
                                    chiTietHoaDon.setNongsan(nongSan);
                                    chiTietHoaDon.setGia(itemGioHang.getGia()*itemGioHang.getSoLuong());
                                    chiTietHoaDon.setSoluong(itemGioHang.getSoLuong());



                                    ApiService.apiService.postItemHoaDon(chiTietHoaDon).enqueue(
                                            new Callback<HoaDon>() {
                                                @Override
                                                public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                                                    HoaDon hoaDon1 = response.body();

                                                    System.out.println("chi tiet "+gson.toJson(chiTietHoaDon));

                                                }

                                                @Override
                                                public void onFailure(Call<HoaDon> call, Throwable t) {

                                                }
                                            }
                                    );
                                }

                                editor.putString("giohang", "[]");// or put anything you want in this with String type
                                editor.apply();
                                Intent intent   = new Intent(UI_saunutmuangayActivity.this, ChiTietHoaDonActivity.class);
                                intent.putExtra("trangThaiDonHang",hoaDonNew.getTrangthai());
                                intent.putExtra("ngayMua",hoaDonNew.getNgaytao().toString());
                                intent.putExtra("tongTien",hoaDonNew.getTongthanhtoan());
                                intent.putExtra("maHD",hoaDonNew.getId());
                                startActivity(intent);


                            }

                            @Override
                            public void onFailure(Call<HoaDon> call, Throwable t) {
                                Toast.makeText(UI_saunutmuangayActivity.this, "Post thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }
                );


//                startActivity(new Intent(UI_saunutmuangayActivity.this, ChiTietHoaDonActivity.class));
            }
        });

    }


    int tt = 0;

    public void finish(View view) {
        finish();
    }

    class NongSanAdapter extends BaseAdapter {

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
            View view = getLayoutInflater().inflate(R.layout.layout_dongchitiet_hoadon, null);
            TextView name = view.findViewById(R.id.tenSanPham_CTHD);
            TextView price = view.findViewById(R.id.tong_CTHD);
            TextView qty = view.findViewById(R.id.soLuowng_CTHD);
            ImageView imageView = view.findViewById(R.id.hinhanhchitiet_hoaDon);


//            ApiService.apiService.getNongSanById(Integer.parseInt(itemGioHangs.get(position).getId())).enqueue(
//                    new Callback<NongSan>() {
//                        @Override
//                        public void onResponse(Call<NongSan> call, Response<NongSan> response) {
//
//                            NongSan nongSan = response.body();
//                            price.setText(withLargeIntegers(itemGioHangs.get(position).getSoLuong() * nongSan.getGia()).toString());
//                            name.setText(nongSan.getTenNS());
//                            qty.setText(String.valueOf(itemGioHangs.get(position).getSoLuong()));
//                            tt += itemGioHangs.get(position).getSoLuong() * nongSan.getGia();
//
//
//                            TextView tongTien = findViewById(R.id.tongTienThanhToan);
//                            tongTien.setText(String.valueOf(withLargeIntegers(tt)) + " đ");
//                        }
//
//                        @Override
//                        public void onFailure(Call<NongSan> call, Throwable t) {
//
//                        }
//
//
//                    }
//            );

            price.setText(withLargeIntegers(itemGioHangs.get(position).getSoLuong() * itemGioHangs.get(position).getGia()).toString());
                            name.setText(itemGioHangs.get(position).getTenNS());
                            qty.setText(String.valueOf(itemGioHangs.get(position).getSoLuong()));
                            tt += itemGioHangs.get(position).getSoLuong() * itemGioHangs.get(position).getGia();


                            TextView tongTien = findViewById(R.id.tongTienThanhToan);
                            tongTien.setText(String.valueOf(withLargeIntegers(tt)) + " đ");

            Picasso.with(UI_saunutmuangayActivity.this).load(itemGioHangs.get(position).getUrl())
                    .placeholder(R.drawable.pngegg)
                    .into(imageView);


            return view;
        }
    }


    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

}