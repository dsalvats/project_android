package com.xpomanager.controllers;

import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.Personaje;
import com.xpomanager.models.Pregunta;
import com.xpomanager.models.PreguntaIdioma;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ControladorJuego {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private static final String ALPHA_NUMERIC_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int GAME_ID_LENGTH = 5;

    /*************
     * ATRIBUTOS *
     *************/
    private String gameId;
    private Personaje personaje;
    private Idioma idioma;
    private Nivel nivel;
    private Exposicion exposicion;
    private Map<PreguntaIdioma, Boolean> respuestas;
    private Iterator<Map.Entry<PreguntaIdioma, Boolean>> respuetasIterator;
    private PreguntaIdioma currentPreguntaIdioma;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public ControladorJuego(Personaje personaje, Idioma idioma, Nivel nivel, Exposicion exposicion) {
        this.gameId = generateGameId();
        this.personaje = personaje;
        this.idioma = idioma;
        this.nivel = nivel;
        this.exposicion = exposicion;
        this.respuestas = generateRespuestas();
        this.respuetasIterator = respuestas.entrySet().iterator();
        this.currentPreguntaIdioma = null;
    }

    /***********
     * MÉTODOS *
     ***********/

    public String getStringProgress() {
        StringBuilder sb = new StringBuilder();
        ExposicionIdioma exposicionIdioma = exposicion.getExposicionIdiomas().get(idioma);


        sb.append(exposicionIdioma.getQuestion()).append(" ");
        if (currentPreguntaIdioma == null) {
            sb.append(getTotalPreguntas());
        } else {
            sb.append(getCurrentPregunta(currentPreguntaIdioma));
        }
        sb.append("/");
        sb.append(getTotalPreguntas());
        sb.append(" - ").append(exposicionIdioma.getHits()).append(" ");
        sb.append(getPreguntasAcertadas());

        return sb.toString();
    }

    // TODO: Pasar respuestas a LinkedHashMap
    private int getCurrentPregunta(PreguntaIdioma preguntaIdioma) {
        //List<PreguntaIdioma> indexes = new ArrayList<>(respuestas.keySet());
        int index = 0;

        for (PreguntaIdioma key : respuestas.keySet()) {
            index++;
            if (key.equals(preguntaIdioma)) {
                break;
            }
        }

        return index;
    }

    private int getTotalPreguntas() {
        return respuestas.size();
    }

    private int getPreguntasAcertadas() {
        return Collections.frequency(respuestas.values(), true);
    }

    public void asignarRespuesta(PreguntaIdioma preguntaIdioma, Boolean acierto) {
        respuestas.put(preguntaIdioma, acierto);
    }

    public PreguntaIdioma siguientePregunta() {
        PreguntaIdioma preguntaIdioma = null;

        if (respuetasIterator.hasNext()) {
            preguntaIdioma = respuetasIterator.next().getKey();

            currentPreguntaIdioma = preguntaIdioma;
        } else {
            currentPreguntaIdioma = null;
        }

        return preguntaIdioma;
    }

    public Boolean hasNextPregunta() {
        return respuetasIterator.hasNext();
    }

    private String generateGameId() {
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(GAME_ID_LENGTH);
        for (int i = 0; i < GAME_ID_LENGTH; i++) {
            sb.append(ALPHA_NUMERIC_STRING.charAt(rnd.nextInt(ALPHA_NUMERIC_STRING.length())));
        }

        return sb.toString();
    }

    private Map<PreguntaIdioma, Boolean> generateRespuestas() {
        Map<PreguntaIdioma, Boolean> respuestas = new HashMap<>();

        if (exposicion != null &&
                exposicion.getPreguntas() != null &&
                exposicion.getPreguntas().containsKey(nivel)) {
            for (Pregunta pregunta : exposicion.getPreguntas().get(nivel)) {
                if (pregunta != null &&
                        pregunta.getPreguntaIdiomas() != null &&
                        pregunta.getPreguntaIdiomas().containsKey(idioma)) {

                    PreguntaIdioma preguntaIdioma = pregunta.getPreguntaIdiomas().get(idioma);

                    respuestas.put(preguntaIdioma, false);
                }
            }
        }

        return respuestas;
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Exposicion getExposicion() {
        return exposicion;
    }

    public void setExposicion(Exposicion exposicion) {
        this.exposicion = exposicion;
    }

    public Map<PreguntaIdioma, Boolean> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<PreguntaIdioma, Boolean> respuestas) {
        this.respuestas = respuestas;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public PreguntaIdioma getCurrentPreguntaIdioma() {
        return currentPreguntaIdioma;
    }

    public void setCurrentPreguntaIdioma(PreguntaIdioma currentPreguntaIdioma) {
        this.currentPreguntaIdioma = currentPreguntaIdioma;
    }
}
