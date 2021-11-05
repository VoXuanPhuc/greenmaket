package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editProfile extends AppCompatActivity {

    Button btnEditProfile;
    EditText eName;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent getIntent = getIntent();
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        eName = (EditText) findViewById(R.id.edit_name);
        username = (TextView) findViewById(R.id.username);

        username.setText(getIntent.getStringExtra("username"));
        eName.setText(getIntent.getStringExtra("username"));

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editProfile.this, activity_profile.class);
                intent.putExtra("name", eName.getText().toString());
                startActivity(intent);
            }
        });
    }
}