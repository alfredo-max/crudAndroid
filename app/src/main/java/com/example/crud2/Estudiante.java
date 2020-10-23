package com.example.crud2;


public class Estudiante {
    private String codigo;   // Integer codigo
    private String nombre;
    private String progrma;
    public Estudiante(){

    }
    public Estudiante(String codigo, String nombre, String progrma) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.progrma = progrma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProgrma() {
        return progrma;
    }

    public void setProgrma(String progrma) {
        this.progrma = progrma;
    }
}
