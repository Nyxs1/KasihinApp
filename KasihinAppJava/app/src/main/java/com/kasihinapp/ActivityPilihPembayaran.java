package com.kasihinapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ActivityPilihPembayaran extends AppCompatActivity {

    // Konstanta untuk Intent
    public static final String EXTRA_TIPE = "extra_tipe";
    public static final String EXTRA_NAMA_BANK = "extra_nama_bank";
    public static final String EXTRA_DESKRIPSI_BANK = "extra_deskripsi_bank";
    public static final String EXTRA_LOGO_ID = "extra_logo_id";
    public static final String TIPE_BANK = "bank";
    public static final String TIPE_EWALLET = "ewallet";

    // Adapter dideklarasikan sebagai member class agar bisa diakses di OnClickListener
    private PembayaranAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_pembayaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView sectionTitle = findViewById(R.id.sectionTitleTextView);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView cancelButton = findViewById(R.id.cancelButton);
        Button confirmButton = findViewById(R.id.confirmButton);

        String tipe = getIntent().getStringExtra(EXTRA_TIPE);
        List<MetodePembayaran> dataList = new ArrayList<>();

        if (TIPE_BANK.equals(tipe)) {
            sectionTitle.setText("Mobile Banking");
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_bca, "Bank Central Asia", "BCA"));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_mandiri, "Bank Mandiri", "Mandiri"));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_bni, "Bank Negara Indonesia", "BNI"));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_bri, "Bank Rakyat Indonesia", "BRI"));
        } else if (TIPE_EWALLET.equals(tipe)) {
            sectionTitle.setText("E-Wallet");
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_dana, "Dana", ""));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_gopay, "GoPay", ""));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_linkaja, "Link Aja!", ""));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_ovo, "OVO", ""));
            dataList.add(new MetodePembayaran(R.drawable.ic_logo_shopeepay, "Shopee Pay", ""));
        }

        // Inisialisasi adapter sebagai member class
        adapter = new PembayaranAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        cancelButton.setOnClickListener(v -> finish());

        // --- INI BAGIAN YANG DIUBAH ---
        confirmButton.setOnClickListener(v -> {
            // Dapatkan item yang dipilih dari adapter
            MetodePembayaran selected = adapter.getSelectedItem();

            if (selected != null) {
                // Jika ada yang dipilih, siapkan intent untuk hasil
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_NAMA_BANK, selected.getNama());
                resultIntent.putExtra(EXTRA_DESKRIPSI_BANK, selected.getDeskripsi());
                resultIntent.putExtra(EXTRA_LOGO_ID, selected.getLogoResId());

                // Set hasilnya menjadi OK dan kirimkan datanya
                setResult(RESULT_OK, resultIntent);
                finish(); // Tutup halaman ini
            } else {
                // Jika tidak ada yang dipilih, tampilkan pesan
                Toast.makeText(this, "Silakan pilih salah satu metode", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
