package com.xpomanager.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpomanager.R;
import com.xpomanager.adapters.AdaptadorObjects;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.Personaje;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private final static int DEFAULT_INT_PERSONAJE = 0;
    private final static int DEFAULT_INT_IDIOMA = 2;
    private final static int DEFAULT_INT_NIVEL = 0;

    /*************
     * ATRIBUTOS *
     *************/
    private ControladorPrincipal controladorPrincipal;
    private ConstraintLayout constraintLayoutMain;
    private TextView textViewLiteralExposicion;
    private TextView textViewNombreExposicion;
    private TextView textViewDescripcion;
    private TextView textViewDescripcion2;
    private TextView textViewLinkExposicion;
    private ImageView imageViewPersonaje;
    private ImageView imageViewIdioma;
    private ImageView imageViewNivel;
    private ImageView imageViewJugarAhora;
    //private ImageView imageViewLogoGrupo;
    private ImageView imageViewLogoMuseo;
    private ImageView imageViewQRMuseo;

    /***********
     * MÉTODOS *
     ***********/
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        fillControladorPrincipal();
        declareElements();
        setDefaultSettings();
        setElementsListeners();
        fillElements();
        setImages();
    }

    private void setListToRecyclerView(List<?> objects) {
        RecyclerView recyclerView = null;

        if (!objects.isEmpty()) {
            if (objects.get(0) instanceof Personaje) {
                recyclerView = findViewById(R.id.RecyclerViewPersonaje);
            } else if (objects.get(0) instanceof Idioma) {
                recyclerView = findViewById(R.id.RecyclerViewIdioma);
            }
        }

        if (recyclerView != null) {
            final AdaptadorObjects adaptadorObjects = new AdaptadorObjects(objects, controladorPrincipal);

            recyclerView.setAdapter(adaptadorObjects);

            recyclerView.setLayoutManager(
                    new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        }


    }

    private void fillControladorPrincipal() {
        controladorPrincipal = (ControladorPrincipal) super.getApplication();
    }

    private void setElementsListeners() {
        imageViewJugarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje personaje = getSelectedPersonaje();
                Idioma idioma = getSelectedIdioma();
                Nivel nivel = getSelectedNivel();

                controladorPrincipal.iniciarJuego(personaje, idioma, nivel);

                if (controladorPrincipal.getControladorJuego().hasNextPregunta()) {
                    Intent intent = new Intent(view.getContext(), PreguntaActivity.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        });
    }

    private Personaje getSelectedPersonaje() {
        return (Personaje) imageViewPersonaje.getTag();
    }

    private Idioma getSelectedIdioma() {
        return (Idioma) imageViewIdioma.getTag();
    }

    private Nivel getSelectedNivel() {
        return (Nivel) imageViewNivel.getTag();
    }

    private void declareElements() {
        // ConstraintLayouts
        constraintLayoutMain = findViewById(R.id.ConstraintLayoutMain);

        // TextViews
        textViewLiteralExposicion = findViewById(R.id.TextViewLiteralExposicion);
        textViewNombreExposicion = findViewById(R.id.TextViewNombreExposicion);
        textViewDescripcion = findViewById(R.id.TextViewDescripcion);
        textViewDescripcion2 = findViewById(R.id.TextViewDescripcion2);
        textViewLinkExposicion = findViewById(R.id.TextViewLinkExposicion);

        // ImageViews
        imageViewPersonaje = findViewById(R.id.ImageViewPersonaje);
        imageViewIdioma = findViewById(R.id.ImageViewIdioma);
        imageViewNivel = findViewById(R.id.ImageViewDificultad);
        imageViewJugarAhora = findViewById(R.id.ImageViewJugarAhora);
        //imageViewLogoGrupo = findViewById(R.id.ImageViewLogoGrupo);
        imageViewLogoMuseo = findViewById(R.id.ImageViewLogoMuseo);
        imageViewQRMuseo = findViewById(R.id.ImageViewQRMuseo);
    }

    private void setDefaultSettings() {
        setPersonaje(controladorPrincipal.getExposicion().getPersonajes().get(DEFAULT_INT_PERSONAJE));
        setIdioma(controladorPrincipal.getExposicion().getIdiomas().get(DEFAULT_INT_IDIOMA));
        setNivel(controladorPrincipal.getExposicion().getNiveles().get(DEFAULT_INT_NIVEL));
    }

    private void setImages() {
        setAppImage();
        //setGroupLogo();
        setMuseoLogo();
        setExpoURLQR();
        setPlayNowImage();
    }

    private void setPlayNowImage() {
        imageViewJugarAhora.setImageBitmap(controladorPrincipal.getJugarAhoraBitmap());
    }

    private void setExpoURLQR() {
        imageViewQRMuseo.setImageBitmap(controladorPrincipal.getMainQRBitMap(getSelectedIdioma()));
    }

    private void setAppImage() {
        Drawable drawable = new BitmapDrawable(this.getResources(), controladorPrincipal.getAppImageBitmap());
        constraintLayoutMain.setBackground(drawable);
    }

    /*private void setGroupLogo() {
        imageViewLogoGrupo.setImageBitmap(controladorPrincipal.getLogoGrupoBitMap());
    }*/

    private void setMuseoLogo() {
        imageViewLogoMuseo.setImageBitmap(controladorPrincipal.getLogoMuseoBitmap());
    }

    private void setPersonaje(Personaje personaje) {
        imageViewPersonaje.setTag(personaje);
        setPersonajeImagen();
    }

    private void setPersonajeImagen() {
        Bitmap bitmap = controladorPrincipal.getPersonajeImageBitmap(getSelectedPersonaje());

        imageViewPersonaje.setImageBitmap(bitmap);
    }

    private void setIdioma(Idioma idioma) {
        imageViewIdioma.setTag(idioma);
        setIdiomaImagen();
    }

    private void setIdiomaImagen() {
        Bitmap bitmap = controladorPrincipal.getIdiomaImageBitmap(getSelectedIdioma());

        imageViewIdioma.setImageBitmap(bitmap);
    }

    private void setNivel(Nivel nivel) {
        imageViewNivel.setTag(nivel);
    }

    private void fillRecyclerViews() {
        List<Personaje> personajes = controladorPrincipal.getExposicion().getPersonajes();
        List<Idioma> idiomas = controladorPrincipal.getExposicion().getIdiomas();

        setListToRecyclerView(personajes);
        setListToRecyclerView(idiomas);
    }

    private void fillExpoInfo() {
        Exposicion exposicion = controladorPrincipal.getExposicion();
        Idioma idioma = getSelectedIdioma();
        ExposicionIdioma exposicionIdioma = exposicion.getExposicionIdiomas().get(idioma);

        if (exposicionIdioma != null) {
            textViewLiteralExposicion.setText(exposicionIdioma.getStartExposition());
            textViewNombreExposicion.setText(exposicionIdioma.getStartExpoTitle());
            textViewDescripcion.setText(exposicionIdioma.getStartExpoIntro());

            StringBuilder sb = new StringBuilder();

            if (exposicionIdioma.getStartExpoDateFrom() != null) {
                sb.append(exposicionIdioma.getStartExpoDateFrom());
                sb.append(" ");
                sb.append(exposicion.getFechaInicio());
            }

            if (!sb.toString().equals("")) {
                sb.append(" ");
            }

            if (exposicionIdioma.getStartExpoDateTo() != null) {
                sb.append(exposicionIdioma.getStartExpoDateTo());
                sb.append(" ");
                sb.append(exposicion.getFechaFin());
            }

            textViewDescripcion2.setText(sb.toString());
            textViewLinkExposicion.setText(exposicionIdioma.getStartExpoURL());
        }

    }

    private void fillElements() {
        fillExpoInfo();
        fillRecyclerViews();
    }
}
