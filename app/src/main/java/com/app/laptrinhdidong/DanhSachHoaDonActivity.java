package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;

import com.app.laptrinhdidong.allclass.danhmucClass;
import com.app.laptrinhdidong.model.HoaDon;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachHoaDonActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<HoaDon> hoaDons;
    BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoa_don);
        progressBar = findViewById(R.id.progress);

        bottomNavigationView = findViewById(R.id.menubottom);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(DanhSachHoaDonActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });

        listView = findViewById(R.id.listViewHoaDon);

        callApi((long) Long.parseLong(activity_profile.khachHang.getId()));


    }

    public void moChiTietHoaDon() {
        Intent intent = new Intent(this, ChiTietHoaDonActivity.class);
        startActivity(intent);
    }


    void callApi(long id) {

        ApiService.apiService.getHoaDonByKH(id)
                .enqueue(new Callback<ArrayList<HoaDon>>() {
            @Override
            public void onResponse(Call<ArrayList<HoaDon>> call, Response<ArrayList<HoaDon>> response) {
                hoaDons = response.body();
                progressBar.setVisibility(View.GONE);

                HoaDonAdapter hoaDonAdapter = new HoaDonAdapter();

                listView.setAdapter(hoaDonAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<HoaDon>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DanhSachHoaDonActivity.this, "Hóa ĐƠn Thất Bại", Toast.LENGTH_SHORT).show();

            }
        });
    }


    class HoaDonAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hoaDons.size();
        }

        @Override
        public Object getItem(int position) {
            return hoaDons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.dong_hoa_don, null);
            TextView maHD = view.findViewById(R.id.maHD);
            TextView ngay = view.findViewById(R.id.ngay);
            TextView tongThanhTona = view.findViewById(R.id.thanhTien);
            TextView trangThai = view.findViewById(R.id.trangThai);

            maHD.setText("HD" + String.valueOf(hoaDons.get(position).getId()));

            ngay.setText(hoaDons.get(position).getNgaytao().toString());

            if (hoaDons.get(position).getTrangthai().equals("Đã giao")) {
                trangThai.setTextColor(Color.parseColor("#009969"));
            }
            if (hoaDons.get(position).getTrangthai().equals("Đang xác nhận")) {
                trangThai.setTextColor(Color.parseColor("#17A2B8"));
            }

            trangThai.setText(hoaDons.get(position).getTrangthai());
            tongThanhTona.setText(String.valueOf(hoaDons.get(position).getTongthanhtoan()));

            ConstraintLayout constraintLayout = view.findViewById(R.id.itemhoadon);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DanhSachHoaDonActivity.this, ChiTietHoaDonActivity.class);
                    intent.putExtra("trangThaiDonHang",hoaDons.get(position).getTrangthai());
                    intent.putExtra("ngayMua",hoaDons.get(position).getNgaytao().toString());
                    intent.putExtra("tongTien",hoaDons.get(position).getTongthanhtoan());
                    intent.putExtra("maHD",hoaDons.get(position).getId());
                    startActivity(intent);
                }
            });


            return view;
        }
    }

}