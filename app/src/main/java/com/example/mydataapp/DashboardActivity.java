package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    TextView txtWelcome;

    EditText etNim, etNama, etProdi,
            etKelas, etAlamat, etEmail;

    Button btnTambah, btnLogout;

    ListView listView;

    ArrayList<String> dataList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);

        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);

        listView = findViewById(R.id.listView);

        String username =
                getIntent().getStringExtra("username");

        txtWelcome.setText(
                "Selamat Datang " + username);

        dataList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );

        listView.setAdapter(adapter);

        btnTambah.setOnClickListener(v -> {

            String data =
                    "NIM : " + etNim.getText().toString()
                            + "\nNama : " + etNama.getText().toString()
                            + "\nProdi : " + etProdi.getText().toString()
                            + "\nKelas : " + etKelas.getText().toString()
                            + "\nAlamat : " + etAlamat.getText().toString()
                            + "\nEmail : " + etEmail.getText().toString();

            dataList.add(data);

            adapter.notifyDataSetChanged();

            Toast.makeText(this,
                    "Data berhasil ditambah",
                    Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {

            SharedPreferences sharedPreferences =
                    getSharedPreferences("login",
                            MODE_PRIVATE);

            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.clear();
            editor.apply();

            Intent intent =
                    new Intent(DashboardActivity.this,
                            MainActivity.class);

            startActivity(intent);

            finish();
        });
    }
}