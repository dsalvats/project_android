package com.xpomanager.controllers;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.xpomanager.utils.QR;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.IdiomaExposicionIdioma;
import com.xpomanager.models.IdiomaPreguntaIdiomas;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.NivelPreguntas;
import com.xpomanager.models.Personaje;
import com.xpomanager.models.Pregunta;
import com.xpomanager.models.PreguntaIdioma;
import com.xpomanager.utils.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class ControladorPrincipal extends Application {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    // Formats
    private final static String[] VIDEO_FORMATS = {"webm", "mkv", "flv", "vob", "gif", "gifv",
            "avi", "mov", "wmv", "yuv", "amv", "mp4", "m4p", "m4v", "3gp"};
    private final static String[] IMAGE_FORMATS = {"png", "jpg", "gif", "webp", "tiff", "waw", "svg"};

    // Paths
    private final static String APP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/XPOmanager/data/";
    private final static String IMAGES_FOLDER = APP_FOLDER + "Imagenes/Elements/";
    private final static String JSON_PATH = APP_FOLDER + "exposicion.json";
    private final static String DEFAULT_PERSONAJE_IMAGE_SRC = IMAGES_FOLDER + "charE0I3.jpg";
    private final static String DEFAULT_IDIOMA_IMAGE_SRC = IMAGES_FOLDER + "flag1.jpg";
    private final static String DEFAULT_APP_IMAGE_SRC = IMAGES_FOLDER + "spaceship.webm";
    private final static String DEFAULT_LOGO_GRUPO_SRC = IMAGES_FOLDER + "group_logo.png";
    private final static String DEFAULT_LOGO_MUSEO_SRC = IMAGES_FOLDER + "museum_logo.png";
    private final static String DEFAULT_PLAY_BUTTON_SRC = IMAGES_FOLDER + "playnow.png";
    private final static String DEFAULT_EXPO_URL = "https://mnactec.cat/es/inicio";

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
    }

    public void loadInfo() {
        cargarJson();
        transformExposicion();
    }

    public void iniciarJuego(Personaje personaje, Idioma idioma, Nivel nivel) {
        controladorJuego = new ControladorJuego(personaje, idioma, nivel, exposicion);
    }

    private void cargarJson() {

        Gson gson = new Gson();

        try {
            Type type = new TypeToken<Exposicion>() {
            }.getType();
            JsonReader reader = new JsonReader(new FileReader(JSON_PATH));
            exposicion = gson.fromJson(reader, type);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    private void transformExposicion() {
        HashMap<Nivel, List<Pregunta>> preguntas = new HashMap<>();
        HashMap<Idioma, PreguntaIdioma> preguntaIdiomas;
        HashMap<Idioma, ExposicionIdioma> exposicionIdiomas = new HashMap<>();

        if (exposicion != null) {

            // Transforma List<NivelPreguntas> a HashMap<Nivel, Preguntas>
            for (NivelPreguntas nivelPreguntas : exposicion.getNivelPreguntas()) {
                Nivel nivel = nivelPreguntas.getNivel();
                List<Pregunta> preguntasAux = nivelPreguntas.getPreguntas();

                // Transforma List<IdiomaPreguntaIdiomas> a HashMap<Idioma, PreguntaIdioma>
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

            // Transforma List<IdiomaExposicionIdiomas> a HashMap<Idioma, ExposicionIdioma>
            for (IdiomaExposicionIdioma idiomaExposicionIdioma : exposicion.getIdiomaExposicionIdiomas()) {
                Idioma idioma = idiomaExposicionIdioma.getIdioma();
                ExposicionIdioma exposicionIdiomasAux = idiomaExposicionIdioma.getExposicionIdioma();

                exposicionIdiomas.put(findIdiomaExposicionByIdioma(idioma), exposicionIdiomasAux);
            }
        }

        exposicion.setPreguntas(preguntas);
        exposicion.setExposicionIdiomas(exposicionIdiomas);
    }

    private Nivel findNivelExposicionByNivel(Nivel nivel) {
        Nivel exposicionNivel = null;

        for (Nivel nivelAux : exposicion.getNiveles()) {
            if (nivelAux.getNombre().equals(nivel.getNombre())) {
                exposicionNivel = nivelAux;
            }
        }

        return exposicionNivel;
    }

    private Idioma findIdiomaExposicionByIdioma(Idioma idioma) {
        Idioma exposicionIdioma = null;

        for (Idioma idiomaAux : exposicion.getIdiomas()) {
            if (idiomaAux.getNombre().equals(idioma.getNombre())) {
                exposicionIdioma = idiomaAux;
            }
        }

        return exposicionIdioma;
    }

    public Bitmap getPersonajeImageBitmap(Personaje personaje) {
        Bitmap bitmap = null;
        String src = IMAGES_FOLDER + personaje.getImagenSrc();

        if (src == null) {
            src = DEFAULT_PERSONAJE_IMAGE_SRC;
        }

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        return bitmap;
    }

    public Bitmap getIdiomaImageBitmap(Idioma idioma) {
        Bitmap bitmap = null;
        String src = IMAGES_FOLDER + idioma.getImagenSrc();

        if (src == null) {
            src = DEFAULT_IDIOMA_IMAGE_SRC;
        }

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        BitmapFactory.decodeFile(idioma.getImagenSrc());

        return bitmap;
    }

    public Boolean isAppImageVideo() {
        String imageSrc = IMAGES_FOLDER + DEFAULT_APP_IMAGE_SRC;

        if (exposicion.getAppImageSrc() != null) {
            imageSrc = exposicion.getAppImageSrc();
        }

        return StringUtils.stringContainsString(imageSrc, VIDEO_FORMATS);
    }

    public Bitmap getAppImageBitmap() {
        Bitmap bitmap = null;
        String src = exposicion.getAppImageSrc();

        if (src == null) {
            src = DEFAULT_APP_IMAGE_SRC;
        }

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        return bitmap;
    }

    public void setAppVideo(final VideoView videoView) {
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        Uri uri = Uri.fromFile(new File(DEFAULT_APP_IMAGE_SRC));
        videoView.setVideoPath(DEFAULT_APP_IMAGE_SRC);
        videoView.pause();
        videoView.seekTo(0);

    }

    public Bitmap getLogoMuseoBitmap() {
        Bitmap bitmap = null;
        String src = DEFAULT_LOGO_MUSEO_SRC;

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        return bitmap;
    }

    public Bitmap getLogoGrupoBitMap() {
        Bitmap bitmap = null;
        String src = DEFAULT_LOGO_GRUPO_SRC;

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        return bitmap;
    }

    public Bitmap getMainQRBitMap(Idioma idioma) {
        ExposicionIdioma exposicionIdioma = null;
        Bitmap bitmap = null;
        String exposicionURL = null;

        exposicionIdioma = exposicion.getExposicionIdiomas().get(idioma);

        if (exposicionIdioma != null) {
            exposicionURL = exposicionIdioma.getStartExpoURL();
        } else {
            exposicionURL = DEFAULT_EXPO_URL;
        }

        bitmap = QR.encodeAsBitmap(exposicionURL);

        return bitmap;
    }

    public Bitmap getJugarAhoraBitmap() {
        Bitmap bitmap = null;
        String src =  DEFAULT_PLAY_BUTTON_SRC;

        if (new File(src).exists()) {
            bitmap = BitmapFactory.decodeFile(src);
        } else {
            // TODO: Recuperar desde @drawable una imagen default
        }

        return bitmap;
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
