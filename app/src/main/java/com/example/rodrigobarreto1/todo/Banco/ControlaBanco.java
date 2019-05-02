package com.example.rodrigobarreto1.todo.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public Cursor carregaDados(){
        Cursor cursor;
        String [] campos = {"_id", "nome"};
        db = banco.getReadableDatabase();
        cursor = db.query("tarefa", campos, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void deletaDado(int id){
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        db.delete("tarefa", where, null);
        db.close();

    }

    public Cursor carregaDadoPorId(int id){
        Cursor cursor;
        String [] campos = {"_id", "nome"};
        String where = "_id = "+id;
        db = banco.getReadableDatabase();
        cursor = db.query("tarefa", campos, where, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraDado(int id, String nome){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();
        where = "_id="+id;
        valores = new ContentValues();
        valores.put("nome", nome);

        db.update("tarefa", valores, where, null);
        db.close();
    }
}
