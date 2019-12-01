package com.xpomanager.models;

public class PreguntaIdioma {

    /*************
     * ATRIBUTOS *
     *************/
    private int id;
    private String pregunta;
    private String respuestaCorrecta;
    private String respuestaIncorrecta1;
    private String respuestaIncorrecta2;
    private String respuestaIncorrecta3;
    private String stringRespuestaCorrecta;
    private String stringLinkRespuestaCorrecta;
    private String linkRespuestaCorrecta;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public PreguntaIdioma(int id, String pregunta, String respuestaCorrecta,
                          String respuestaIncorrecta1, String respuestaIncorrecta2,
                          String respuestaIncorrecta3, String stringRespuestaCorrecta,
                          String stringLinkRespuestaCorrecta, String linkRespuestaCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
        this.stringRespuestaCorrecta = stringRespuestaCorrecta;
        this.stringLinkRespuestaCorrecta = stringLinkRespuestaCorrecta;
        this.linkRespuestaCorrecta = linkRespuestaCorrecta;
    }

    public PreguntaIdioma() {
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

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getRespuestaIncorrecta1() {
        return respuestaIncorrecta1;
    }

    public void setRespuestaIncorrecta1(String respuestaIncorrecta1) {
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return respuestaIncorrecta2;
    }

    public void setRespuestaIncorrecta2(String respuestaIncorrecta2) {
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
    }

    public String getRespuestaIncorrecta3() {
        return respuestaIncorrecta3;
    }

    public void setRespuestaIncorrecta3(String respuestaIncorrecta3) {
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public String getStringRespuestaCorrecta() {
        return stringRespuestaCorrecta;
    }

    public void setStringRespuestaCorrecta(String stringRespuestaCorrecta) {
        this.stringRespuestaCorrecta = stringRespuestaCorrecta;
    }

    public String getStringLinkRespuestaCorrecta() {
        return stringLinkRespuestaCorrecta;
    }

    public void setStringLinkRespuestaCorrecta(String stringLinkRespuestaCorrecta) {
        this.stringLinkRespuestaCorrecta = stringLinkRespuestaCorrecta;
    }

    public String getLinkRespuestaCorrecta() {
        return linkRespuestaCorrecta;
    }

    public void setLinkRespuestaCorrecta(String linkRespuestaCorrecta) {
        this.linkRespuestaCorrecta = linkRespuestaCorrecta;
    }

}
