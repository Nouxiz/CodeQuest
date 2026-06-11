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
        int xpNoNivelAtual = xpTotal % 50;
        int xpProximoNivel = 50 - xpNoNivelAtual;


        TextView tvNivel = findViewById(R.id.tvNivelPerfil);
        tvNivel.setText("Nível " + nivel);


        TextView tvBadge = findViewById(R.id.tvBadgeNivel);
        tvBadge.setText("LVL " + nivel);


        ProgressBar progressXP = findViewById(R.id.progressXP);
        progressXP.setMax(50);
        progressXP.setProgress(xpNoNivelAtual);


        TextView tvXPNumerico = findViewById(R.id.tvXPNumerico);
        int xpParaProximoAbsoluto = nivel * 50;
        tvXPNumerico.setText(xpTotal + "/" + xpParaProximoAbsoluto);


        TextView tvXPInfo = findViewById(R.id.tvXPInfo);
        tvXPInfo.setText("faltam " + xpProximoNivel + " XP para o nível " + (nivel + 1));


        TextView tvFasesConcluidas = findViewById(R.id.tvAulas);
        int fasesConcluidas = xpTotal / 10;
        tvFasesConcluidas.setText(String.valueOf(fasesConcluidas));


        Button btnVoltar = findViewById(R.id.btnVoltarMapa);
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, MapaActivity.class);
            startActivity(intent);
        });

        String nome = getSharedPreferences("codequest", MODE_PRIVATE).getString("usuario_nome", "Usuário");
        TextView tvNome = findViewById(R.id.tvNomeUsuario);
        tvNome.setText(nome);
    }
}