package com.codequest;

import android.widget.Button;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExercicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        int faseId = getIntent().getIntExtra("fase_id", 0);

        DatabaseHelper db = new DatabaseHelper(this);

        Cursor cursor = db.getExercicio(faseId);
        if (cursor.moveToFirst()) {
            String pergunta = cursor.getString(cursor.getColumnIndexOrThrow("pergunta"));
            TextView tvPergunta = findViewById(R.id.tvPergunta);
            tvPergunta.setText(pergunta);
            int exercicioId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));

            Cursor opcoes = db.getOpcoes(exercicioId);
            Button btnOpcaoA = findViewById(R.id.btnOpcaoA);
            Button btnOpcaoB = findViewById(R.id.btnOpcaoB);
            Button btnOpcaoC = findViewById(R.id.btnOpcaoC);
            Button btnOpcaoD = findViewById(R.id.btnOpcaoD);

            Button[] botoes = {btnOpcaoA, btnOpcaoB, btnOpcaoC, btnOpcaoD};
            int i = 0;

            final boolean[] acertou = {false};
            final String[] respostaSelecionada = {""};
            final Button[] botaoSelecionado = {null};

            while (opcoes.moveToNext() && i < 4) {
                String textoOpcao = opcoes.getString(opcoes.getColumnIndexOrThrow("texto"));
                int correta = opcoes.getInt(opcoes.getColumnIndexOrThrow("correta"));
                botoes[i].setText(textoOpcao);
                final boolean isCorreta = correta == 1;
                final Button botaoAtual = botoes[i];

                botoes[i].setOnClickListener(v -> {

                    for (Button b : botoes) {
                        b.setBackgroundTintList(ColorStateList.valueOf(0xFF1e1e3a));
                        b.setTextColor(0xFFcccccc);
                    }


                    botaoAtual.setBackgroundTintList(ColorStateList.valueOf(0xFF2e7d32));
                    botaoAtual.setTextColor(0xFFffffff);

                    botaoSelecionado[0] = botaoAtual;
                    acertou[0] = isCorreta;
                    respostaSelecionada[0] = textoOpcao;
                });
                i++;
            }

            Button btnConfirmar = findViewById(R.id.btnConfirmar);
            btnConfirmar.setOnClickListener(v -> {
                if (respostaSelecionada[0].isEmpty()) {
                    Toast.makeText(this, "Selecione uma opção!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (acertou[0]) {
                    Intent intent = new Intent(ExercicioActivity.this, ResultadoActivity.class);
                    intent.putExtra("fase_id", faseId);
                    intent.putExtra("acertou", true);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Resposta errada! Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}