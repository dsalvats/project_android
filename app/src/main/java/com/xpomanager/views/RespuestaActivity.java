package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Personaje;
import com.xpomanager.models.PreguntaIdioma;
import com.xpomanager.utils.QR;
import java.io.File;

public class RespuestaActivity extends AppCompatActivity {

    private final static String APP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/XPOmanager/data/";
    private final static String IMAGES_FOLDER = APP_FOLDER + "Imagenes/Elements/";
    private final static String QSTIMG_FOLDER = APP_FOLDER + "Imagenes/Preguntas/";

    /*************
     * ATRIBUTOS *
     *************/
    PreguntaIdioma preguntaIdioma;
    ControladorJuego controladorJuego;
    TextView textViewRespuestaBool;
    TextView textViewResumen;

    Bitmap bitmap;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {  }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        if (getSupportActionBar() != null) { getSupportActionBar().hide(); }

        // - - - - - - - - - - carga info de objeto controladorPRINCIPAL
        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();

        // - - - - - - - - - - carga info de objeto controladorJUEGO
        controladorJuego = controladorPrincipal.getControladorJuego();

        // - - - - - - - - - - carga info de objeto preguntaIDIOMA
        preguntaIdioma = controladorJuego.getCurrentPreguntaIdioma();

        // - - - - - - - - - - carga info de parametro de ACIERTO desde Intent
        Boolean acierto = (Boolean) getIntent().getSerializableExtra("Acierto");

        // - - - - - - - - -  - - - - - - - - - - - - - - - - - - - carga referencia de elementos Activity

        // - - - - - - - - - - MaGoMo
        ExposicionIdioma literals = controladorJuego.getExposicion().getExposicionIdiomas().get( controladorJuego.getIdioma() );

        // - - - - - - - - - - Imagen de Personaje
        ConstraintLayout constraintLayoutBackground = findViewById(R.id.ConstraintLayoutBackground);
        String filename;
        if (preguntaIdioma.getLinkRespuestaCorrecta()!=null) { filename = QSTIMG_FOLDER + preguntaIdioma.getLinkRespuestaCorrecta(); }
        else { filename = IMAGES_FOLDER + "appimage.jpg"; }
        // lee archivo grafico y retorna un bitmap
        if (new File( filename ).exists()) { bitmap = BitmapFactory.decodeFile( filename ); }
        constraintLayoutBackground.setBackground( new BitmapDrawable(getResources(), bitmap));

        // - - - - - - - - - - Imagen de Personaje
        ImageView imageViewPersonaje = findViewById(R.id.ImageViewPersonaje);
        bitmap = controladorPrincipal.getPersonajeImageBitmap( controladorJuego.getPersonaje() );
        imageViewPersonaje.setImageBitmap(bitmap);

        // - - - - - - - - - - Imagen de Idioma
        ImageView imageViewBandera = findViewById(R.id.ImageViewBandera);
        bitmap = controladorPrincipal.getIdiomaImageBitmap( controladorJuego.getIdioma() );
        imageViewBandera.setImageBitmap(bitmap);

        // - - - - - - - - - - Texto nivel
        // presentamos un textbox con el literal de nivel
        // se calcula el color de fondo en base a posicion y longitud de arralist de niveles
        // PE, si es de 3 sera de 0=#00FF00 1=#FFFF00 2=@FF0000
        TextView textViewDificultad = findViewById(R.id.TextViewDificultad);
        textViewDificultad.setText( controladorJuego.getNivel().getNombre() );
        textViewDificultad.setBackgroundColor( Color.rgb(50, 130, 50) );

        // - - - - - - - - - - Imagen de Inicio
        ImageView imageViewInicio = findViewById(R.id.ImageViewInicio);
        imageViewInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        // - - - - - - - - - - Texto nivel
        // TextView	TextViewEntrarId

        // - - - - - - - - - - Texto Respuesta correcta
        TextView textViewRespuestaCorrecta = findViewById(R.id.TextViewRespuestaCorrecta);
        textViewRespuestaCorrecta.setText( preguntaIdioma.getRespuestaCorrecta() );

        // - - - - - - - - - - Tezxt e Imagen de QR URL LINK
        TextView textViewPregunta = findViewById(R.id.TextViewPregunta);
        ImageView imageViewQR = findViewById(R.id.ImageViewQR);
        if ( preguntaIdioma.getStringLinkRespuestaCorrecta() != null) {
            textViewPregunta.setText( preguntaIdioma.getStringLinkRespuestaCorrecta() );
            bitmap = QR.encodeAsBitmap(preguntaIdioma.getStringLinkRespuestaCorrecta());
            imageViewPersonaje.setImageBitmap(bitmap);
        }

        // - - - - - - - - - - Resumen preguntas y aciertos
        textViewResumen = findViewById(R.id.TextViewResumen);
        textViewResumen.setText(controladorJuego.getStringProgress()); // set PROGRESS Text

        // - - - - - - - - - - Texto Resumen Explicacion Respuesta
        TextView textViewDescripcionRespuesta;
        textViewDescripcionRespuesta = findViewById(R.id.TextViewDescripcionRespuesta);
        textViewDescripcionRespuesta.setText( preguntaIdioma.getStringRespuestaCorrecta() );

        // - - - - - - - - - - Respuesta Bool
        textViewRespuestaBool = findViewById(R.id.TextViewRespuestaBool);
        // - - - - - - - - - - control de acierto en respuesta
        if (acierto != null)
        {
            if (acierto)
            {
                textViewRespuestaBool.setText( literals.getAnswerOK() );
                textViewRespuestaBool.setTextColor(Color.rgb(0, 255, 0));
            }
            else
            {
                textViewRespuestaBool.setText( literals.getAnswerKO() );
                textViewRespuestaBool.setTextColor(Color.rgb(255, 0, 0));
            }
        }
        else
        {
            textViewRespuestaBool.setText( "Timeout!" );
        }

        // - - - - - - - - - - Next Button
        Button buttonSiguiente = findViewById(R.id.ButtonSiguiente);
        buttonSiguiente.setText( literals.getNextButton() ); // set NEXT button Text
        // - - - - - - - - - - Accion de boton NEXT
        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controladorJuego.hasNextPregunta()) {
                    Intent intent = new Intent(view.getContext(), PreguntaActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(view.getContext(), ResumenActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });

    }

}
