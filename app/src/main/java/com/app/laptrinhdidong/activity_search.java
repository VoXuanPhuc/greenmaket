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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.alladapter.SearchAdapter;
import com.app.laptrinhdidong.model.NongSan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NongSan> nongSans;
    private SearchAdapter searchAdapter;
    private BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;
    private EditText inputSearch;
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
                        startActivity(new Intent(activity_search.this, Activity_dangnhap.class));
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
                nongSans = response.body();
                searchAdapter = new SearchAdapter(nongSans, activity_search.this);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity_search.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}