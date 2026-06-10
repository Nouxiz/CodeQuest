package com.codequest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        int faseId = getIntent().getIntExtra("fase_id", 0);
        boolean acertou = getIntent().getBooleanExtra("acertou", false);

        DatabaseHelper db = new DatabaseHelper(this);
        int usuarioId = getSharedPreferences("codequest", MODE_PRIVATE).getInt("usuario_id", 0);
        db.salvarProgresso(usuarioId, faseId);

        TextView tvFaseConcluida = findViewById(R.id.tvFaseConcluida);
        tvFaseConcluida.setText("FASE CONCLUÍDA!");

        Cursor cursor = db.getFase(faseId);
        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            TextView tvTitulo = findViewById(R.id.tvAprendeu);
            tvTitulo.setText("VOCÊ APRENDEU: " + titulo);
        }

        Button btnProximo = findViewById(R.id.btnProximaFase);
        btnProximo.setOnClickListener(v -> {
            Intent intent = new Intent(ResultadoActivity.this, MapaActivity.class);
            startActivity(intent);
        });
        }
    }