package com.xpomanager.models;

import java.util.HashMap;

public class Pregunta {

    /*************
     * ATRIBUTOS *
     *************/
    private int id;
    private boolean isRespText;
    private String imagenPreguntaSrc;
    private String imagenRespuestaCorrectaSrc;
    private String imagenRespuestaIncorrecta1Src;
    private String imagenRespuestaIncorrecta2Src;
    private String imagenRespuestaIncorrecta3Src;
    private HashMap<Idioma, PreguntaIdioma> preguntaIdiomas;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Pregunta(int id, boolean isRespText, String imagenPreguntaSrc,
                    String imagenRespuestaCorrectaSrc, String imagenRespuestaIncorrecta1Src,
                    String imagenRespuestaIncorrecta2Src, String imagenRespuestaIncorrecta3Src,
                    HashMap<Idioma, PreguntaIdioma> preguntaIdiomas) {
        this.id = id;
        this.isRespText = isRespText;
        this.imagenPreguntaSrc = imagenPreguntaSrc;
        this.imagenRespuestaCorrectaSrc = imagenRespuestaCorrectaSrc;
        this.imagenRespuestaIncorrecta1Src = imagenRespuestaIncorrecta1Src;
        this.imagenRespuestaIncorrecta2Src = imagenRespuestaIncorrecta2Src;
        this.imagenRespuestaIncorrecta3Src = imagenRespuestaIncorrecta3Src;
        this.preguntaIdiomas = preguntaIdiomas;
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

    public HashMap<Idioma, PreguntaIdioma> getPreguntaIdiomas() {
        return preguntaIdiomas;
    }

    public void setPreguntaIdiomas(HashMap<Idioma, PreguntaIdioma> preguntaIdiomas) {
        this.preguntaIdiomas = preguntaIdiomas;
    }
}
