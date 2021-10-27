package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class activity_quanlynongsan extends AppCompatActivity {

    List<danhmucClass> dm;
    ListView lvQuanLyNongSan;
    quanlybaidangAdapter lvbaidangAdapter;
    List<nongsanClass> nongsan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlynongsan);

        Nopvao();
        lvbaidangAdapter = new quanlybaidangAdapter(this, R.layout.lv_baidang, nongsan);
        lvQuanLyNongSan.setAdapter(lvbaidangAdapter);
    }
    private void Nopvao(){
        dm = new ArrayList<>();
        nongsan = new ArrayList<>();
      dm.add(new danhmucClass("Trai cay tuoi", "Rau sach", "Thit" , "Ca"));
        lvQuanLyNongSan = findViewById(R.id.listviewnongsan);

        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
        nongsan.add(new nongsanClass("Chuoi", dm.get(0), "Trai cay sach", 20.000, 20, R.drawable.chuoi));
    }
}