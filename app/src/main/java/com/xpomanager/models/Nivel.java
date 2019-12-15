package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Nivel {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Id")
    private int id;
    @SerializedName("Activo")
    private boolean activo;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Literales")
    private List<Literal> literales;
    private HashMap<Idioma, String> traducciones;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Nivel(int id, boolean activo, String nombre, List<Literal> literales, HashMap<Idioma, String> traducciones) {
        this.id = id;
        this.activo = activo;
        this.nombre = nombre;
        this.literales = literales;
        this.traducciones = traducciones;
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

    public List<Literal> getLiterales() {
        return literales;
    }

    public void setLiterales(List<Literal> literales) {
        this.literales = literales;
    }

    public HashMap<Idioma, String> getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(HashMap<Idioma, String> traducciones) {
        this.traducciones = traducciones;
    }
}
