package com.example.rodrigobarreto1.todo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.rodrigobarreto1.todo.Banco.ControlaBanco;

public class ListaActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ControlaBanco crud = new ControlaBanco(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String [] {"_id", "nome"};
        int[] idViews = new int[] {R.id.tvId, R.id.tvNome};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_tarefas, cursor, nomeCampos, idViews, 0);

        lista = findViewById(R.id.listView);
        lista.setAdapter(adapter);

    }
}
