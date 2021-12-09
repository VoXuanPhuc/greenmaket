package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.Double_temp;
import com.app.laptrinhdidong.model.NongSan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanhamTheoDanhMucActivity extends AppCompatActivity {
    ListView listView;
    ConstraintLayout theSanPham1;
    TextView tenDanhMuc;
    BottomNavigationView bottomNavigationView;
    ArrayList<Double_temp> nongSans = new ArrayList<>();
    ArrayList<NongSan> listNognSan = new ArrayList<>();
    int idDanhMuc;
    String url = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanham_theo_danh_muc);
        Intent intent = getIntent();
        idDanhMuc = intent.getIntExtra("idDanhMuc", 0);

        callApi(idDanhMuc);


        bottomNavigationView = findViewById(R.id.menubottom);


        tenDanhMuc = findViewById(R.id.tenDanhMuc);

        tenDanhMuc.setText(intent.getStringExtra("tenDanhMuc"));


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, DanhMucActivity.class));
                        break;
                    case R.id.card:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, GioHangActivity.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, activity_search.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(SanhamTheoDanhMucActivity.this, Activity_dangnhap.class));
                        break;
                }
                return true;
            }
        });


        theSanPham1 = findViewById(R.id.card0);


    }


    void callApi(int idDanhMuc) {
        ApiService.apiService.convertNongSanTheoDanhMuc(idDanhMuc).enqueue(new Callback<ArrayList<NongSan>>() {
            @Override
            public void onResponse(Call<ArrayList<NongSan>> call, Response<ArrayList<NongSan>> response) {
                listNognSan = response.body();
                Toast.makeText(SanhamTheoDanhMucActivity.this, "CAll Api theo danh muc thanh cong", Toast.LENGTH_SHORT).show();

                System.out.println("soluong : " + listNognSan.size());
                for (int i = 1; i <= listNognSan.size(); i++) {
                    Double_temp double_temp = new Double_temp();
                    double_temp.nongSan0 = listNognSan.get(i - 1);
                    if (i == listNognSan.size()) {
                        nongSans.add(double_temp);
                        break;
                    }
                    double_temp.nongSan1 = listNognSan.get(++i - 1);
                    nongSans.add(double_temp);
                }
                listView = findViewById(R.id.listView);
                NongSanAdapter nongSanAdapter = new NongSanAdapter();
                listView.setAdapter(nongSanAdapter);


            }

            @Override
            public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                Toast.makeText(SanhamTheoDanhMucActivity.this, "Call API that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void finish(View view) {
        finish();
    }


    class NongSanAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nongSans.size();
        }

        @Override
        public Object getItem(int position) {
            return nongSans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.itemlistview_sanphamtheodanhmuc, null);


            TextView name0, price0;
            ImageView image0;

            ConstraintLayout constraintLayout0;
            constraintLayout0 = view.findViewById(R.id.card0);
            name0 = view.findViewById(R.id.name0);
            price0 = view.findViewById(R.id.price0);
            image0 = view.findViewById(R.id.image0);
            name0.setText(nongSans.get(position).nongSan0.getTenNS());
            price0.setText(String.valueOf(nongSans.get(position).nongSan0.getGia()));

            ApiService.apiService.getAnhNongSanByIdKhachHang(nongSans.get(position).nongSan0.getId()).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();

                    if (anhNongSans.size() != 0){
                        Picasso.with(SanhamTheoDanhMucActivity.this).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.chuoi)
                                .into(image0);

                    }

                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });
            constraintLayout0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class);
                    intent.putExtra("tenNS", nongSans.get(position).nongSan0.getTenNS());
                    intent.putExtra("moTaNS", nongSans.get(position).nongSan0.getMoTaNS());
                    intent.putExtra("gia", nongSans.get(position).nongSan0.getGia());
                    intent.putExtra("maNS",nongSans.get(position).nongSan0.getId());
                    intent.putExtra("ID",nongSans.get(position).nongSan0.getId());
                    startActivity(intent);

                }
            });


            if (nongSans.get(position).nongSan1 == null) {
                ConstraintLayout constraintLayout = view.findViewById(R.id.card1);
                constraintLayout.setVisibility(View.GONE);
            } else {
                TextView name1, price1;
                ImageView image1;
                ConstraintLayout constraintLayout1;
                constraintLayout1 = view.findViewById(R.id.card1);
                name1 = view.findViewById(R.id.name1);
                price1 = view.findViewById(R.id.price1);
                image1 = view.findViewById(R.id.image1);
                name1.setText(nongSans.get(position).nongSan1.getTenNS());
                price1.setText(String.valueOf(nongSans.get(position).nongSan1.getGia()));


                ApiService.apiService.getAnhNongSanByIdKhachHang(nongSans.get(position).nongSan1.getId()).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                        ArrayList<AnhNongSan> anhNongSans = response.body();

                        if (anhNongSans.size() != 0)
                            Picasso.with(SanhamTheoDanhMucActivity.this).load(anhNongSans.get(0).getTen())
                                    .placeholder(R.drawable.bananas)
                                    .into(image1);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                    }
                });

                constraintLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SanhamTheoDanhMucActivity.this, activity_chitietnongsan.class);
                        intent.putExtra("tenNS", nongSans.get(position).nongSan1.getTenNS());
                        intent.putExtra("moTaNS", nongSans.get(position).nongSan1.getMoTaNS());
                        intent.putExtra("gia", nongSans.get(position).nongSan1.getGia());
                        intent.putExtra("maNS",nongSans.get(position).nongSan1.getId());
                        intent.putExtra("ID",nongSans.get(position).nongSan1.getId());
                        startActivity(intent);

                    }
                });

            }
            return view;
        }
    }


}