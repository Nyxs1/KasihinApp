package com.kasihinapp; // Sesuaikan dengan package Anda

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class ActivityPilihbank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup3_pilihbank);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Menangani klik pada tombol kembali (navigation icon)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengakhiri activity ini dan kembali ke halaman sebelumnya
                finish();
            }
        });
    }
}