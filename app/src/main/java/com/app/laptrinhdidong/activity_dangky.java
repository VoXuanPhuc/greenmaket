package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.KhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_dangky extends AppCompatActivity {
    TextView tvcotk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        tvcotk = (TextView) findViewById(R.id.cotaikhoan);

        tvcotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_dangky.this, Activity_dangnhap.class));
            }
        });

        Button button = findViewById(R.id.btnDangKy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText hoTen = findViewById(R.id.edthoten);
                EditText edtmatkhau = findViewById(R.id.edtmatkhau);
                EditText edtemail  =findViewById(R.id.edtemail);
                EditText edtsdt = findViewById(R.id.edtsdt);
                EditText edtngaysinh = findViewById(R.id.edtngaysinh);
                EditText edtgioitinh  =findViewById(R.id.edtgioitinh);

                KhachHang  khachHang = new KhachHang( );
                khachHang.setHoTenKH(hoTen.getText().toString());
                khachHang.setEmail(edtemail.getText().toString());
                khachHang.setMatkhau(edtmatkhau.getText().toString());
                khachHang.setSdt(edtsdt.getText().toString());
                khachHang.setNgaysinh(edtngaysinh.getText().toString()+"T03:11:18.255Z");
                khachHang.setGioitinh(edtgioitinh.getText().toString());
                khachHang.setTenDangNhap(edtemail.getText().toString());
                khachHang.setId(null);
                callAPIMPost(khachHang);
            }
        });
    }


    void callAPIMPost(KhachHang khachHang){
        ApiService.apiService.postKhachHang(khachHang).enqueue(
                new Callback<KhachHang>() {
                    @Override
                    public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                        if(response.body() !=null){
                            startActivity(new Intent(activity_dangky.this,Activity_dangnhap.class));
                        }
                        KhachHang khachHang1 = response.body();
                        Toast.makeText(activity_dangky.this, "DDawng ki thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<KhachHang> call, Throwable t) {
                        Toast.makeText(activity_dangky.this, "DDawng ki that bai", Toast.LENGTH_SHORT).show();

                    }
                }
        );

    }
}