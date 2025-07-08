package com.kasihinapp; // Pastikan package ini sesuai dengan proyek Anda

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; // Import kelas Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; // Import kelas TextView
import android.widget.Toast;

public class ActivityLogin1 extends AppCompatActivity {

    // Deklarasikan komponen yang akan berinteraksi
    private Button googleSignInButton;
    private TextView textViewSignUp; // Tambahkan variabel untuk TextView SIGN UP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_1);

        // Menghubungkan variabel dengan komponen di layout menggunakan ID
        googleSignInButton = findViewById(R.id.googleSignInButton);
        textViewSignUp = findViewById(R.id.textViewSignUp); // Hubungkan variabel dengan TextView

        // Aksi untuk tombol Login
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityLogin1.this, "Tombol Login Diklik!", Toast.LENGTH_SHORT).show();
            }
        });

        // --- INI BAGIAN BARUNYA ---
        // Menambahkan aksi saat teks "SIGN UP" diklik
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Intent untuk pindah dari activity ini ke ActivitySignup1
                Intent intent = new Intent(ActivityLogin1.this, ActivitySignup1.class);
                startActivity(intent); // Mulai activity baru
            }
        });
    }
}