package com.codequest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        GridLayout layoutFases = findViewById(R.id.layoutFases);

        Button btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MapaActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        int usuarioId = getSharedPreferences("codequest", MODE_PRIVATE).getInt("usuario_id", 0);
        DatabaseHelper db = new DatabaseHelper(this);

        Cursor concluidas = db.getFasesConcluidas(usuarioId);
        int fasesDesbloqueadas = 1;
        while (concluidas.moveToNext()) {
            int faseConcluida = concluidas.getInt(concluidas.getColumnIndexOrThrow("fase_id"));
            if (faseConcluida >= fasesDesbloqueadas) {
                fasesDesbloqueadas = faseConcluida + 1;
            }
        }
        concluidas.close();

        Cursor cursor = db.getFases();
        int i = 0;

        while (cursor.moveToNext()) {
            final int faseId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));

            boolean concluida = faseId < fasesDesbloqueadas;
            boolean desbloqueada = faseId <= fasesDesbloqueadas;

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setGravity(android.view.Gravity.CENTER);
            card.setPadding(24, 32, 24, 32);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.columnSpec = GridLayout.spec(i % 2, 1f);
            params.rowSpec = GridLayout.spec(i / 2);
            params.setMargins(12, 12, 12, 12);
            card.setLayoutParams(params);

            if (concluida) {
                card.setBackgroundColor(Color.parseColor("#0a2a0a"));
            } else if (desbloqueada) {
                card.setBackgroundColor(Color.parseColor("#1a1a3e"));
            } else {
                card.setBackgroundColor(Color.parseColor("#111111"));
            }

            TextView tvIcone = new TextView(this);
            tvIcone.setTextSize(28);
            tvIcone.setGravity(android.view.Gravity.CENTER);
            tvIcone.setPadding(0, 0, 0, 8);

            if (concluida) tvIcone.setText("📜");
            else if (desbloqueada) tvIcone.setText("⚔️");
            else tvIcone.setText("🔒");

            TextView tvNum = new TextView(this);
            tvNum.setText("FASE 0" + faseId);
            tvNum.setTextSize(8);
            tvNum.setGravity(android.view.Gravity.CENTER);

            if (concluida) tvNum.setTextColor(Color.parseColor("#4caf50"));
            else if (desbloqueada) tvNum.setTextColor(Color.parseColor("#7c4dff"));
            else tvNum.setTextColor(Color.parseColor("#444444"));

            TextView tvTitulo = new TextView(this);
            tvTitulo.setText(titulo.toUpperCase());
            tvTitulo.setTextSize(10);
            tvTitulo.setGravity(android.view.Gravity.CENTER);
            tvTitulo.setPadding(0, 4, 0, 8);

            if (concluida) tvTitulo.setTextColor(Color.parseColor("#4caf50"));
            else if (desbloqueada) tvTitulo.setTextColor(Color.parseColor("#ffffff"));
            else tvTitulo.setTextColor(Color.parseColor("#444444"));

            TextView tvBadge = new TextView(this);
            tvBadge.setTextSize(8);
            tvBadge.setGravity(android.view.Gravity.CENTER);
            tvBadge.setPadding(12, 4, 12, 4);

            if (concluida) {
                tvBadge.setText("✓ completa");
                tvBadge.setTextColor(Color.parseColor("#4caf50"));
                tvBadge.setBackgroundColor(Color.parseColor("#0a2a0a"));
            } else if (desbloqueada) {
                tvBadge.setText("▶ jogar");
                tvBadge.setTextColor(Color.parseColor("#aa88ff"));
                tvBadge.setBackgroundColor(Color.parseColor("#2a1a6e"));
            } else {
                tvBadge.setText("bloqueada");
                tvBadge.setTextColor(Color.parseColor("#444444"));
                tvBadge.setBackgroundColor(Color.parseColor("#1a1a1a"));
            }

            card.addView(tvIcone);
            card.addView(tvNum);
            card.addView(tvTitulo);
            card.addView(tvBadge);

            if (desbloqueada) {
                card.setOnClickListener(v -> {
                    Intent intent = new Intent(MapaActivity.this, AulaActivity.class);
                    intent.putExtra("fase_id", faseId);
                    startActivity(intent);
                });
            } else {
                card.setOnClickListener(v ->
                        Toast.makeText(this, "Complete a fase anterior primeiro!", Toast.LENGTH_SHORT).show()
                );
            }

            layoutFases.addView(card);
            i++;
        }

        cursor.close();
    }
}