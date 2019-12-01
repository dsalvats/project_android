package com.xpomanager.classes;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.IdiomaPreguntaIdiomas;
import com.xpomanager.models.Pregunta;
import com.xpomanager.models.NivelPreguntas;
import com.xpomanager.models.PreguntaIdioma;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class ControladorPrincipal extends Application {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private final static String JSON_PATH = "/storage/sdcard/XPOmanager/data/exposicion.json";

    /*************
     * ATRIBUTOS *
     *************/
    private Exposicion exposicion;

    /****************
     * CONTRUCTORES *
     ****************/
    public ControladorPrincipal() {
        this.exposicion = null;
    }

    /***********
     * MÉTODOS *
     ***********/
    private void cargarJson() {

        Gson gson = new Gson();

        try {
            Type type = new TypeToken<Exposicion>(){}.getType();
            JsonReader reader = new JsonReader(new FileReader(JSON_PATH));
            exposicion = gson.fromJson(reader, type);
            System.out.println(gson.toJson(exposicion));
            reader.close();

            System.out.println("_________________________________");

            for (NivelPreguntas test: exposicion.getNivelPreguntas()) {
                if (test.getNivel() != null && test.getPreguntas() != null) {
                    System.out.println(test.getNivel().getNombre());

                    for (Pregunta pregunta : test.getPreguntas()) {
                        System.out.println(pregunta.getId());

                        if (pregunta.getIdiomaPreguntaIdiomas() != null) {
                            for (IdiomaPreguntaIdiomas idiomaPreguntaIdiomas : pregunta.getIdiomaPreguntaIdiomas()) {
                                System.out.println(idiomaPreguntaIdiomas.getIdioma().getNombre());

                                if (idiomaPreguntaIdiomas.getPreguntaIdioma() != null) {
                                    System.out.println(idiomaPreguntaIdiomas.getPreguntaIdioma().getPregunta());
                                }
                            }
                        }

                    }
                } else {
                    System.out.println("ES NULL");
                }
            }

            System.out.println("_________________________________");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        cargarJson();
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
}
