package com.codequest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "codequest.db";
    private static final int BANCO_VERSAO = 2;

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, BANCO_VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "senha TEXT NOT NULL)");

        db.execSQL("CREATE TABLE fases (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ordem INTEGER," +
                "titulo TEXT," +
                "descricao TEXT," +
                "tipo TEXT)");

        db.execSQL("CREATE TABLE aulas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fase_id INTEGER," +
                "conteudo_teorico TEXT," +
                "imagem_url TEXT)");

        db.execSQL("CREATE TABLE exercicios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fase_id INTEGER," +
                "pergunta TEXT," +
                "tipo_resposta TEXT," +
                "resposta_correta TEXT," +
                "feedback_explicativo TEXT)");

        db.execSQL("CREATE TABLE progresso (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER," +
                "fase_id INTEGER," +
                "concluido INTEGER," +
                "nota INTEGER," +
                "data_conclusao TEXT)");

        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (1, 'Lógica', 'Introdução à lógica de programação', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (2, 'Algoritmos', 'Introdução a algoritmos', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (3, 'Variáveis', 'Introdução a variáveis', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (4, 'Condicionais', 'Introdução a condicionais', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (5, 'Loops', 'Introdução a loops', 'teoria')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersaoAntiga, int NovaVersao) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS fases");
        db.execSQL("DROP TABLE IF EXISTS aulas");
        db.execSQL("DROP TABLE IF EXISTS exercicios");
        db.execSQL("DROP TABLE IF EXISTS progresso");
        onCreate(db);
    }
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("senha", senha);
        long resultado = db.insert("usuarios", null, values);
        return resultado != -1;
    }
    public boolean loginUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email=? AND senha=?",
                new String[]{email, senha});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }
    public Cursor getFases() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM fases ORDER BY ordem", null);
    }
}
