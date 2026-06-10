package com.codequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        int usuarioId = getSharedPreferences("codequest", MODE_PRIVATE)
                .getInt("usuario_id", 0);
        DatabaseHelper db = new DatabaseHelper(this);

        int xpTotal       = db.getTotalXP(usuarioId);
        int nivel         = (xpTotal / 50) + 1;
        int xpNoNivelAtual = xpTotal % 50;          // progresso dentro do nível
        int xpProximoNivel = 50 - xpNoNivelAtual;   // quanto falta

        // --- Header: "Nível X" ---
        TextView tvNivel = findViewById(R.id.tvNivelPerfil);
        tvNivel.setText("Nível " + nivel);

        // --- Badge de nível sobreposto ao card do personagem ---
        TextView tvBadge = findViewById(R.id.tvBadgeNivel);
        tvBadge.setText("LVL " + nivel);

        // --- Barra de XP (max = 50 por nível) ---
        ProgressBar progressXP = findViewById(R.id.progressXP);
        progressXP.setMax(50);
        progressXP.setProgress(xpNoNivelAtual);

        // --- Contador numérico "50/100" (xpTotal / xpParaProximoNível absoluto) ---
        TextView tvXPNumerico = findViewById(R.id.tvXPNumerico);
        int xpParaProximoAbsoluto = nivel * 50;    // xp acumulado necessário
        tvXPNumerico.setText(xpTotal + "/" + xpParaProximoAbsoluto);

        // --- Texto auxiliar ---
        TextView tvXPInfo = findViewById(R.id.tvXPInfo);
        tvXPInfo.setText("faltam " + xpProximoNivel + " XP para o nível " + (nivel + 1));

        // --- Estatísticas ---
        TextView tvFasesConcluidas = findViewById(R.id.tvAulas);
        int fasesConcluidas = xpTotal / 10;
        tvFasesConcluidas.setText(String.valueOf(fasesConcluidas));

        // Acertos e Sequência podem ser expandidos conforme a lógica do DatabaseHelper
        // TextView tvAcertos  = findViewById(R.id.tvAcertos);
        // TextView tvSequencia = findViewById(R.id.tvSequencia);

        // --- Botão voltar ---
        Button btnVoltar = findViewById(R.id.btnVoltarMapa);
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, MapaActivity.class);
            startActivity(intent);
        });
    }
}