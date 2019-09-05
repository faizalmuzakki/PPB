package com.example.tugassijippb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HitungLuasActivity extends AppCompatActivity {

    Button btn_hitung;
    EditText panjang, lebar;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_hitung = findViewById(R.id.btn_hitung);
        panjang = findViewById(R.id.ed_panjang);
        lebar = findViewById(R.id.ed_lebar);
        hasil = findViewById(R.id.hasil);

        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double panjang_num = Double.parseDouble(panjang.getText().toString().trim());
                double lebar_num = Double.parseDouble(lebar.getText().toString().trim());
                double hasil_num = panjang_num * lebar_num;

                hasil.setText(Double.toString(hasil_num));
            }
        });
    }


}
