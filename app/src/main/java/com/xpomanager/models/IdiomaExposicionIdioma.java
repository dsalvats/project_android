package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

public class IdiomaExposicionIdioma {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Key")
    private Idioma idioma;
    @SerializedName("Value")
    private ExposicionIdioma exposicionIdioma;

    /***************
     * CONSTRUCTOR *
     ***************/
    public IdiomaExposicionIdioma(Idioma idioma, ExposicionIdioma exposicionIdioma) {
        this.idioma = idioma;
        this.exposicionIdioma = exposicionIdioma;
    }

    public IdiomaExposicionIdioma() {
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public ExposicionIdioma getExposicionIdioma() {
        return exposicionIdioma;
    }

    public void setExposicionIdioma(ExposicionIdioma exposicionIdioma) {
        this.exposicionIdioma = exposicionIdioma;
    }

}
