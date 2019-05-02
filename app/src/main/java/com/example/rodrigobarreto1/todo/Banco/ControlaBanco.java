package com.example.rodrigobarreto1.todo.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControlaBanco {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public ControlaBanco(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String nome){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);

        resultado = db.insert("tarefa", null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao inserir dado";
        }
        else {
            return "Dado inserido com sucesso";
        }

    }
}
