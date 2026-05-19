package com.codequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        Button btnIrMapa = findViewById(R.id.btnIrMapa);
        btnIrMapa.setOnClickListener(v -> {
            Intent intent = new Intent(TutorialActivity.this, MapaActivity.class);
            startActivity(intent);
        });
    }
}