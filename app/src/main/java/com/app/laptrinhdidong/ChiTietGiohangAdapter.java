package com.app.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChiTietGiohangAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<ChiTietGioHang> chiTietGioHangs;


    public ChiTietGiohangAdapter(Context context, int layout, List<ChiTietGioHang> chiTietGioHangs) {
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

        TextView ten = convertView.findViewById(R.id.giohang_ten);
        TextView donGia = convertView.findViewById(R.id.gihang_giaSP);
        TextView soLuong = convertView.findViewById(R.id.giohang_soLuong);

        ten.setText(chiTietGioHangs.get(position).getTenSP());
        donGia.setText(String.valueOf(chiTietGioHangs.get(position).getDonGia()));
        soLuong.setText(String.valueOf(chiTietGioHangs.get(position).getSoLuong()));




        return convertView;
    }
}
