package com.xpomanager.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.Personaje;

public class MainActivity extends AppCompatActivity {

    ControladorPrincipal controladorPrincipal;
    TextView textViewNombreExposicion;
    TextView textViewDescripcion;
    TextView textViewDescripcion2;
    TextView textViewLinkExposicion;
    ImageView imageViewPersonaje;
    ImageView imageViewIdioma;
    ImageView imageViewNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        controladorPrincipal = (ControladorPrincipal) super.getApplication();

        declareElements();
        fillElements();

        // ImageView
        ImageView imageViewJugarAhora = findViewById(R.id.ImageViewJugarAhora);

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
        // TextViews
        textViewNombreExposicion = findViewById(R.id.TextViewNombreExposicion);
        textViewDescripcion = findViewById(R.id.TextViewDescripcion);
        textViewDescripcion2 = findViewById(R.id.TextViewDescripcion2);
        textViewLinkExposicion = findViewById(R.id.TextViewLinkExposicion);

        // ImageViews
        imageViewPersonaje = findViewById(R.id.ImageViewPersonaje);
        imageViewIdioma = findViewById(R.id.ImageViewBandera);
        imageViewNivel = findViewById(R.id.ImageViewDificultad);

        if (controladorPrincipal.getExposicion().getPersonajes().size() > 0)
            imageViewPersonaje.setTag(controladorPrincipal.getExposicion().getPersonajes().get(0));

        if (controladorPrincipal.getExposicion().getIdiomas().size() > 0)
            imageViewIdioma.setTag(controladorPrincipal.getExposicion().getIdiomas().get(0));

        if (controladorPrincipal.getExposicion().getIdiomas().size() > 0)
            imageViewNivel.setTag(controladorPrincipal.getExposicion().getNiveles().get(0));
    }

    private void fillElements() {
        Exposicion exposicion = controladorPrincipal.getExposicion();

        textViewNombreExposicion.setText(exposicion.getNombre());
        textViewDescripcion.setText(exposicion.getDescripcionExpo());
        textViewDescripcion2.setText("Desde una fecha a no se cual otra");
        textViewLinkExposicion.setText("un link .com");
    }
}
