package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

public class Literal {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Id")
    private int id;
    @SerializedName("LangId")
    private int langId;
    @SerializedName("Idioma")
    private String idioma;
    @SerializedName("Literal")
    private String literal;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Literal(int id, int langId, String idioma, String literal) {
        this.id = id;
        this.langId = langId;
        this.idioma = idioma;
        this.literal = literal;
    }

    public Literal() {
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

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }
}
