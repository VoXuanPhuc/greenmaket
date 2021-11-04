package com.app.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietHoaDonAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ChiTietHoaDon> chiTietHoaDons;

    public ChiTietHoaDonAdapter(Context context, int layout, List<ChiTietHoaDon> chiTietHoaDons) {
        this.context = context;
        this.layout = layout;
        this.chiTietHoaDons = chiTietHoaDons;
    }

    @Override
    public int getCount() {
        return chiTietHoaDons.size();
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

        TextView ten = (TextView) convertView.findViewById(R.id.tenSanPham_CTHD);

        TextView soLuong = (TextView)  convertView.findViewById(R.id.soLuowng_CTHD);

        TextView tong = (TextView)  convertView.findViewById(R.id.tong_CTHD);

        ImageView hinhAnh = (ImageView) convertView.findViewById(R.id.hinhanhchitiet_hoaDon);

        ten.setText(chiTietHoaDons.get(position).getTen());

        soLuong.setText(String.valueOf(chiTietHoaDons.get(position).getSoLuong()));

        tong.setText(withLargeIntegers(chiTietHoaDons.get(position).getDonGia()*chiTietHoaDons.get(position).getSoLuong()));

        hinhAnh.setImageResource(chiTietHoaDons.get(position).image);

        return convertView;
    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}
