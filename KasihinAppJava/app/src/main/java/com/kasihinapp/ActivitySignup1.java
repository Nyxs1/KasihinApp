package com.kasihinapp; // Sesuaikan dengan package Anda

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent; // <<< DITAMBAHKAN
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivitySignup1 extends AppCompatActivity {

    private EditText editTextDate;
    private ImageButton buttonMale, buttonFemale;
    private Button buttonNext;
    private final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup1_identitas);

        // Inisialisasi semua view
        editTextDate = findViewById(R.id.editTextDate);
        buttonMale = findViewById(R.id.buttonMale);
        buttonFemale = findViewById(R.id.buttonFemale);
        buttonNext = findViewById(R.id.buttonNext);

        // Setup untuk DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivitySignup1.this, dateSetListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Setup untuk pilihan jenis kelamin
        buttonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMale.setSelected(true);
                buttonFemale.setSelected(false);
            }
        });

        buttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFemale.setSelected(true);
                buttonMale.setSelected(false);
            }
        });

        // Setup tombol selanjutnya
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kode Toast yang lama dihapus
                // Toast.makeText(ActivitySignup1.this, "Tombol Selanjutnya Diklik", Toast.LENGTH_SHORT).show();

                // Kode baru untuk pindah ke ActivityTipeakun
                Intent intent = new Intent(ActivitySignup1.this, ActivityTipeakun.class);
                startActivity(intent);
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; // Format tanggal
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }
}