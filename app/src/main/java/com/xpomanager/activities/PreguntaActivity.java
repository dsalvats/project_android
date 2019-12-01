package com.xpomanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;
import com.xpomanager.classes.ControladorPrincipal;
import com.xpomanager.models.PreguntaIdioma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreguntaActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    ControladorPrincipal controladorPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_pregunta);

        controladorPrincipal = (ControladorPrincipal) super.getApplication();
        PreguntaIdioma preguntaIdioma = controladorPrincipal.getControladorJuego().siguientePregunta();

        // TextViews
        TextView textViewEntrarId = findViewById(R.id.TextViewEntrarId);
        textViewEntrarId.setText(controladorPrincipal.getControladorJuego().getGameId());

        // RESPUESTAS ConstraintLayouts
        ConstraintLayout constraintLayoutRespuesta1 = findViewById(R.id.ConstraintLayoutLateralRespuesta1);
        ConstraintLayout constraintLayoutRespuesta2 = findViewById(R.id.ConstraintLayoutLateralRespuesta2);
        ConstraintLayout constraintLayoutRespuesta3 = findViewById(R.id.ConstraintLayoutLateralRespuesta3);
        ConstraintLayout constraintLayoutRespuesta4 = findViewById(R.id.ConstraintLayoutLateralRespuesta4);

        // RESPUESTAS TextViews
        TextView textViewPregunta = findViewById(R.id.TextViewPregunta);
        TextView textViewRespuesta1 = findViewById(R.id.TextViewRespuesta1);
        TextView textViewRespuesta2 = findViewById(R.id.TextViewRespuesta2);
        TextView textViewRespuesta3 = findViewById(R.id.TextViewRespuesta3);
        TextView textViewRespuesta4 = findViewById(R.id.TextViewRespuesta4);

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
                responder(v);
            }
        });

        constraintLayoutRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v);
            }
        });

        constraintLayoutRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v);
            }
        });

        constraintLayoutRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responder(v);
            }
        });

    }

    /************
     * MÉTODOES *
     ************/
    private void responder(View view) {
        Intent intent = new Intent(view.getContext(), RespuestaActivity.class);
        startActivityForResult(intent, 0);
    }

}
