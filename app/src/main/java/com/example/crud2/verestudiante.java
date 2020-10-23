package com.example.crud2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class verestudiante extends AppCompatActivity {
    private TextView txtgetcodigo,txtgetnombre,txtgetprograma;
    private Button beditar,bborrar;
    ConexionSQLiteHelper conn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verestudiante);
        conn= new ConexionSQLiteHelper(this,"bd_estudiantes",null ,1);
        txtgetcodigo=(TextView) findViewById(R.id.txtgetcodigo);
        txtgetnombre=(TextView) findViewById(R.id.txtgetnombre);
        txtgetprograma=(TextView) findViewById(R.id.txtgetprograma);

        beditar=(Button) findViewById(R.id.beditar);
        bborrar=(Button) findViewById(R.id.bborrar);


        Intent miIntent= getIntent();

        String codigo=miIntent.getExtras().getString("codigo");
        String nombre=miIntent.getExtras().getString("nombre");
        String programa=miIntent.getExtras().getString("programa");



        txtgetcodigo.setText(codigo);
        txtgetnombre.setText(nombre);
        txtgetprograma.setText(programa);

    }

    public boolean ValidarVacio(String nombre,String programa){

        boolean vacio=false;
        if(nombre.isEmpty()){
            txtgetnombre.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(programa.isEmpty()){
            txtgetprograma.setError("Este campo no puede quedar vacio");
            vacio=true;
        }

        return vacio;
    }
    public void Editar(View view){
       SQLiteDatabase db= conn.getWritableDatabase();
        String[] parametros={txtgetcodigo.getText().toString()};
        ContentValues values= new ContentValues();

        String nombre=txtgetnombre.getText().toString();
        String programa= txtgetprograma.getText().toString();

        if(!ValidarVacio(nombre,programa)){
            values.put(Constantes.Campo_nombre,nombre);
            values.put(Constantes.Campo_programa,programa);

            db.update(Constantes.TABLA_ESTUDIANTE,values,Constantes.Campo_ID+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Estudiante actualizado",Toast.LENGTH_LONG).show();
        }

        db.close();

    }
    public void Eliminar(View view){
        SQLiteDatabase db= conn.getWritableDatabase();
        String[] parametros={txtgetcodigo.getText().toString()};

        db.delete(Constantes.TABLA_ESTUDIANTE,Constantes.Campo_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino correctamente",Toast.LENGTH_LONG).show();
        txtgetcodigo.setText(" ");
        txtgetnombre.setText(" ");
        txtgetprograma.setText(" ");

        bborrar.setEnabled(false);
        beditar.setEnabled(false);


        db.close();
    }

}