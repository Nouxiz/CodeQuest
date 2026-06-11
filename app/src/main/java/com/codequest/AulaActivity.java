package com.codequest;

import android.content.Intent;
import android.widget.Button;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        final int faseId = getIntent().getIntExtra("fase_id", 0);
        DatabaseHelper db = new DatabaseHelper(this);

        Cursor cursorFase = db.getFase(faseId);
        if (cursorFase.moveToFirst()) {
            String titulo = cursorFase.getString(cursorFase.getColumnIndexOrThrow("titulo"));
            TextView tvTituloAula = findViewById(R.id.tvTituloAula);
            tvTituloAula.setText("Aula " + faseId + ":");
            TextView tvSubtitulo = findViewById(R.id.tvSubtitulo);
            tvSubtitulo.setText(titulo);
        }

        Cursor cursor = db.getAula(faseId);
        if (cursor.moveToFirst()) {
            String conteudo = cursor.getString(cursor.getColumnIndexOrThrow("conteudo_teorico"));
            TextView tvConteudo = findViewById(R.id.tvConteudo);
            tvConteudo.setText(conteudo);
        }

        Button btnProximo = findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(v -> {
            Intent intent = new Intent(AulaActivity.this, ExercicioActivity.class);
            intent.putExtra("fase_id", faseId);
            startActivity(intent);
        });
    }
}