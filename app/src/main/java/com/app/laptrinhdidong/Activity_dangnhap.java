package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.mailapi.ForgotPassword;
import com.app.laptrinhdidong.model.KhachHang;

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
    TextView quenmatkhau;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        btnDangNhap = (Button) findViewById(R.id.btnDangnhap);
        tvDangky = (TextView) findViewById(R.id.dangky);

        quenmatkhau = (TextView) findViewById(R.id.quenmatkhau);

        tenDangNhapInPut = findViewById(R.id.tenDangNhapInPut);
        matkhauInput = findViewById(R.id.matkhauInput);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = tenDangNhapInPut.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(email.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (email.trim().matches(emailPattern)) {
                        CallApi();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_dangnhap.this, activity_dangky.class));
            }
        });

        quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_dangnhap.this, ForgotPassword.class));
            }
        });
    }

    void CallApi(){
        ApiService.apiService.getAllKhachHang().enqueue(new Callback<ArrayList<KhachHang>>() {
            @Override
            public void onResponse(Call<ArrayList<KhachHang>> call, Response<ArrayList<KhachHang>> response) {
                khachHangs = response.body();

                for (int i = 0; i < khachHangs.size(); i++) {
                    if (tenDangNhapInPut.getText().toString().equals(khachHangs.get(i).getEmail())
                            && matkhauInput.getText().toString().equals(khachHangs.get(i).getMatkhau())) {
                        Toast.makeText(Activity_dangnhap.this, "Đăng nhập thanh công", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("idKhachHang", khachHangs.get(i).getId());
                        editor.putString("chiTietDiaChi", khachHangs.get(i).getHoTenKH()+","+khachHangs.get(i).getChitietdiachi());
                        editor.apply();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(Activity_dangnhap.this,activity_profile.class));
                        return;
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