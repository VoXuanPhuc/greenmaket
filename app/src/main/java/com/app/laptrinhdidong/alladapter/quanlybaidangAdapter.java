package com.app.laptrinhdidong.alladapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.laptrinhdidong.R;
import com.app.laptrinhdidong.allclass.nongsanClass;

import java.util.List;

public class quanlybaidangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<nongsanClass> nongsanClassesList;

    public quanlybaidangAdapter(Context context, int layout, List<nongsanClass> nongsanClassesList) {
        this.context = context;
        this.layout = layout;
        this.nongsanClassesList = nongsanClassesList;
    }

    @Override
    public int getCount() {
        return nongsanClassesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView ten = (TextView) view.findViewById(R.id.tenNongsan);
        TextView mieuta = (TextView) view.findViewById(R.id.mieutaNongsan);
        TextView gia = (TextView) view.findViewById(R.id.giaNongsan);
        TextView solgcon = (TextView) view.findViewById(R.id.soluongCon);
        ImageView hinh = (ImageView) view.findViewById(R.id.imageviewTraicaybaidang);

        nongsanClass nongsan = nongsanClassesList.get(i);

        ten.setText(nongsan.getTen());
        mieuta.setText(nongsan.getMieuTa());
        gia.setText(nongsan.getGia().toString());
        solgcon.setText("100");
        hinh.setImageResource(nongsan.getHinh());
        return view;
    }
}
