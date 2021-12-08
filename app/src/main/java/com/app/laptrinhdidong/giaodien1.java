package com.app.laptrinhdidong;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class giaodien1 extends AppCompatActivity {

    ImageButton nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien1);
        ImageButton nextbtn;
        nextbtn = (ImageButton) findViewById(R.id.next);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(giaodien1.this, giaodien2.class));
            }
        });
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if(FirstTime.equals("Yes")){
            Intent intent = new Intent(giaodien1.this, DanhMucActivity.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }
    }
}