package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NivelPreguntas {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Key")
    private Nivel nivel;
    @SerializedName("Value")
    private List<Pregunta> preguntas;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public NivelPreguntas(Nivel nivel, List<Pregunta> preguntas) {
        this.nivel = nivel;
        this.preguntas = preguntas;
    }

    public NivelPreguntas() {
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
