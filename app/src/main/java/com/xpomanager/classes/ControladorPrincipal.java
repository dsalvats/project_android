package com.xpomanager.classes;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.IdiomaPreguntaIdiomas;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.NivelPreguntas;
import com.xpomanager.models.Personaje;
import com.xpomanager.models.Pregunta;
import com.xpomanager.models.PreguntaIdioma;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class ControladorPrincipal extends Application {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private final static String JSON_PATH = "/storage/sdcard/XPOmanager/data/exposicion.json";

    /*************
     * ATRIBUTOS *
     *************/
    private Exposicion exposicion;
    private ControladorJuego controladorJuego;

    /****************
     * CONTRUCTORES *
     ****************/
    public ControladorPrincipal() {
        this.exposicion = null;
        this.controladorJuego = null;
    }

    /***********
     * MÉTODOS *
     ***********/
    @Override
    public void onCreate() {
        super.onCreate();
        cargarJson();
        transformExposiciones();
    }

    public void iniciarJuego(Personaje personaje, Idioma idioma, Nivel nivel) {
        controladorJuego = new ControladorJuego(personaje, idioma, nivel, exposicion);
    }

    private void cargarJson() {

        Gson gson = new Gson();

        try {
            Type type = new TypeToken<Exposicion>(){}.getType();
            JsonReader reader = new JsonReader(new FileReader(JSON_PATH));
            exposicion = gson.fromJson(reader, type);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transformExposiciones() {
        HashMap<Nivel, List<Pregunta>> preguntas = new HashMap<>();
        HashMap<Idioma, PreguntaIdioma> preguntaIdiomas;

        if (exposicion != null) {

            for (NivelPreguntas nivelPreguntas : exposicion.getNivelPreguntas()) {
                Nivel nivel = nivelPreguntas.getNivel();
                List<Pregunta> preguntasAux = nivelPreguntas.getPreguntas();

                for (Pregunta pregunta : preguntasAux) {
                    preguntaIdiomas = new HashMap<>();

                    for (IdiomaPreguntaIdiomas idiomaPreguntaIdiomas : pregunta.getIdiomaPreguntaIdiomas()) {
                        Idioma idioma = idiomaPreguntaIdiomas.getIdioma();
                        PreguntaIdioma preguntaIdioma = idiomaPreguntaIdiomas.getPreguntaIdioma();

                        if (preguntaIdioma != null) {
                            preguntaIdiomas.put(findIdiomaExposicionByIdioma(idioma), preguntaIdioma);
                        }
                    }

                    pregunta.setPreguntaIdiomas(preguntaIdiomas);
                }

                preguntas.put(findNivelExposicionByNivel(nivel), preguntasAux);
            }
        }

        exposicion.setPreguntas(preguntas);
    }

    private Nivel findNivelExposicionByNivel(Nivel nivel) {

        Nivel exposicionNivel =  null;

        for(Nivel nivelAux: exposicion.getNiveles()) {
            if (nivelAux.getNombre().equals(nivel.getNombre())) {
                exposicionNivel = nivelAux;
            }
        }

        return exposicionNivel;
    }

    private Idioma findIdiomaExposicionByIdioma(Idioma idioma) {
        Idioma exposicionIdioma = null;

        for(Idioma idiomaAux: exposicion.getIdiomas()) {
            if (idiomaAux.getNombre().equals(idioma.getNombre())) {
                exposicionIdioma = idiomaAux;
            }
        }

        return exposicionIdioma;
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public Exposicion getExposicion() {
        return exposicion;
    }

    public void setExposicion(Exposicion exposicion) {
        this.exposicion = exposicion;
    }

    public ControladorJuego getControladorJuego() {
        return controladorJuego;
    }

    public void setControladorJuego(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
    }
}
