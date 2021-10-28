package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DanhSachHoaDonActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HoaDon> hoaDonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoa_don);

        listView = findViewById(R.id.listViewHoaDon);
        hoaDonList = new ArrayList<>();

        hoaDonList.add(new HoaDon("#HD000001","12/11/2021","2500000","Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000001","12/11/2021","2500000","Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000001","12/11/2021","2500000","Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000001","12/11/2021","2500000","Đã thanh toán"));
        hoaDonList.add(new HoaDon("#HD000001","12/11/2021","2500000","Đã thanh toán"));

        HoaDonAdapter hoaDonAdapter;
        hoaDonAdapter = new HoaDonAdapter(DanhSachHoaDonActivity.this, R.layout.dong_hoa_don, hoaDonList);
        listView.setAdapter(hoaDonAdapter);

    }
}