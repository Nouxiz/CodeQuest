package com.codequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvCadastro = findViewById(R.id.tvCadastro);
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etSenha = findViewById(R.id.etSenha);

        DatabaseHelper db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String email = etUsuario.getText().toString();
            String senha = etSenha.getText().toString();

            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.loginUsuario(email, senha)) {
                int usuarioId = db.getUsuarioId(email);

                getSharedPreferences("codequest", MODE_PRIVATE)
                        .edit()
                        .putInt("usuario_id", usuarioId)
                        .apply();

                Intent intent = new Intent(LoginActivity.this, TutorialActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }
        });

        tvCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }
}