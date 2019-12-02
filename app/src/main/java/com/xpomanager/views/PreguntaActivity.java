package com.xpomanager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.PreguntaIdioma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreguntaActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    ControladorJuego controladorJuego;
    PreguntaIdioma preguntaIdioma;
    ConstraintLayout constraintLayoutRespuesta1;
    ConstraintLayout constraintLayoutRespuesta2;
    ConstraintLayout constraintLayoutRespuesta3;
    ConstraintLayout constraintLayoutRespuesta4;
    TextView textViewEntrarId;
    TextView textViewPregunta;
    TextView textViewRespuesta1;
    TextView textViewRespuesta2;
    TextView textViewRespuesta3;
    TextView textViewRespuesta4;
    TextView textViewResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_pregunta);

        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorJuego = controladorPrincipal.getControladorJuego();
        preguntaIdioma = controladorJuego.siguientePregunta();

        // TextViews
        textViewEntrarId = findViewById(R.id.TextViewEntrarId);
        textViewEntrarId.setText(controladorJuego.getGameId());
        textViewResumen = findViewById(R.id.TextViewResumen);
        textViewResumen.setText(controladorJuego.getStringProgress(preguntaIdioma));

        // RESPUESTAS ConstraintLayouts
        constraintLayoutRespuesta1 = findViewById(R.id.ConstraintLayoutLateralRespuesta1);
        constraintLayoutRespuesta2 = findViewById(R.id.ConstraintLayoutLateralRespuesta2);
        constraintLayoutRespuesta3 = findViewById(R.id.ConstraintLayoutLateralRespuesta3);
        constraintLayoutRespuesta4 = findViewById(R.id.ConstraintLayoutLateralRespuesta4);

        // RESPUESTAS TextViews
        textViewPregunta = findViewById(R.id.TextViewPregunta);
        textViewRespuesta1 = findViewById(R.id.TextViewRespuesta1);
        textViewRespuesta2 = findViewById(R.id.TextViewRespuesta2);
        textViewRespuesta3 = findViewById(R.id.TextViewRespuesta3);
        textViewRespuesta4 = findViewById(R.id.TextViewRespuesta4);

        textViewPregunta.setText(preguntaIdioma.getPregunta());

        List<TextView> textViewRespuestas = new ArrayList<>();
        textViewRespuestas.add(textViewRespuesta1);
        textViewRespuestas.add(textViewRespuesta2);
        textViewRespuestas.add(textViewRespuesta3);
        textViewRespuestas.add(textViewRespuesta4);

        Collections.shuffle(textViewRespuestas);

        textViewRespuestas.get(0).setText(preguntaIdioma.getRespuestaCorrecta());
        textViewRespuestas.get(1).setText(preguntaIdioma.getRespuestaIncorrecta1());
        textViewRespuestas.get(2).setText(preguntaIdioma.getRespuestaIncorrecta2());
        textViewRespuestas.get(3).setText(preguntaIdioma.getRespuestaIncorrecta3());

        constraintLayoutRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v, textViewRespuesta1);
            }
        });

        constraintLayoutRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v, textViewRespuesta2);
            }
        });

        constraintLayoutRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v, textViewRespuesta3);
            }
        });

        constraintLayoutRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v, textViewRespuesta4);
            }
        });

    }

    /************
     * MÉTODOES *
     ************/
    private void responder(View view, TextView textView) {

        Intent intent = new Intent(view.getContext(), RespuestaActivity.class);

        if (textView.getText().equals(preguntaIdioma.getRespuestaCorrecta())) {
            controladorJuego.asignarRespuesta(preguntaIdioma, true);
            intent.putExtra("Acierto", true);
        } else {
            intent.putExtra("Acierto", false);
        }

        intent.putExtra("PreguntaIdioma", preguntaIdioma);
        startActivityForResult(intent, 0);
    }

}
