package com.codequest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        LinearLayout layoutFases = findViewById(R.id.layoutFases);
        TextView tvFasesConcluidas = findViewById(R.id.tvFasesConcluidas);

        Button btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MapaActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.getFases();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));

            Button btnFase = new Button(this);
            btnFase.setText("Fase " + id + ": " + titulo);
            btnFase.setBackgroundColor(0xFF7c4dff);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16);
            params.width = 600;
            btnFase.setLayoutParams(params);
            btnFase.setPadding(24, 16, 24, 16);
            btnFase.setTextColor(0xFFFFFFFF);

            btnFase.setOnClickListener(v -> {
                Intent intent = new Intent(MapaActivity.this, AulaActivity.class);
                intent.putExtra("fase_id", id);
                startActivity(intent);
            });

            layoutFases.addView(btnFase);
        }

        cursor.close();
    }
}