package com.example.crud2;


public class Constantes {
    // Constantes tabla usuario
    public static final String TABLA_ESTUDIANTE="estudiante";
    public static final String Campo_ID="id";
    public static final String Campo_nombre="nombre";
    public static final String Campo_programa="programa";

                                                                                                    // INTEGER,
    public static final String CREAR_TABLA_USUARIO ="CREATE TABLE "+TABLA_ESTUDIANTE+"("+Campo_ID+" text primary key,"+Campo_nombre+" TEXT,"+Campo_programa+" TEXT)";

}
