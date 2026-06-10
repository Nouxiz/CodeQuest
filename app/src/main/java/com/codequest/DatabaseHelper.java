package com.codequest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "codequest.db";
    private static final int BANCO_VERSAO = 7;

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

        db.execSQL("CREATE TABLE opcoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exercicio_id INTEGER, " +
                "texto TEXT," +
                "correta BOOLEAN)");

        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (1, 'Lógica', 'Introdução à lógica de programação', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (2, 'Algoritmos', 'Introdução a algoritmos', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (3, 'Variáveis', 'Introdução a variáveis', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (4, 'Condicionais', 'Introdução a condicionais', 'teoria')");
        db.execSQL("INSERT INTO fases (ordem, titulo, descricao, tipo) VALUES (5, 'Loops', 'Introdução a loops', 'teoria')");

        // aula fase 1

        db.execSQL("INSERT INTO aulas (fase_id, conteudo_teorico) VALUES (1, 'Lógica é a forma de organizar o raciocínio para resolver problemas. \n No dia a dia usamos lógica o tempo todo sem perceber! Por exemplo, para escovar os dentes você segue uma ordem: 1. Pegar a escova \n 2. Colocar pasta \n 3. Escovar \n 4. Enxaguar')");

        // exercicio fase 1
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'O que é lógica de programação?', 'texto', 'organizar raciocinio', 'Lógica é a forma de organizar o raciocínio para resolver problemas!') ");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Organizar o raciocínio para resolver problemas', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Uma linguagem de programação', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Um tipo de computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Um programa de computador', 0)");

        // aula fase 2
        db.execSQL("INSERT INTO aulas (fase_id, conteudo_teorico) VALUES (2, 'Um algoritmo é uma sequência de passos para resolver um problema.\n\nPense numa receita de bolo:\n1. Separar os ingredientes\n2. Misturar os ingredientes\n3. Colocar na forma\n4. Assar por 40 minutos\n5. Esperar esfriar\n\nIsso é um algoritmo! Uma sequência lógica de passos que leva a um resultado.')");

        // exercicio fase 2
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'O que é um algoritmo?', 'multipla_escolha', 'sequencia de passos', 'Um algoritmo é uma sequência de passos para resolver um problema!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Uma sequência de passos para resolver um problema', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Um tipo de computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Uma linguagem de programação', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Um programa de jogos', 0)");

        // aula fase 3
        db.execSQL("INSERT INTO aulas (fase_id, conteudo_teorico) VALUES (3, 'Uma variável é um espaço na memória do computador para guardar dados.\n\nPense numa caixinha com um nome:\n- nome = \"João\"\n- idade = 15\n- altura = 1.70\n\nVocê pode guardar qualquer valor nela e mudar quando quiser!\n\nExemplo:\nidade = 15\nidade = 16  (fez aniversário!)')");

        // exercicio fase 3
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'O que é uma variável?', 'multipla_escolha', 'espaco na memoria', 'Uma variável é um espaço na memória para guardar dados!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Um espaço na memória para guardar dados', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Um tipo de algoritmo', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Um comando do computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Uma tela do programa', 0)");

        // aula fase 4
        db.execSQL("INSERT INTO aulas (fase_id, conteudo_teorico) VALUES (4, 'Condicionais permitem que o programa tome decisões!\n\nExemplo:\nSE (temperatura > 30)\n   Vista roupa leve\nSENÃO\n   Vista agasalho\n\nNo código isso se chama IF/ELSE:\n\nif (temperatura > 30) {\n   // roupa leve\n} else {\n   // agasalho\n}\n\nO programa verifica a condição e executa um caminho ou outro!')");

        // exercicio fase 4
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'Qual comando usamos para tomar decisões no código?', 'multipla_escolha', 'if else', 'Usamos if/else para tomar decisões!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'if/else', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'for/while', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'print/input', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'int/string', 0)");

        // aula fase 5
        db.execSQL("INSERT INTO aulas (fase_id, conteudo_teorico) VALUES (5, 'Loops repetem um bloco de código várias vezes!\n\nSem loop:\nprint(1)\nprint(2)\nprint(3)\nprint(4)\nprint(5)\n\nCom loop:\nfor i in range(1, 6):\n   print(i)\n\nMuito mais simples! O loop repete o código enquanto a condição for verdadeira.')");

        // exercicio fase 5
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'Para que serve um loop?', 'multipla_escolha', 'repetir codigo', 'Um loop serve para repetir um bloco de código várias vezes!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Repetir um bloco de código várias vezes', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Guardar dados na memória', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Tomar decisões no código', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Criar variáveis', 0)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersaoAntiga, int NovaVersao) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS fases");
        db.execSQL("DROP TABLE IF EXISTS aulas");
        db.execSQL("DROP TABLE IF EXISTS exercicios");
        db.execSQL("DROP TABLE IF EXISTS progresso");
        db.execSQL("DROP TABLE IF EXISTS opcoes");
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

    public Cursor getAula(int faseId) {
        SQLiteDatabase aula = this.getReadableDatabase();
        return aula.rawQuery("SELECT * FROM aulas Where fase_id = ?", new String[]{String.valueOf(faseId)});
        }
    public Cursor getExercicio(int faseId) {
        SQLiteDatabase exercicio = this.getReadableDatabase();
        return exercicio.rawQuery("SELECT * FROM exercicios Where fase_id = ?", new String[]{String.valueOf(faseId)});
    }
    public Cursor getOpcoes(int exercicioId) {
        SQLiteDatabase opcoes = this.getReadableDatabase();
        return opcoes.rawQuery("SELECT * FROM opcoes WHERE exercicio_id = ?", new String[]{String.valueOf(exercicioId)});
        }
    public Cursor getFase(int faseId) {
        SQLiteDatabase fase = this.getReadableDatabase();
        return fase.rawQuery("SELECT * FROM fases where id = ?", new String[]{String.valueOf(faseId)});
        }
    public Boolean salvarProgresso(int usuarioId, int faseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario_id", usuarioId);
        values.put("fase_Id", faseId);
        values.put("concluido", 1);
        long resultado = db.insert("progresso", null, values);
        return resultado != -1;
    }
    public Cursor getFasesConcluidas(int usuarioId) {
        SQLiteDatabase fasesconc = this.getReadableDatabase();
        return fasesconc.rawQuery("SELECT fase_id FROM progresso WHERE usuario_id = ? AND concluido = 1", new String[]{String.valueOf(usuarioId)});
    }
    public int getUsuarioId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM usuarios WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        }
        return 0;
    }
}

