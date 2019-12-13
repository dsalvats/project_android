package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

public class IdiomaPreguntaIdiomas {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Key")
    private Idioma idioma;
    @SerializedName("Value")
    private PreguntaIdioma preguntaIdioma;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public IdiomaPreguntaIdiomas(Idioma idioma, PreguntaIdioma preguntaIdioma) {
        this.idioma = idioma;
        this.preguntaIdioma = preguntaIdioma;
    }

    public IdiomaPreguntaIdiomas() {
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

    public PreguntaIdioma getPreguntaIdioma() {
        return preguntaIdioma;
    }

    public void setPreguntaIdioma(PreguntaIdioma preguntaIdioma) {
        this.preguntaIdioma = preguntaIdioma;
    }

}
