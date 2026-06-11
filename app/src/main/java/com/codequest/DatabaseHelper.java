package com.codequest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "codequest.db";
    private static final int BANCO_VERSAO = 13;

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
                "pagina INTEGER," +
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

        // aula 1
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (1, 1, 'O que é lógica?\n\nLógica é a forma de organizar o raciocínio para resolver problemas.\n\nNo dia a dia usamos lógica o tempo todo sem perceber!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (1, 2, 'Exemplo de lógica:\n\nPara escovar os dentes você segue uma ordem:\n1. Pegar a escova\n2. Colocar pasta\n3. Escovar\n4. Enxaguar\n\nIsso é lógica!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (1, 3, 'Lógica na programação:\n\nNa programação usamos lógica para dizer ao computador o que fazer.\n\nO computador faz exatamente o que mandamos, então precisamos ser precisos!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (1, 4, 'Pensamento lógico:\n\nPara desenvolver o pensamento lógico:\n- Quebre problemas grandes em partes menores\n- Pense passo a passo\n- Seja preciso e organizado')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (1, 5, 'Resumo - Lógica:\n\n✓ Lógica é organizar o raciocínio\n✓ Usamos no dia a dia\n✓ Na programação é fundamental\n✓ Pense passo a passo\n\nAgora vamos praticar!')");

        // aula 2
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (2, 1, 'O que é um algoritmo?\n\nUm algoritmo é uma sequência finita de passos para resolver um problema.\n\nTodo programa de computador é um algoritmo!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (2, 2, 'Exemplo de algoritmo:\n\nReceita de bolo:\n1. Separar ingredientes\n2. Misturar ingredientes\n3. Colocar na forma\n4. Assar por 40 min\n5. Esperar esfriar')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (2, 3, 'Características de um algoritmo:\n\n- Tem início e fim\n- Os passos são ordenados\n- Cada passo é claro e preciso\n- Resolve um problema específico')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (2, 4, 'Algoritmo no computador:\n\nQuando você escreve código, está criando um algoritmo!\n\nO computador executa cada instrução na ordem que você definiu.')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (2, 5, 'Resumo - Algoritmos:\n\n✓ Sequência de passos\n✓ Tem início e fim\n✓ Resolve um problema\n✓ Base da programação\n\nAgora vamos praticar!')");

        // aula 3
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (3, 1, 'O que é uma variável?\n\nUma variável é um espaço na memória do computador para guardar dados.\n\nPense numa caixinha com um nome!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (3, 2, 'Exemplos de variáveis:\n\nnome = \"João\"\nidade = 15\naltura = 1.70\naprovado = true\n\nCada variável guarda um tipo de dado!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (3, 3, 'Tipos de variáveis:\n\n- Texto (String): \"João\"\n- Número inteiro (int): 15\n- Número decimal (double): 1.70\n- Verdadeiro/Falso (boolean): true')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (3, 4, 'Usando variáveis:\n\nidade = 15\nidade = 16  // fez aniversário!\n\nVocê pode mudar o valor de uma variável quando quiser!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (3, 5, 'Resumo - Variáveis:\n\n✓ Guardam dados na memória\n✓ Têm um nome e um valor\n✓ Podem ser alteradas\n✓ Têm tipos diferentes\n\nAgora vamos praticar!')");

        // aula 4
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (4, 1, 'O que são condicionais?\n\nCondicionais permitem que o programa tome decisões!\n\nSe uma condição for verdadeira, faz uma coisa. Senão, faz outra.')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (4, 2, 'Exemplo do dia a dia:\n\nSE (está chovendo)\n   leve guarda-chuva\nSENÃO\n   não precisa\n\nO programa verifica e decide!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (4, 3, 'If/Else no código:\n\nif (temperatura > 30) {\n   // roupa leve\n} else {\n   // agasalho\n}\n\nif = SE, else = SENÃO')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (4, 4, 'Operadores de comparação:\n\n> maior que\n< menor que\n== igual a\n!= diferente de\n>= maior ou igual\n<= menor ou igual')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (4, 5, 'Resumo - Condicionais:\n\n✓ Tomam decisões no código\n✓ Usam if/else\n✓ Verificam condições\n✓ Controlam o fluxo\n\nAgora vamos praticar!')");

        // aula 5
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (5, 1, 'O que são loops?\n\nLoops repetem um bloco de código várias vezes!\n\nSem loop você precisaria escrever o mesmo código muitas vezes.')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (5, 2, 'Exemplo sem loop:\n\nprint(1)\nprint(2)\nprint(3)\nprint(4)\nprint(5)\n\nMuito repetitivo!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (5, 3, 'Exemplo com loop:\n\nfor i in range(1, 6):\n   print(i)\n\nMuito mais simples! O loop repete automaticamente.')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (5, 4, 'Tipos de loops:\n\nfor - repete um número fixo de vezes\nwhile - repete enquanto condição for verdadeira\n\nEscolha o mais adequado para cada situação!')");
        db.execSQL("INSERT INTO aulas (fase_id, pagina, conteudo_teorico) VALUES (5, 5, 'Resumo - Loops:\n\n✓ Repetem blocos de código\n✓ Evitam repetição desnecessária\n✓ For e While são os principais\n✓ Fundamentais na programação\n\nAgora vamos praticar!')");

        // exercicio fase 1
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'O que é lógica de programação?', 'multipla_escolha', 'organizar raciocinio', 'Lógica é organizar o raciocínio!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Organizar o raciocínio para resolver problemas', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Uma linguagem de programação', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Um tipo de computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (1, 'Um programa de computador', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'Qual a ordem correta para escovar os dentes?', 'multipla_escolha', 'escova pasta escovar enxaguar', 'A ordem é: escova, pasta, escovar e enxaguar!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Pegar escova, pasta, escovar, enxaguar', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Enxaguar, escovar, pegar escova', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Comer, montar, pegar o pão', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (2, 'Escovar, pegar o pão, comer', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'Lógica é usada apenas em computadores?', 'multipla_escolha', 'nao', 'Lógica é usada no dia a dia também!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Não, usamos lógica no dia a dia também', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Sim, só em computadores', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Só em matemática', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (3, 'Apenas em jogos', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'O que significa pensar de forma lógica?', 'multipla_escolha', 'passo a passo', 'Pensar logicamente é organizar o raciocínio passo a passo!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'Organizar o raciocínio passo a passo', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'Pensar rápido', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'Memorizar tudo', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (4, 'Usar calculadora', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (1, 'Na programação, o computador faz:', 'multipla_escolha', 'exatamente oq mandamos', 'O computador faz exatamente o que mandamos!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Exatamente o que mandamos', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'O que quiser', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Nada sozinho', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (5, 'Sempre erra', 0)");

// exercicio fase 2
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'O que é um algoritmo?', 'multipla_escolha', 'sequencia de passos', 'Algoritmo é uma sequência de passos!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (6, 'Uma sequência de passos para resolver um problema', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (6, 'Um tipo de computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (6, 'Uma linguagem de programação', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (6, 'Um programa de jogos', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'Uma receita de bolo é um exemplo de:', 'multipla_escolha', 'algoritmo', 'Uma receita é um algoritmo pois tem passos ordenados!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (7, 'Algoritmo', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (7, 'Variável', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (7, 'Loop', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (7, 'Condicional', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'Um algoritmo deve ter:', 'multipla_escolha', 'inicio e fim', 'Todo algoritmo tem início e fim!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (8, 'Início e fim definidos', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (8, 'Apenas início', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (8, 'Apenas fim', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (8, 'Nenhum dos dois', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'Os passos de um algoritmo devem ser:', 'multipla_escolha', 'ordenados e precisos', 'Os passos devem ser ordenados e precisos!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (9, 'Ordenados e precisos', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (9, 'Aleatórios', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (9, 'Confusos', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (9, 'Opcionais', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (2, 'Todo programa de computador é:', 'multipla_escolha', 'um algoritmo', 'Todo programa é um algoritmo!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (10, 'Um algoritmo', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (10, 'Uma variável', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (10, 'Um loop', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (10, 'Uma condicional', 0)");

// exercicio fase 3
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'O que é uma variável?', 'multipla_escolha', 'espaco na memoria', 'Variável é um espaço na memória!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (11, 'Um espaço na memória para guardar dados', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (11, 'Um tipo de algoritmo', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (11, 'Um comando do computador', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (11, 'Uma tela do programa', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'Qual tipo guarda textos?', 'multipla_escolha', 'string', 'String guarda textos!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (12, 'String', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (12, 'int', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (12, 'boolean', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (12, 'double', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'Qual tipo guarda verdadeiro/falso?', 'multipla_escolha', 'boolean', 'Boolean guarda true ou false!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (13, 'boolean', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (13, 'String', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (13, 'int', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (13, 'double', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'Posso mudar o valor de uma variável?', 'multipla_escolha', 'sim', 'Sim, você pode mudar o valor quando quiser!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (14, 'Sim, quando quiser', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (14, 'Não, nunca', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (14, 'Só uma vez', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (14, 'Apenas no início', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (3, 'Qual tipo guarda números decimais?', 'multipla_escolha', 'double', 'Double guarda números decimais!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (15, 'double', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (15, 'int', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (15, 'String', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (15, 'boolean', 0)");

// exercicio fase 4
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'Qual comando usamos para decisões?', 'multipla_escolha', 'if else', 'Usamos if/else!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (16, 'if/else', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (16, 'for/while', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (16, 'print/input', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (16, 'int/string', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'O que significa else?', 'multipla_escolha', 'senao', 'else significa SENÃO!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (17, 'Senão / caso contrário', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (17, 'Se', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (17, 'Enquanto', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (17, 'Para', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'Qual operador verifica igualdade?', 'multipla_escolha', '==', '== verifica se dois valores são iguais!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (18, '==', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (18, '=', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (18, '!=', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (18, '>', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'O que faz um if?', 'multipla_escolha', 'verifica condicao', 'if verifica se uma condição é verdadeira!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (19, 'Verifica se uma condição é verdadeira', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (19, 'Repete código', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (19, 'Guarda dados', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (19, 'Cria variáveis', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (4, 'Qual operador significa diferente?', 'multipla_escolha', '!=', '!= significa diferente!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (20, '!=', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (20, '==', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (20, '>=', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (20, '<=', 0)");

// exercicio fase 5
        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'Para que serve um loop?', 'multipla_escolha', 'repetir codigo', 'Loop serve para repetir código!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (21, 'Repetir um bloco de código várias vezes', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (21, 'Guardar dados na memória', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (21, 'Tomar decisões', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (21, 'Criar variáveis', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'Qual loop repete número fixo de vezes?', 'multipla_escolha', 'for', 'for repete um número fixo de vezes!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (22, 'for', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (22, 'while', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (22, 'if', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (22, 'else', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'Qual loop repete enquanto condição for verdadeira?', 'multipla_escolha', 'while', 'while repete enquanto a condição for verdadeira!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (23, 'while', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (23, 'for', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (23, 'if', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (23, 'else', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'Loops evitam:', 'multipla_escolha', 'repeticao desnecessaria', 'Loops evitam repetição desnecessária de código!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (24, 'Repetição desnecessária de código', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (24, 'Erros no programa', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (24, 'Uso de variáveis', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (24, 'Condicionais', 0)");

        db.execSQL("INSERT INTO exercicios (fase_id, pergunta, tipo_resposta, resposta_correta, feedback_explicativo) VALUES (5, 'O que é i++ num loop for?', 'multipla_escolha', 'incrementa i', 'i++ incrementa o valor de i em 1!')");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (25, 'Incrementa i em 1', 1)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (25, 'Decrementa i em 1', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (25, 'Zera i', 0)");
        db.execSQL("INSERT INTO opcoes (exercicio_id, texto, correta) VALUES (25, 'Multiplica i por 2', 0)");
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

    public Cursor getAula(int faseId, int pagina) {
        SQLiteDatabase aula = this.getReadableDatabase();
        return aula.rawQuery("SELECT * FROM aulas Where fase_id = ? AND pagina = ?", new String[]{String.valueOf(faseId), String.valueOf(pagina)});
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
        values.put("fase_id", faseId);
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
    public int getTotalXP(int usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM progresso WHERE usuario_id = ? AND concluido = 1", new String[]{String.valueOf(usuarioId)});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0) * 10;
        }
        return 0;
    }
    public Cursor getExercicioPorId(int exercicioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM exercicios WHERE id = ?", new String[]{String.valueOf(exercicioId)});
    }
    public String getNomeUsuario(int usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome FROM usuarios WHERE id = ?", new String[]{String.valueOf(usuarioId)});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        }
        return "Usuário";
    }
}

