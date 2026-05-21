package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnCancel;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.isEmpty() || pass.isEmpty()){

                Toast.makeText(this,
                        "Data tidak boleh kosong",
                        Toast.LENGTH_SHORT).show();

            } else if(user.equals("admin")
                    && pass.equals("admin123")){

                SharedPreferences.Editor editor =
                        sharedPreferences.edit();

                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Intent intent =
                        new Intent(MainActivity.this,
                                DashboardActivity.class);

                intent.putExtra("username", user);

                startActivity(intent);

            } else {

                Toast.makeText(this,
                        "Username atau Password salah",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> {

            username.setText("");
            password.setText("");

        });
    }
}