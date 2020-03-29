package com.moataz.developer.ncittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sign,login;

        sign = findViewById(R.id.sign);
        sign.setOnClickListener(v -> {
            Intent goSign = new Intent(MainActivity.this,SignActivity.class);
            startActivity(goSign);
            finish();
        });

        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            Intent goLogin = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(goLogin);
            finish();
        });
    }
}
