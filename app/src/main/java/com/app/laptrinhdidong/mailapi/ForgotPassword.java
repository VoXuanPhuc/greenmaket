package com.app.laptrinhdidong.mailapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.Activity_dangnhap;
import com.app.laptrinhdidong.R;
import com.app.laptrinhdidong.activity_dangky;
import com.app.laptrinhdidong.model.KhachHang;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    EditText editTextEmail;
    TextView dangnhap;
    TextView dangky;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editTextEmail = findViewById(R.id.email);
        dangnhap = findViewById(R.id.dangnhap);
        dangky = findViewById(R.id.dangky);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        Button buttonbtngetpass = (Button) findViewById(R.id.btngetpass);

        buttonbtngetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                CallApi();
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, Activity_dangnhap.class);
                startActivity(intent);
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, activity_dangky.class);
                startActivity(intent);
            }
        });
    }
    private void senEmail(String password) {
        String mEmail = editTextEmail.getText().toString();
        String mSubject = "Đây là mật khẩu của bạn từ GreenMarket";
        
        String mMessage = "Xin chào bạn, đây là mật khẩu truy cập ứng dụng greenmarket của bạn trên ứng dụng của chúng tôi : ";
        mMessage += password;

        javaMailapi javamailapi = new javaMailapi(this, mEmail, mSubject, mMessage);

        javamailapi.execute();
        progressBar.setVisibility(View.GONE);

        Toast.makeText(ForgotPassword.this, "Da gui mat kh", Toast.LENGTH_SHORT).show();
    }

    void CallApi() {
        ApiService.apiService.getAllKhachHang().enqueue(new Callback<ArrayList<KhachHang>>() {
            @Override
            public void onResponse(Call<ArrayList<KhachHang>> call, Response<ArrayList<KhachHang>> response) {
                ArrayList<KhachHang> khachHangs = response.body();

                for (int i = 0; i < khachHangs.size(); i++) {
                    if (editTextEmail.getText().toString().equals(khachHangs.get(i).getEmail())) {
                        senEmail(khachHangs.get(i).getMatkhau());
                        Intent intent = new Intent(ForgotPassword.this, Activity_dangnhap.class);
                        startActivity(intent);
                        return;
                    }
                }
                progressBar.setVisibility(View.GONE);

                Toast.makeText(ForgotPassword.this, "Email khong ton tai ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<KhachHang>> call, Throwable t) {
                Toast.makeText(ForgotPassword.this, "Call API that bai ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void finish(View view) {
        finish();
    }
}