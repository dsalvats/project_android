package com.xpomanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;

public class PreguntaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pregunta);

        // RESPUESTAS ConstraintLayouts
        ConstraintLayout constraintLayoutRespuesta1 = findViewById(R.id.ConstraintLayoutLateralRespuesta1);
        ConstraintLayout constraintLayoutRespuesta2 = findViewById(R.id.ConstraintLayoutLateralRespuesta2);
        ConstraintLayout constraintLayoutRespuesta3 = findViewById(R.id.ConstraintLayoutLateralRespuesta3);
        ConstraintLayout constraintLayoutRespuesta4 = findViewById(R.id.ConstraintLayoutLateralRespuesta4);

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
