package com.example.crud2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText CampoCodigo,CampoNombre,CampoPrograma;
    ConexionSQLiteHelper conn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CampoCodigo= (EditText) findViewById(R.id.txtcodigo);
        CampoNombre= (EditText) findViewById(R.id.txtnombre);
        CampoPrograma= (EditText) findViewById(R.id.txtprograma);

        conn= new ConexionSQLiteHelper(this,"bd_estudiantes",null ,1);
    }
    public boolean ValidarVacio(String codigo,String nombre,String programa){

        boolean vacio=false;
        if(codigo.isEmpty()){
            CampoCodigo.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(nombre.isEmpty()){
            CampoNombre.setError("Este campo no puede quedar vacio");
            vacio=true;
        }
        if(programa.isEmpty()){
            CampoPrograma.setError("Este campo no puede quedar vacio");
            vacio=true;
        }

        return vacio;
    }

    public void onClick(View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnGuardar:
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                Registrar();
                break;
            case R.id.btnCancelar:
                break;
            case R.id.btnListado:
                // Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                miIntent= new Intent(this,listado.class);
                break;
        }
        if(miIntent!=null){
            startActivity(miIntent);
        }

    }

    private void Registrar() {
        SQLiteDatabase db= conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        String codigo=CampoCodigo.getText().toString();
        String nombre=CampoNombre.getText().toString();
        String programa=CampoPrograma.getText().toString();

        if(!ValidarVacio(codigo,nombre,programa)){
            values.put(Constantes.Campo_ID,codigo);
            values.put(Constantes.Campo_nombre,nombre);
            values.put(Constantes.Campo_programa,programa);

            if(!buscar()){
                db.insert(Constantes.TABLA_ESTUDIANTE,Constantes.Campo_ID,values);
                Toast.makeText(getApplicationContext(),"Estudiante Registrado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Codigo ya registrado",Toast.LENGTH_SHORT).show();
            }
        }


        db.close();
    }
    public boolean buscar(){
        boolean encontrado=false;
        SQLiteDatabase db= conn.getReadableDatabase();
        String[] parametros={CampoCodigo.getText().toString()};
        String[] campos={Constantes.Campo_nombre,Constantes.Campo_programa};

        Cursor cursor= db.query(Constantes.TABLA_ESTUDIANTE,campos,Constantes.Campo_ID+"=?",parametros,null,null,null);

        if(cursor.moveToFirst()){
            encontrado=true;
            cursor.close();
            cursor.close();
           return encontrado;
        }
        return encontrado;

    }

    public void cancelar (View view){
        CampoCodigo.setText("");
        CampoNombre.setText("");
        CampoPrograma.setText("");
    }


}