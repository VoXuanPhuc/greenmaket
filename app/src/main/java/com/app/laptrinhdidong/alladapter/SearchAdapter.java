package com.app.laptrinhdidong.alladapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.R;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.NongSan;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<NongSan> listNongSan;
    private Context context;

    public SearchAdapter(List<NongSan> listNongSan, Context context) {
        this.listNongSan = listNongSan;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lvsearch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Picasso.with(context).load(listNongSan.get(position).getAnhDaiDienNongSan()).into(holder.imageNongsan);
        holder.imageNongsan.setImageResource(R.drawable.raucaixanh);
        holder.tenNongsan.setText(listNongSan.get(position).getTenNS());
        holder.motangan.setText(listNongSan.get(position).getMoTaNS());
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("VND"));
        holder.gia.setText(format.format((long)listNongSan.get(position).getGia()));
    }

    @Override
    public int getItemCount() {
        return listNongSan.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNongsan;
        TextView tenNongsan;
        TextView motangan;
        TextView gia;
        public MyViewHolder(View itemView) {
            super((itemView));
            imageNongsan = itemView.findViewById(R.id.imageSearchNongSan);
            tenNongsan = itemView.findViewById(R.id.tenNongsan);
            motangan = itemView.findViewById(R.id.mieutaNongsan);
            gia = itemView.findViewById(R.id.searchgiann);
        }
    }
}
