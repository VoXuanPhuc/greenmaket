package com.app.laptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class activity_chitietnongsan extends AppCompatActivity {

    ImageButton add;
    ImageButton minus;
    ImageButton love;
    TextView number;
    Integer sl;
    int tym1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietnongsan);
        tym1 = R.drawable.tym_icon;
        add = (ImageButton) findViewById(R.id.them);
        minus = (ImageButton) findViewById(R.id.giam);
        love = (ImageButton) findViewById(R.id.yeuthichbaidang);
        number = (TextView) findViewById(R.id.soluongctns);

        sl = Integer.parseInt(number.getText().toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl += 1;
                number.setText(sl.toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sl > 1) {
                    sl -= 1;
                    number.setText(sl.toString());
                } else {
                    Toast.makeText(activity_chitietnongsan.this, "Số lượng tối thiểu là 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tym1 == R.drawable.tym_icon) {
                    tym1 = R.drawable.traitim2;
                } else  {
                    tym1 = R.drawable.tym_icon;
                }
                love.setImageResource(tym1);
            }
        });

    }
}