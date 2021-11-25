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

import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.Double_temp;
import com.app.laptrinhdidong.model.NongSan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanhamTheoDanhMucActivity extends AppCompatActivity {
    ListView listView ;
    ConstraintLayout theSanPham1;
    TextView tenDanhMuc;
    BottomNavigationView bottomNavigationView;
    ArrayList<Double_temp> nongSans = new ArrayList<>();
    ArrayList<AnhNongSan> anhNongSans;
    String tempUrl="";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanham_theo_danh_muc);

        anhNongSans = DanhMucActivity.anhNongSans;
        for (int i = 1; i <= DanhMucActivity.nongsans.size(); i++) {
            Double_temp double_temp = new Double_temp();
            double_temp.nongSan0 = DanhMucActivity.nongsans.get(i-1);
            if(i==DanhMucActivity.nongsans.size()){
                nongSans.add(double_temp);
                break;
            }
            double_temp.nongSan1 = DanhMucActivity.nongsans.get(++i-1);
            nongSans.add(double_temp);
        }


        bottomNavigationView = findViewById(R.id.menubottom);


        Intent intent = getIntent();
        tenDanhMuc = findViewById(R.id.tenDanhMuc);

        tenDanhMuc.setText(intent.getStringExtra("tenDanhMuc"));
        System.out.println(DanhMucActivity.nongsans.size());

        listView = findViewById(R.id.listView);
        NongSanAdapter nongSanAdapter = new NongSanAdapter()    ;
        listView.setAdapter(nongSanAdapter);




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
            for(AnhNongSan anhNongSan : DanhMucActivity.anhNongSans){
                if(anhNongSan.getAnhnongsan().getId()== nongSans.get(position).nongSan0.getId()){
                    Picasso.with(SanhamTheoDanhMucActivity.this).load(anhNongSan.getTen())
                            .placeholder(R.drawable.chuoi)
                            .into(image0);
                    System.out.println(anhNongSan.getAnhnongsan().getId()+" và "+nongSans.get(position).nongSan0.getId()+"2222222222222222222222222222222222");
                    tempUrl = anhNongSan.getTen();
                    break;
                }


            }
            constraintLayout0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent    = new Intent(SanhamTheoDanhMucActivity.this,activity_chitietnongsan.class);
                    intent.putExtra("tenNS",nongSans.get(position).nongSan0.getTenNS());
                    intent.putExtra("moTaNS",nongSans.get(position).nongSan0.getMoTaNS());
                    intent.putExtra("gia",nongSans.get(position).nongSan0.getGia());
                    intent.putExtra("url",tempUrl);
                    startActivity(intent);

                }
            });





















            if(nongSans.get(position).nongSan1 == null){
                ConstraintLayout constraintLayout = view.findViewById(R.id.card1);
                constraintLayout.setVisibility(View.GONE);
            }else{
                TextView name1, price1;
                ImageView image1;
                ConstraintLayout constraintLayout1;
                constraintLayout1 = view.findViewById(R.id.card1);
                name1 = view.findViewById(R.id.name1);
                price1 = view.findViewById(R.id.price1);
                image1 = view.findViewById(R.id.image1);
                name1.setText(nongSans.get(position).nongSan1.getTenNS());
                price1.setText(String.valueOf(nongSans.get(position).nongSan1.getGia()));


                for(AnhNongSan anhNongSan : anhNongSans){
                    if(anhNongSan.getAnhnongsan().getId()== nongSans.get(position).nongSan1.getId()){
                        Picasso.with(SanhamTheoDanhMucActivity.this).load(anhNongSan.getTen())
                                .placeholder(R.drawable.bananas)
                                .into(image1);
                        tempUrl = anhNongSan.getTen();
                    }

                }


                constraintLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent    = new Intent(SanhamTheoDanhMucActivity.this,activity_chitietnongsan.class);
                        intent.putExtra("tenNS",nongSans.get(position).nongSan1.getTenNS());
                        intent.putExtra("moTaNS",nongSans.get(position).nongSan1.getMoTaNS());
                        intent.putExtra("gia",nongSans.get(position).nongSan1.getGia());
                        intent.putExtra("url",tempUrl);
                        startActivity(intent);

                    }
                });



            }






            return view;
        }
    }

}