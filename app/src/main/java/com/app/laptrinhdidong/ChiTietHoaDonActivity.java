package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.ChiTietHoaDon;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ChiTietHoaDon> chiTietHoaDons, chiTietHoaDonCuaKH;
    TextView textViewTongTienHang;
    BottomNavigationView bottomNavigationView;
    TextView trangThangThaiHoaDon;
    TextView ngayMua;
    TextView diaChiKhachHang;
    Intent intent;
    TextView soLuongSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        intent = getIntent();

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


        listView = findViewById(R.id.listViewCTHD);
//        chiTietHoaDons = new ArrayList<>();
//        chiTietHoaDons.add(new ChiTietHoaDon("Dưa hấu Bắc Mỹ","Dưa hấu Bắc Mỹ",3,15000,R.drawable.duahau));
//        chiTietHoaDons.add(new ChiTietHoaDon("Dưa hấu Bắc Mỹ","Dưa hấu Bắc Mỹ",3,15000,R.drawable.bananas));
//        chiTietHoaDons.add(new ChiTietHoaDon("Cam Vinh","Cam Vinh",3,15000,R.drawable.oranges));
//        chiTietHoaDons.add(new ChiTietHoaDon("Nho Bình Thuận","Nho Bình Thuận",3,15000,R.drawable.grape));
//        chiTietHoaDons.add(new ChiTietHoaDon("Táo Cao Bằng","Được Đại trồng",3,15000,R.drawable.apple));
//        chiTietHoaDons.add(new ChiTietHoaDon("Dứa Bình Định","Được Đại trồng",3,15000,R.drawable.pineapple));
//
//
//        ChiTietHoaDonAdapter chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(ChiTietHoaDonActivity.this,R.layout.layout_dongchitiet_hoadon,chiTietHoaDons);
//          listView.setAdapter(chiTietHoaDonAdapter);

        textViewTongTienHang = findViewById(R.id.tongTienHang);
        textViewTongTienHang.setText(String.valueOf(intent.getIntExtra("tongTien",0)));


    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    void callApi() {
        ApiService.apiService.convertTatChaChiTietHoaDon().enqueue(new Callback<ArrayList<ChiTietHoaDon>>() {
            @Override
            public void onResponse(Call<ArrayList<ChiTietHoaDon>> call, Response<ArrayList<ChiTietHoaDon>> response) {
                chiTietHoaDons = response.body();
                chiTietHoaDonCuaKH  =new ArrayList<>();
                for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons
                ) {
                    if(chiTietHoaDon.getHoadon().getId()== intent.getIntExtra("maHD",0))
                    chiTietHoaDonCuaKH.add(chiTietHoaDon);
                }
                soLuongSanPham.setText("Đã mua "+String.valueOf(chiTietHoaDonCuaKH.size())+" sản phẩm");
                listView.setAdapter(new chiTietHoaDonAdapter());
            }


            @Override
            public void onFailure(Call<ArrayList<ChiTietHoaDon>> call, Throwable t) {

            }
        });
    }


    class chiTietHoaDonAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return chiTietHoaDonCuaKH.size();
        }

        @Override
        public Object getItem(int position) {
            return chiTietHoaDonCuaKH.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view   = getLayoutInflater().inflate(R.layout.layout_dongchitiet_hoadon, null);

            return view;
        }
    }

}