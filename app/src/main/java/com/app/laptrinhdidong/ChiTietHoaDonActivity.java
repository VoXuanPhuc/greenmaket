package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ChiTietHoaDon> chiTietHoaDons ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        listView = findViewById(R.id.listViewCTHD);
        chiTietHoaDons = new ArrayList<>();
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));
        chiTietHoaDons.add(new ChiTietHoaDon("Chuối rất ngon","Được Đại trồng",5,25000));

        ChiTietHoaDonAdapter chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(ChiTietHoaDonActivity.this,R.layout.layout_dongchitiet_hoadon,chiTietHoaDons);
          listView.setAdapter(chiTietHoaDonAdapter);


    }
}