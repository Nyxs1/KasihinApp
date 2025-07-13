package com.kasihinapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityPilihbank extends AppCompatActivity {

    // Launcher untuk menerima hasil dari ActivityPilihPembayaran
    private final ActivityResultLauncher<Intent> pembayaranLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Cek apakah hasilnya OK dan ada data
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Jika ya, teruskan data hasil ini kembali ke activity sebelumnya (ActivityTipeakun)
                        setResult(RESULT_OK, result.getData());
                        finish(); // Tutup halaman menu ini
                    }
                    // Jika hasilnya CANCEL (misalnya menekan tombol back), tidak ada yang dilakukan
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup3_pilihbank);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RelativeLayout buttonBank = findViewById(R.id.buttonMobileBanking);
        RelativeLayout buttonEwallet = findViewById(R.id.buttonEwallet);

        buttonBank.setOnClickListener(v -> {
            // Buka halaman daftar dengan tipe BANK
            Intent intent = new Intent(ActivityPilihbank.this, ActivityPilihPembayaran.class);
            intent.putExtra(ActivityPilihPembayaran.EXTRA_TIPE, ActivityPilihPembayaran.TIPE_BANK);
            pembayaranLauncher.launch(intent); // Gunakan launcher
        });

        buttonEwallet.setOnClickListener(v -> {
            // Buka halaman daftar dengan tipe EWALLET
            Intent intent = new Intent(ActivityPilihbank.this, ActivityPilihPembayaran.class);
            intent.putExtra(ActivityPilihPembayaran.EXTRA_TIPE, ActivityPilihPembayaran.TIPE_EWALLET);
            pembayaranLauncher.launch(intent); // Gunakan launcher
        });
    }
}
