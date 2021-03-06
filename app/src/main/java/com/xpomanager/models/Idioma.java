package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

public class Idioma {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Id")
    private int id;
    @SerializedName("Activo")
    private boolean activo;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Image")
    private String imagenSrc;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Idioma(int id, boolean activo, String nombre, String imagenSrc) {
        this.id = id;
        this.activo = activo;
        this.nombre = nombre;
        this.imagenSrc = imagenSrc;
    }

    public Idioma() {
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

    public String getImagenSrc() {
        return imagenSrc;
    }

    public void setImagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
    }

}
