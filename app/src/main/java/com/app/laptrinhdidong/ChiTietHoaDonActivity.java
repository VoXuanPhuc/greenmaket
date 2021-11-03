package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ChiTietHoaDon> chiTietHoaDons ;
    TextView textViewTongTienHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        listView = findViewById(R.id.listViewCTHD);
        chiTietHoaDons = new ArrayList<>();
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));
        chiTietHoaDons.add(new ChiTietHoaDon("Trái Lựu","Được Đại trồng",3,15000,R.drawable.trailuu));


        ChiTietHoaDonAdapter chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(ChiTietHoaDonActivity.this,R.layout.layout_dongchitiet_hoadon,chiTietHoaDons);
          listView.setAdapter(chiTietHoaDonAdapter);

        textViewTongTienHang = findViewById(R.id.tongTienHang);

        double tongTienHang=0;
        for (ChiTietHoaDon chiTietHoaDon :
                chiTietHoaDons) {
            tongTienHang+=chiTietHoaDon.getDonGia()*chiTietHoaDon.getSoLuong();
        }

        textViewTongTienHang.setText(withLargeIntegers(tongTienHang)+" đ");

    }
    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}