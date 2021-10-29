package com.app.laptrinhdidong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

                maHoaDon.setText(hoaDonList.get(position).maHoaDon);
                ngay.setText(hoaDonList.get(position).ngay);
                thanhTien.setText(hoaDonList.get(position).thanhTien);
                trangThai.setText(hoaDonList.get(position).trangThai);





                return convertView;
        }
}
