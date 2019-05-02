package com.example.rodrigobarreto1.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.rodrigobarreto1.todo.Banco.ControlaBanco;

public class ListaActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        final ControlaBanco crud = new ControlaBanco(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String [] {"_id", "nome"};
        int[] idViews = new int[] {R.id.tvId, R.id.tvNome};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_tarefas, cursor, nomeCampos, idViews, 0);

        lista = findViewById(R.id.listView);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                crud.deletaDado(codigo);
                finish();
                startActivity(getIntent());
                return true;
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(ListaActivity.this, AtualizaActivity.class);
                intent.putExtra("codigo", codigo.toString());
                startActivity(intent);
                finish();
            }
        });

    }

}
