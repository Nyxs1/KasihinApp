package com.kasihinapp; // Sesuaikan dengan package Anda

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; // <<< DITAMBAHKAN
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityTipeakun extends AppCompatActivity {

    // Deklarasi variabel
    private LinearLayout buttonPerson, buttonCompany;
    private Button buttonNext;
    private LinearLayout buttonPilihBank; // <<< DITAMBAHKAN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2_tipeakun);

        // Inisialisasi semua view
        buttonPerson = findViewById(R.id.buttonPerson);
        buttonCompany = findViewById(R.id.buttonCompany);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPilihBank = findViewById(R.id.buttonPilihBank); // <<< DITAMBAHKAN

        // Setup untuk pilihan tipe akun
        buttonPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Saat dipilih, set isSelected jadi true. Child (ImageButton) akan ikut berubah
                buttonPerson.setSelected(true);
                buttonCompany.setSelected(false);
            }
        });

        buttonCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCompany.setSelected(true);
                buttonPerson.setSelected(false);
            }
        });

        // Setup tombol selanjutnya
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Untuk sementara, hanya tampilkan pesan
                Toast.makeText(ActivityTipeakun.this, "Pindah ke halaman Selesai!", Toast.LENGTH_SHORT).show();
            }
        });

        // --- INI BAGIAN BARUNYA ---
        // Setup untuk tombol pilih bank
        buttonPilihBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTipeakun.this, ActivityPilihbank.class);
                startActivity(intent);
            }
        });


        // Set pilihan default
        buttonPerson.setSelected(true);
    }
}