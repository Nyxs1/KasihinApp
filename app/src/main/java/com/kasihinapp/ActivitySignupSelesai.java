package com.kasihinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ActivitySignupSelesai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup4_selesai);

        Button buttonBeranda = findViewById(R.id.buttonBeranda);

        buttonBeranda.setOnClickListener(v -> {
            // --- INI BAGIAN YANG DIUBAH ---
            // Buat intent untuk pindah ke HomePage, bukan lagi ke ActivityLogin1
            Intent intent = new Intent(ActivitySignupSelesai.this, HomePage.class);

            // Flag ini penting untuk menghapus semua activity sebelumnya dari stack,
            // agar pengguna tidak bisa kembali ke alur pendaftaran.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Tutup activity ini
        });
    }
}
