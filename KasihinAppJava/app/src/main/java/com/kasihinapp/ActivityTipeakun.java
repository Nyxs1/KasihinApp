package com.kasihinapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ActivityTipeakun extends AppCompatActivity {

    // Deklarasi Variabel
    private LinearLayout buttonPerson, buttonCompany, buttonPilihBank;
    private Button buttonNext;
    private EditText editTextDisplayName, editTextNoRekening;
    private ImageView rekeningLogoImageView;
    private TextView rekeningNamaTextView, rekeningDeskripsiTextView;

    // Launcher untuk menerima hasil dari ActivityPilihbank (yang meneruskan hasil dari ActivityPilihPembayaran)
    private final ActivityResultLauncher<Intent> pilihPembayaranLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String namaBank = data.getStringExtra(ActivityPilihPembayaran.EXTRA_NAMA_BANK);
                        String deskripsiBank = data.getStringExtra(ActivityPilihPembayaran.EXTRA_DESKRIPSI_BANK);
                        int logoId = data.getIntExtra(ActivityPilihPembayaran.EXTRA_LOGO_ID, R.drawable.ic_bca_logo);

                        // Update UI dengan data yang diterima
                        rekeningNamaTextView.setText(namaBank);
                        rekeningDeskripsiTextView.setText(deskripsiBank);
                        rekeningLogoImageView.setImageResource(logoId);

                        // Setelah memilih bank, cek lagi validasinya
                        validateFields();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2_tipeakun);

        // Inisialisasi semua view
        buttonPerson = findViewById(R.id.buttonPerson);
        buttonCompany = findViewById(R.id.buttonCompany);
        buttonPilihBank = findViewById(R.id.buttonPilihBank);
        buttonNext = findViewById(R.id.buttonNext);
        editTextDisplayName = findViewById(R.id.editTextDisplayName);
        editTextNoRekening = findViewById(R.id.editTextNoRekening);
        rekeningLogoImageView = findViewById(R.id.rekeningLogoImageView);
        rekeningNamaTextView = findViewById(R.id.rekeningNamaTextView);
        rekeningDeskripsiTextView = findViewById(R.id.rekeningDeskripsiTextView);

        // Setup OnClickListener
        setupClickListeners();

        // Setup TextWatcher untuk validasi form
        setupTextWatchers();

        // Set kondisi awal tombol
        validateFields();
        buttonPerson.setSelected(true); // Set pilihan default
    }

    private void setupClickListeners() {
        buttonPerson.setOnClickListener(v -> {
            buttonPerson.setSelected(true);
            buttonCompany.setSelected(false);
        });

        buttonCompany.setOnClickListener(v -> {
            buttonCompany.setSelected(true);
            buttonPerson.setSelected(false);
        });

        buttonPilihBank.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityTipeakun.this, ActivityPilihbank.class);
            pilihPembayaranLauncher.launch(intent);
        });

        // --- INI BAGIAN YANG DIUBAH ---
        buttonNext.setOnClickListener(v -> {
            // Hapus Toast yang lama
            // Toast.makeText(ActivityTipeakun.this, "Data Lengkap, Proses Selanjutnya!", Toast.LENGTH_SHORT).show();

            // Tambahkan kode ini untuk pindah ke halaman Selesai
            Intent intent = new Intent(ActivityTipeakun.this, ActivitySignupSelesai.class);
            startActivity(intent);
            // Panggil finish() agar pengguna tidak bisa kembali ke halaman ini dari halaman Selesai
            finish();
        });
    }

    private void setupTextWatchers() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateFields();
            }
        };

        editTextDisplayName.addTextChangedListener(textWatcher);
        editTextNoRekening.addTextChangedListener(textWatcher);
    }

    private void validateFields() {
        String displayName = editTextDisplayName.getText().toString().trim();
        String noRekening = editTextNoRekening.getText().toString().trim();

        if (!displayName.isEmpty() && !noRekening.isEmpty()) {
            // Jika semua field terisi
            buttonNext.setEnabled(true);
            buttonNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
        } else {
            // Jika ada field yang kosong
            buttonNext.setEnabled(false);
            buttonNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.grey)));
        }
    }
}
