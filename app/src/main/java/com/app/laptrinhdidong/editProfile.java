package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.laptrinhdidong.API.ApiService;
import com.app.laptrinhdidong.model.KhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editProfile extends AppCompatActivity {

    KhachHang khachHang = new KhachHang();
    Button btnEditProfile;
    EditText eName;
    EditText sdt;
    EditText diachi;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent getIntent = getIntent();
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        eName = (EditText) findViewById(R.id.edit_name);
        sdt = (EditText) findViewById(R.id.sdt);
        diachi = (EditText) findViewById(R.id.email);
        email = (EditText) findViewById(R.id.diachi);

        eName.setText(getIntent.getStringExtra("username"));
        sdt.setText(activity_profile.khachHang.getSdt());
        diachi.setText(activity_profile.khachHang.getChitietdiachi());
        email.setText(activity_profile.khachHang.getEmail());

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                khachHang.setId(activity_profile.khachHang.getId());
                khachHang.setHoTenKH(eName.getText().toString());
                khachHang.setTenDangNhap(activity_profile.khachHang.getTenDangNhap());
                khachHang.setMatkhau(activity_profile.khachHang.getMatkhau());
                khachHang.setSdt(sdt.getText().toString());
                khachHang.setEmail(email.getText().toString());
                khachHang.setDiaChi(activity_profile.khachHang.getDiaChi());
                khachHang.setNgaysinh(activity_profile.khachHang.getNgaysinh());
                khachHang.setChitietdiachi(diachi.getText().toString());
                khachHang.setGioitinh(activity_profile.khachHang.getGioitinh());

                Toast.makeText(editProfile.this, "da call", Toast.LENGTH_SHORT).show();

                System.out.println("aaaaaaaaaaaaaaaaaaaaa" + khachHang.toString());
                ApiService.apiService.updateKhachhangById((long) 1702, khachHang).enqueue(
                        new Callback<KhachHang>() {
                            @Override
                            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                                if (response.isSuccessful()) {
                                    KhachHang khachHang = response.body();
                                    eName.setText(khachHang.getHoTenKH());
                                    sdt.setText(activity_profile.khachHang.getSdt());
                                    diachi.setText(activity_profile.khachHang.getChitietdiachi());
                                    email.setText(activity_profile.khachHang.getEmail());
                                    Toast.makeText(editProfile.this, "success", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<KhachHang> call, Throwable t) {
                                Toast.makeText(editProfile.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

//                Intent intent = new Intent(editProfile.this, activity_profile.class);
//                startActivity(intent);

            }
        });
    }
}