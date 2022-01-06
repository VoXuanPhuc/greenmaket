package com.app.laptrinhdidong;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.AnhNongSan;
import com.app.laptrinhdidong.model.Double_temp;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NongSanAdapter extends RecyclerView.Adapter<NongSanAdapter.UserViewHolder> {

    ArrayList<Double_temp> nongSans = new ArrayList<>();
    Context context;
    public void setData(ArrayList<Double_temp> nongSans,Context context) {
        this.nongSans = nongSans;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistview_sanphamtheodanhmuc, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Double_temp nongSan = nongSans.get(position);
        if (nongSan == null) {
            return;
        }
        holder.name0.setText(nongSans.get(position).nongSan0.getTenNS());
        holder.price0.setText(withLargeIntegers(nongSans.get(position).nongSan0.getGia()) );
//        holder.tv.setText(user.getId());
        ApiService.apiService.getAnhNongSanByIdKhachHang(nongSans.get(position).nongSan0.getId()).enqueue(new Callback<ArrayList<AnhNongSan>>() {
            @Override
            public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                ArrayList<AnhNongSan> anhNongSans = response.body();

                if (anhNongSans.size() != 0){
                    Picasso.with(context).load(anhNongSans.get(0).getTen())
                            .placeholder(R.drawable.loading)
                            .into(holder.image0);

                    holder.constraintLayout0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, activity_chitietnongsan.class);
                            intent.putExtra("tenNS", nongSan.nongSan0.getTenNS());
                            intent.putExtra("moTaNS", nongSan.nongSan0.getMoTaNS());
                            intent.putExtra("gia", nongSan.nongSan0.getGia());
                            intent.putExtra("maNS",nongSan.nongSan0.getId());
                            intent.putExtra("ID",nongSan.nongSan0.getId());
                            intent.putExtra("URL",anhNongSans.get(0).getTen());
                            context.startActivity(intent);

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

            }
        });











        ////////////////////////////////////////////////////////////////////////////////////////////////////
        if (nongSans.get(position).nongSan1 == null) {

            holder.constraintLayout1.setVisibility(View.GONE);
        }else{
            holder.name1.setText(nongSans.get(position).nongSan1.getTenNS());
            holder.price1.setText(withLargeIntegers(nongSans.get(position).nongSan1.getGia()));

            ApiService.apiService.getAnhNongSanByIdKhachHang(nongSans.get(position).nongSan1.getId()).enqueue(new Callback<ArrayList<AnhNongSan>>() {
                @Override
                public void onResponse(Call<ArrayList<AnhNongSan>> call, Response<ArrayList<AnhNongSan>> response) {
                    ArrayList<AnhNongSan> anhNongSans = response.body();

                    if (anhNongSans.size() != 0)
                        Picasso.with(context).load(anhNongSans.get(0).getTen())
                                .placeholder(R.drawable.loading)
                                .into(holder.image1);

                    holder.constraintLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, activity_chitietnongsan.class);
                            intent.putExtra("tenNS", nongSan.nongSan1.getTenNS());
                            intent.putExtra("moTaNS", nongSan.nongSan1.getMoTaNS());
                            intent.putExtra("gia", nongSan.nongSan1.getGia());
                            intent.putExtra("maNS",nongSan.nongSan1.getId());
                            intent.putExtra("ID",nongSan.nongSan1.getId());
                            intent.putExtra("URL",anhNongSans.get(0).getTen());
                            context.startActivity(intent);

                        }
                    });
                }

                @Override
                public void onFailure(Call<ArrayList<AnhNongSan>> call, Throwable t) {

                }
            });




        }
    }

    @Override
    public int getItemCount() {
        return nongSans.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
//        private TextView tv;

        TextView name0, price0;
        ImageView image0;
        ConstraintLayout constraintLayout0;

        TextView name1, price1;
        ImageView image1;
        ConstraintLayout constraintLayout1;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
//            tv = itemView.findViewById(R.id.textView);
            constraintLayout0 = itemView.findViewById(R.id.card0);
            name0 = itemView.findViewById(R.id.name0);
            price0 = itemView.findViewById(R.id.price0);
            image0 = itemView.findViewById(R.id.image0);



            constraintLayout1 = itemView.findViewById(R.id.card1);
            name1 = itemView.findViewById(R.id.name1);
            price1 = itemView.findViewById(R.id.price1);
            image1 = itemView.findViewById(R.id.image1);

        }
    }
    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}
