package com.app.laptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.alladapter.SearchAdapter;
import com.app.laptrinhdidong.mailapi.ForgotPassword;
import com.app.laptrinhdidong.model.DanhMuc;
import com.app.laptrinhdidong.model.NongSan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NongSan> nongSans;
    private ArrayList<DanhMuc> danhMucs;
    private SearchAdapter searchAdapter;
    private BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;
    private EditText inputSearch;
    Spinner danhmucSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.searchRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        inputSearch = (EditText) findViewById(R.id.edSearch);

        fetchNongSanByKey("");

        searchNongSan();

        danhmucSpinner = (Spinner) findViewById(R.id.spinner);

        callApiDanhmuc();

        bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        startActivity(new Intent(activity_search.this, DanhMucActivity.class));
                        break;
                    case R.id.card :
                        startActivity(new Intent(activity_search.this, GioHangActivity.class));

                        break;
                    case R.id.search:
                        break;
                    case R.id.profile:
                        startActivity(new Intent(activity_search.this, activity_profile.class));
                        break;
                }
                return true;
            }
        });
    }

    public void searchNongSan () {
        List<NongSan> resultSearch = new ArrayList<>();
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resultSearch.clear();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    progressBar.setVisibility(View.GONE);
                    searchAdapter = new SearchAdapter(nongSans, activity_search.this);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                } else {
                    for (NongSan nongSan : nongSans) {
                        if (nongSan.getTenNS().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            resultSearch.add(nongSan);
                        }
                    }
                    progressBar.setVisibility(View.GONE);
                    searchAdapter = new SearchAdapter(resultSearch, activity_search.this);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void fetchNongSanByKey(String key) {
        ApiService.apiService.fetchNongSanByKey().enqueue(new Callback<ArrayList<NongSan>>() {
            @Override
            public void onResponse(Call<ArrayList<NongSan>> call, Response<ArrayList<NongSan>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    nongSans = response.body();
                    searchAdapter = new SearchAdapter(nongSans, activity_search.this);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity_search.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void callApiDanhmuc() {

        ApiService.apiService.convertDanhMuc().enqueue(new Callback<ArrayList<DanhMuc>>() {
            @Override
            public void onResponse(Call<ArrayList<DanhMuc>> call, Response<ArrayList<DanhMuc>> response) {
                if (response.isSuccessful()) {
                    danhMucs = response.body();
                    DanhMuc dm = new DanhMuc();
                    dm.setId(123123321);
                    dm.setTenDM("Chọn danh mục");
                    danhMucs.add(0, dm);
                    setDanhmucForSpinner(danhMucs);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DanhMuc>> call, Throwable t) {

            }
        });
    }

    public void setDanhmucForSpinner(ArrayList<DanhMuc> lv)
    {
        ArrayAdapter<DanhMuc> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lv);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        danhmucSpinner.setAdapter(spinnerAdapter);

        danhmucSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                DanhMuc linhVucDetai = (DanhMuc) parent.getSelectedItem();
                displayDanhmuc(linhVucDetai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                searchAdapter = new SearchAdapter(nongSans, activity_search.this);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getSelectedItem(View view){
        DanhMuc linhVuc = (DanhMuc) danhmucSpinner.getSelectedItem();
    }

    public void displayDanhmuc(DanhMuc lv) {
        if(lv.getTenDM().equals("Chọn danh mục")) {
            fetchNongSanByKey("");
        } else {
            Toast.makeText(activity_search.this, lv.getTenDM(), Toast.LENGTH_SHORT).show();
            ArrayList<NongSan> resultSearch = new ArrayList<>();
            for (NongSan nongSan : nongSans) {
                if (nongSan.getDanhmuc().getId() == (lv.getId())) {
                    resultSearch.add(nongSan);
                }
            }
            searchAdapter = new SearchAdapter(resultSearch, activity_search.this);
            recyclerView.setAdapter(searchAdapter);
            searchAdapter.notifyDataSetChanged();
        }
    }

    public void finish(View view) {
        finish();
    }
}