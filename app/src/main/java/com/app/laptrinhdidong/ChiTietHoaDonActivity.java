package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.ChiTietHoaDon;
import com.app.laptrinhdidong.model.ItemGioHang;
import com.app.laptrinhdidong.model.NongSan;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietHoaDonActivity extends AppCompatActivity {


    TextView textViewTongTienHang;
    BottomNavigationView bottomNavigationView;
    TextView trangThangThaiHoaDon;
    TextView ngayMua;
    TextView diaChiKhachHang;
    Intent intent;
    TextView soLuongSanPham;
    ArrayList<ChiTietHoaDon> chiTietHoaDons;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        intent = getIntent();
        progressBar = findViewById(R.id.progress);
        soLuongSanPham = findViewById(R.id.soLuongSanPham);
        trangThangThaiHoaDon = findViewById(R.id.trangThaiGiaoDich);
        trangThangThaiHoaDon.setText(intent.getStringExtra("trangThaiDonHang"));

        ngayMua = findViewById(R.id.ngayMua);
        ngayMua.setText(intent.getStringExtra("ngayMua"));

        diaChiKhachHang = findViewById(R.id.diaChiKhachHang);
        diaChiKhachHang.setText(activity_profile.khachHang.getHoTenKH() + ", " + activity_profile.khachHang.getChitietdiachi());

        callApi();

        bottomNavigationView = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(ChiTietHoaDonActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card:
                        startActivity(new Intent(ChiTietHoaDonActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(ChiTietHoaDonActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(ChiTietHoaDonActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });


        textViewTongTienHang = findViewById(R.id.tongTienHang);
        textViewTongTienHang.setText(String.valueOf(intent.getIntExtra("tongTien", 0)));


    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    void callApi() {
        ApiService.apiService.getItemHoaDonByHoaDon(Integer.parseInt(intent.getStringExtra("maHD"))).enqueue(
                new Callback<ArrayList<ChiTietHoaDon>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ChiTietHoaDon>> call, Response<ArrayList<ChiTietHoaDon>> response) {
                        chiTietHoaDons = response.body();
                        ListView listView = findViewById(R.id.listViewCTHD);
                        chiTietHoaDonAdapter chiTietHoaDonAdapter = new chiTietHoaDonAdapter();
                        progressBar.setVisibility(View.GONE);
                        listView.setAdapter(chiTietHoaDonAdapter);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<ChiTietHoaDon>> call, Throwable t) {
                        System.out.println("coloi");
                    }
                }
        );
    }

    public void finish(View view) {
        finish();
    }


    class chiTietHoaDonAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return chiTietHoaDons.size();
        }

        @Override
        public Object getItem(int position) {
            return chiTietHoaDons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.layout_dongchitiet_hoadon, null);
            TextView tenSanPham_CTHD = view.findViewById(R.id.tenSanPham_CTHD);
            TextView soLuowng_CTHD = view.findViewById(R.id.soLuowng_CTHD);
            TextView tong_CTHD = view.findViewById(R.id.tong_CTHD);
            ImageView hinhanhchitiet_hoaDon = view.findViewById(R.id.hinhanhchitiet_hoaDon);

            tong_CTHD.setText(String.valueOf(chiTietHoaDons.get(position).getGia()));
            soLuowng_CTHD.setText(String.valueOf(chiTietHoaDons.get(position).getSoluong()));


            ApiService.apiService.getNongSanById((chiTietHoaDons.get(position).getNongsan().getId())).enqueue(
                    new Callback<NongSan>() {
                        @Override
                        public void onResponse(Call<NongSan> call, Response<NongSan> response) {
                            NongSan nongsan = response.body();
                            tenSanPham_CTHD.setText(nongsan.getTenNS());
                        }
                        @Override
                        public void onFailure(Call<NongSan> call, Throwable t) {

                        }
                    }
            );

            ApiService.apiService.getAnhNongSanByIdKhachHang(chiTietHoaDons.get(position).getNongsan().getId()).enqueue(
                    new Callback<ArrayList<AnhNongSan>>() {
                        @Override
                        public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                            AnhNongSan imageView = response.body().get(0);
                            System.out.println("call hinh anh "+response.body());

                            Picasso.with(ChiTietHoaDonActivity.this).load(imageView.getTen())
                                    .placeholder(R.drawable.pngegg)
                                    .into(hinhanhchitiet_hoaDon);
                            if(chiTietHoaDons.get(position)== chiTietHoaDons.get(chiTietHoaDons.size()-1)){

                            }

                        }

                        @Override
                        public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                        }
                    }
            );


            return view;
        }
    }

}