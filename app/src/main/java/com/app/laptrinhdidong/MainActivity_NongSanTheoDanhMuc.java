package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.Double_temp;
import com.app.laptrinhdidong.model.NongSan;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_NongSanTheoDanhMuc extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private NongSanAdapter nongSanAdapter;
    BottomNavigationView bottomNavigationView;
    Menu mMenu;
    Boolean isExpanded = true;
    ArrayList<NongSan> listNognSan = new ArrayList<>();
    ArrayList<Double_temp> nongSans = new ArrayList<>();
    int idDanhMuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nong_san_theo_danh_muc);
        bottomNavigationView = findViewById(R.id.menubottom);
        Intent intent = getIntent();
        idDanhMuc = intent.getIntExtra("idDanhMuc", 0);

        initUI();
        initToolBar();
//        initRecycleView();
        callApi(idDanhMuc);
        initToolBarAnimation();

        onNavigationItemSelected();



    }
public void onNavigationItemSelected(){
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(MainActivity_NongSanTheoDanhMuc.this, DanhMucActivity.class));
                    break;
                case R.id.card:
                    startActivity(new Intent(MainActivity_NongSanTheoDanhMuc.this, GioHangActivity.class));
                    break;
                case R.id.search:
                    startActivity(new Intent(MainActivity_NongSanTheoDanhMuc.this, activity_search.class));
                    break;
                case R.id.profile:
                    startActivity(new Intent(MainActivity_NongSanTheoDanhMuc.this, activity_profile.class));
                    break;
            }
            return true;
        }
    });
}

    private void initUI(){
        appBarLayout = findViewById(R.id.appbarlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout);
        toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.recyclerview);

    }

    private void initToolBar(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecycleView(ArrayList<Double_temp> nongSans){
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        nongSanAdapter = new NongSanAdapter();
//        userAdapter.setData(getListUser());
        nongSanAdapter.setData(nongSans,MainActivity_NongSanTheoDanhMuc.this);
        recyclerView.setAdapter(nongSanAdapter);
    }


    private ArrayList<Double_temp> getListUser(){
        ArrayList<Double_temp> nss = new ArrayList<>();
        for (int i = 0;i<30;i++){
            nss.add(new Double_temp());

        }
        return nss;


    }
    void callApi(int idDanhMuc) {
        ApiService.apiService.convertNongSanTheoDanhMuc(idDanhMuc).enqueue(new Callback<ArrayList<NongSan>>() {
            @Override
            public void onResponse(Call<ArrayList<NongSan>> call, Response<ArrayList<NongSan>> response) {
                listNognSan = response.body();
                Toast.makeText(MainActivity_NongSanTheoDanhMuc.this, "CAll Api theo danh muc thanh cong", Toast.LENGTH_SHORT).show();

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
                initRecycleView(nongSans);
//                listView = findViewById(R.id.listView);
//                SanhamTheoDanhMucActivity.NongSanAdapter nongSanAdapter = new SanhamTheoDanhMucActivity.NongSanAdapter();
//                listView.setAdapter(nongSanAdapter);


            }

            @Override
            public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                Toast.makeText(MainActivity_NongSanTheoDanhMuc.this, "Call API that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initToolBarAnimation(){
        collapsingToolbarLayout.setTitle(" "); //

        Bitmap bitmap    = BitmapFactory.decodeResource(getResources(),R.drawable.meomeo);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int myColor  = palette.getVibrantColor(getResources().getColor(R.color.color_bar));
                collapsingToolbarLayout.setContentScrimColor(myColor);
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black_trans));
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset)>200){
                    collapsingToolbarLayout.setTitle("Nông sản"); //
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
                    isExpanded = false;
                }else{
                    collapsingToolbarLayout.setTitle(" "); //
                    isExpanded = true;
                }
                invalidateOptionsMenu();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mMenu = menu;
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if(mMenu != null && (!isExpanded || mMenu.size() !=1)){
//            mMenu.add("Add").setIcon(R.drawable.add).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        }
//        return super.onPrepareOptionsMenu(mMenu);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if(item.getTitle() == "Add" ){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}