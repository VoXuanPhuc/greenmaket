package com.app.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DongTinhTienGioHangAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ChiTietGioHang> chiTietGioHangs;


    public DongTinhTienGioHangAdapter(Context context, int layout, List<ChiTietGioHang> chiTietGioHangs) {
        this.context = context;
        this.layout = layout;
        this.chiTietGioHangs = chiTietGioHangs;
    }

    @Override
    public int getCount() {
        return chiTietGioHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView ten = convertView.findViewById(R.id.tenSP_ttgiohang);

        TextView tongTien = convertView.findViewById(R.id.tongGia_ttgiohang);

        ten.setText(chiTietGioHangs.get(position).getTenSP());
//        tongTien.setText(String.valueOf(chiTietGioHangs.get(position).getDonGia()*chiTietGioHangs.get(position).getSoLuong()));

        return convertView;
    }
}
