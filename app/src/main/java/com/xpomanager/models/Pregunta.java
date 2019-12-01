package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Pregunta {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Id")
    private int id;
    @SerializedName("IsRespText")
    private boolean isRespText;
    @SerializedName("ImagenPregunta")
    private String imagenPreguntaSrc;
    @SerializedName("ImagenRespuestaCorrecta")
    private String imagenRespuestaCorrectaSrc;
    @SerializedName("ImagenRespuestaIncorrecta1")
    private String imagenRespuestaIncorrecta1Src;
    @SerializedName("ImagenRespuestaIncorrecta2")
    private String imagenRespuestaIncorrecta2Src;
    @SerializedName("ImagenRespuestaIncorrecta3")
    private String imagenRespuestaIncorrecta3Src;
    @SerializedName("PreguntaIdiomas")
    private List<IdiomaPreguntaIdiomas> idiomaPreguntaIdiomas;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Pregunta(int id, boolean isRespText, String imagenPreguntaSrc,
                    String imagenRespuestaCorrectaSrc, String imagenRespuestaIncorrecta1Src,
                    String imagenRespuestaIncorrecta2Src, String imagenRespuestaIncorrecta3Src,
                    List<IdiomaPreguntaIdiomas> idiomaPreguntaIdiomas) {
        this.id = id;
        this.isRespText = isRespText;
        this.imagenPreguntaSrc = imagenPreguntaSrc;
        this.imagenRespuestaCorrectaSrc = imagenRespuestaCorrectaSrc;
        this.imagenRespuestaIncorrecta1Src = imagenRespuestaIncorrecta1Src;
        this.imagenRespuestaIncorrecta2Src = imagenRespuestaIncorrecta2Src;
        this.imagenRespuestaIncorrecta3Src = imagenRespuestaIncorrecta3Src;
        this.idiomaPreguntaIdiomas = idiomaPreguntaIdiomas;
    }

    public Pregunta() {
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

    public boolean isRespText() {
        return isRespText;
    }

    public void setRespText(boolean respText) {
        isRespText = respText;
    }

    public String getImagenPreguntaSrc() {
        return imagenPreguntaSrc;
    }

    public void setImagenPreguntaSrc(String imagenPreguntaSrc) {
        this.imagenPreguntaSrc = imagenPreguntaSrc;
    }

    public String getImagenRespuestaCorrectaSrc() {
        return imagenRespuestaCorrectaSrc;
    }

    public void setImagenRespuestaCorrectaSrc(String imagenRespuestaCorrectaSrc) {
        this.imagenRespuestaCorrectaSrc = imagenRespuestaCorrectaSrc;
    }

    public String getImagenRespuestaIncorrecta1Src() {
        return imagenRespuestaIncorrecta1Src;
    }

    public void setImagenRespuestaIncorrecta1Src(String imagenRespuestaIncorrecta1Src) {
        this.imagenRespuestaIncorrecta1Src = imagenRespuestaIncorrecta1Src;
    }

    public String getImagenRespuestaIncorrecta2Src() {
        return imagenRespuestaIncorrecta2Src;
    }

    public void setImagenRespuestaIncorrecta2Src(String imagenRespuestaIncorrecta2Src) {
        this.imagenRespuestaIncorrecta2Src = imagenRespuestaIncorrecta2Src;
    }

    public String getImagenRespuestaIncorrecta3Src() {
        return imagenRespuestaIncorrecta3Src;
    }

    public void setImagenRespuestaIncorrecta3Src(String imagenRespuestaIncorrecta3Src) {
        this.imagenRespuestaIncorrecta3Src = imagenRespuestaIncorrecta3Src;
    }

    public List<IdiomaPreguntaIdiomas> getIdiomaPreguntaIdiomas() {
        return idiomaPreguntaIdiomas;
    }

    public void setIdiomaPreguntaIdiomas(List<IdiomaPreguntaIdiomas> idiomaPreguntaIdiomas) {
        this.idiomaPreguntaIdiomas = idiomaPreguntaIdiomas;
    }
}
