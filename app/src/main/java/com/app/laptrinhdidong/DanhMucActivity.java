package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.DanhMuc;
import com.app.laptrinhdidong.model.nongsan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhMucActivity extends AppCompatActivity {
    LinearLayout traiCayTuoi;
    ArrayList<DanhMuc> danhMucs;
    static public ArrayList<nongsan> nongsans;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        callApi();
        callTatCaNongSanApi();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.card:
                        startActivity(new Intent(DanhMucActivity.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        startActivity(new Intent(DanhMucActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(DanhMucActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });
    }

    void callApi() {
        ApiService.apiService.convertDanhMuc().enqueue(new Callback<ArrayList<DanhMuc>>() {
            @Override
            public void onResponse(Call<ArrayList<DanhMuc>> call, Response<ArrayList<DanhMuc>> response) {
                Toast.makeText(DanhMucActivity.this, "Call API thanh cong", Toast.LENGTH_SHORT).show();
                System.out.println("111111111111111111111111111111111111111111111111111");
                danhMucs = response.body();
                DanhMucAdapter danhMucAdapter = new DanhMucAdapter();
                listView = findViewById(R.id.listView);
                listView.setAdapter(danhMucAdapter);


            }

            @Override
            public void onFailure(Call<ArrayList<DanhMuc>> call, Throwable t) {
                Toast.makeText(DanhMucActivity.this, "Call API that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void callTatCaNongSanApi() {
        ApiService.apiService.convertTatcaNongSan().enqueue(new Callback<ArrayList<nongsan>>() {
            @Override
            public void onResponse(Call<ArrayList<nongsan>> call, Response<ArrayList<nongsan>> response) {
                nongsans = response.body();

//                Toast.makeText(DanhMucActivity.this, "Call API thanh cong tat ca nong san", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<nongsan>> call, Throwable t) {
                Toast.makeText(DanhMucActivity.this, "Call API that bai", Toast.LENGTH_SHORT).show();


            }
        });

    }


    class DanhMucAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return danhMucs.size();
        }

        @Override
        public Object getItem(int position) {
            return danhMucs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.itemlistview_danhmuc, null);
            TextView textView = view.findViewById(R.id.ten);
            textView.setText(danhMucs.get(position).getTenDM());
            ImageView imageView = view.findViewById(R.id.hinhAnh);

            if (danhMucs.get(position).getAnhDanhMuc() != null  && danhMucs.get(position).getAnhDanhMuc().length()!= 0) {
                Picasso.with(DanhMucActivity.this).load(danhMucs.get(position).getAnhDanhMuc())
                        .placeholder(R.drawable.fruits)
                        .into(imageView);
            }
            LinearLayout linearLayout = view.findViewById(R.id.danhMuc);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Intent intent = new Intent(DanhMucActivity.this, SanhamTheoDanhMucActivity.class);
                    intent.putExtra("tenDanhMuc",danhMucs.get(position).getTenDM());

                    startActivity(intent);

                }
            });

            return view;
        }
    }
}