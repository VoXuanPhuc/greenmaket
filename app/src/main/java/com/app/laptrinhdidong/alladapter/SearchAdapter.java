package com.app.laptrinhdidong.alladapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.R;
import com.app.laptrinhdidong.activity_chitietnongsan;
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
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenNongsan.setText(listNongSan.get(position).getTenNS());
        NumberFormat format = NumberFormat.getCurrencyInstance();

        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("VND"));
        holder.gia.setText(format.format((long)listNongSan.get(position).getGia()));


        try {
            ApiService.apiService.getAnhNongSanByIdKhachHang(listNongSan.get(position).getId())
                    .enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();

                    if (anhNongSans.size() != 0) {
                        Picasso.with(context).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.isloading)
                                .into(holder.imageNongsan);
                    }else{
                        AnhNongSan anhNongSan = new AnhNongSan();
                        anhNongSan.setTen("");

                        anhNongSans.add(anhNongSan);
                    }
                    setOnClickForItemListView(holder.item_listViewSearch, listNongSan.get(position), anhNongSans.get(0));



                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return listNongSan.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNongsan;
        TextView tenNongsan;
//        TextView motangan;
        LinearLayout item_listViewSearch;
        TextView gia;
        public MyViewHolder(View itemView) {
            super((itemView));
            item_listViewSearch = itemView.findViewById(R.id.item_listViewSearch);
            imageNongsan = itemView.findViewById(R.id.imageSearchNongSan);
            tenNongsan = itemView.findViewById(R.id.tenNongsan);
//            motangan = itemView.findViewById(R.id.mieutaNongsan);
            gia = itemView.findViewById(R.id.searchgiann);
        }
    }

    public void setOnClickForItemListView(LinearLayout item_listViewSearch,NongSan nongSan,AnhNongSan anhNongSan){
    item_listViewSearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,activity_chitietnongsan.class);
            intent.putExtra("tenNS", nongSan.getTenNS());
            intent.putExtra("moTaNS", nongSan.getMoTaNS());
            intent.putExtra("gia", nongSan.getGia());
            intent.putExtra("maNS",nongSan.getId());
            intent.putExtra("ID",nongSan.getId());
            intent.putExtra("URL",anhNongSan.getTen());
            context.startActivity(intent);
        }
    });
    }

}
