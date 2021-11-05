package com.app.laptrinhdidong.alladapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.laptrinhdidong.R;
import com.app.laptrinhdidong.allclass.HoaDon;

import java.text.DecimalFormat;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {

        Context context;
        int layout;
        List<HoaDon> hoaDonList;




        public HoaDonAdapter(Context context, int layout, List<HoaDon> hoaDonList){
                this.context = context;
                this.layout = layout;
                this.hoaDonList = hoaDonList;
        }



        @Override
        public int getCount() {
                return hoaDonList.size();
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

                TextView maHoaDon = (TextView) convertView.findViewById(R.id.maHD);

                TextView ngay = (TextView) convertView.findViewById(R.id.ngay);

                TextView thanhTien = (TextView) convertView.findViewById(R.id.thanhTien);

                TextView trangThai = (TextView) convertView.findViewById(R.id.trangThai);

                maHoaDon.setText(hoaDonList.get(position).getMaHoaDon());
                ngay.setText(hoaDonList.get(position).getNgay());
                thanhTien.setText(withLargeIntegers(hoaDonList.get(position).getThanhTien()));
                trangThai.setText(hoaDonList.get(position).getTrangThai());





                return convertView;
        }
        public static String withLargeIntegers(double value) {
                DecimalFormat df = new DecimalFormat("###,###,###");
                return df.format(value);
        }
}
