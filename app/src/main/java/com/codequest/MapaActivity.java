package com.codequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapaActivity extends AppCompatActivity {

    int[] posX = {170, 180, 195, 80, 100};
    int[] posY = {800, 580, 420, 290, 130};
    String[] titulos = {"Lógica", "Algoritmos", "Variáveis", "Condicionais", "Loops"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        FrameLayout layoutFases = findViewById(R.id.layoutFases);
        TextView tvFasesConcluidas = findViewById(R.id.tvFasesConcluidas);

        Button btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MapaActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        float density = getResources().getDisplayMetrics().density;

        for (int i = 0; i < titulos.length; i++) {
            final int faseId = i + 1;

            Button btnFase = new Button(this);
            btnFase.setBackgroundResource(R.drawable.bolinha_fase);
            btnFase.setTextColor(0xFFFFFFFF);
            btnFase.setTextSize(8);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    (int)(50 * density),
                    (int)(38 * density)
            );
            params.leftMargin = (int)(posX[i] * density);
            params.topMargin = (int)(posY[i] * density);
            btnFase.setLayoutParams(params);

            btnFase.setOnClickListener(v -> {
                Intent intent = new Intent(MapaActivity.this, AulaActivity.class);
                intent.putExtra("fase_id", faseId);
                startActivity(intent);
            });

            layoutFases.addView(btnFase);
        }
    }
}