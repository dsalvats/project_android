package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.PreguntaIdioma;

public class RespuestaActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    PreguntaIdioma preguntaIdioma;
    ControladorJuego controladorJuego;
    TextView textViewRespuestaBool;
    TextView textViewResumen;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorJuego = controladorPrincipal.getControladorJuego();
        preguntaIdioma = (PreguntaIdioma) getIntent().getSerializableExtra("PreguntaIdioma");
        Boolean acierto = (Boolean) getIntent().getSerializableExtra("Acierto");

        textViewRespuestaBool = findViewById(R.id.TextViewRespuestaBool);
        textViewResumen = findViewById(R.id.TextViewResumen);
        textViewResumen.setText(controladorJuego.getStringProgress());

        if (acierto != null) {
            if (acierto) {
                textViewRespuestaBool.setText("CORRECT ANSWER!!!");
            } else {
                textViewRespuestaBool.setText("Wrong answer");
            }
        } else {
            textViewRespuestaBool.setText("Timeout!");
        }


        // BUTTONS
        Button buttonSiguiente = findViewById(R.id.ButtonSiguiente);

        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controladorJuego.hasNextPregunta()) {
                    Intent intent = new Intent(view.getContext(), PreguntaActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    Intent intent = new Intent(view.getContext(), ResumenActivity.class);
                    startActivityForResult(intent, 0);
                }
                finish();
            }
        });

    }

}
