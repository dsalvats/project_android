package com.xpomanager.models;

public class Nivel {

    /*************
     * ATRIBUTOS *
     *************/
    private int id;
    private boolean activo;
    private String nombre;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Nivel(int id, boolean activo, String nombre) {
        this.id = id;
        this.activo = activo;
        this.nombre = nombre;
    }

    public Nivel() {
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
