package com.example.rodrigobarreto1.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rodrigobarreto1.todo.Banco.ControlaBanco;

public class AtualizaActivity extends AppCompatActivity {

    private String codigo;
    private EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza);

        codigo = this.getIntent().getStringExtra("codigo");

        ControlaBanco crud = new ControlaBanco(getBaseContext());

        etNome = findViewById(R.id.txtAtualizaNome);
        Cursor cursor = crud.carregaDadoPorId(Integer.parseInt(codigo));
        etNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
    }

    public void atualiza(View view){
        ControlaBanco crud = new ControlaBanco(getBaseContext());

        crud.alteraDado(Integer.parseInt(codigo), etNome.getText().toString());
        Intent intent = new Intent(AtualizaActivity.this, ListaActivity.class);
        startActivity(intent);
        finish();
    }
}
