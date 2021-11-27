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
import com.app.laptrinhdidong.model.DanhMuc;
import com.app.laptrinhdidong.model.KhachHang;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_dangnhap extends AppCompatActivity {
    ArrayList<KhachHang> khachHangs;
    Button btnDangNhap;
    TextView tvDangky;
    EditText tenDangNhapInPut;
    EditText matkhauInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        btnDangNhap = (Button) findViewById(R.id.btnDangnhap);
        tvDangky = (TextView) findViewById(R.id.dangky);


        tenDangNhapInPut = findViewById(R.id.tenDangNhapInPut);
        matkhauInput = findViewById(R.id.matkhauInput);
        tenDangNhapInPut.setText("nganhan221");
        matkhauInput.setText("123456");
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallApi();
            }
        });

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_dangnhap.this, activity_dangky.class));
            }
        });
    }

    void CallApi(){
        ApiService.apiService.getAllKhachHang().enqueue(new Callback<ArrayList<KhachHang>>() {
            @Override
            public void onResponse(Call<ArrayList<KhachHang>> call, Response<ArrayList<KhachHang>> response) {
                khachHangs = response.body();

                for (int i = 0; i < khachHangs.size(); i++) {
                    if (tenDangNhapInPut.getText().toString().equals(khachHangs.get(i).getTenDangNhap())
                            && matkhauInput.getText().toString().equals(khachHangs.get(i).getMatkhau())) {
                        Toast.makeText(Activity_dangnhap.this, "Đăng nhập thanh công", Toast.LENGTH_SHORT).show();
                        activity_profile.khachHang = khachHangs.get(i);
                        startActivity(new Intent(Activity_dangnhap.this,activity_profile.class));
                    }
                }
                Toast.makeText(Activity_dangnhap.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ArrayList<KhachHang>> call, Throwable t) {
                Toast.makeText(Activity_dangnhap.this, "Call API dang nhap that bai ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}