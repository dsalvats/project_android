package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;

public class ResumenActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    ControladorJuego controladorJuego;
    TextView textViewReview;
    ImageView imageViewInicio;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resumen);

        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorJuego = controladorPrincipal.getControladorJuego();

        // TextViews
        textViewReview = findViewById(R.id.TextViewReview);
        textViewReview.setText(controladorJuego.getStringProgress());

        // ImageViews
        imageViewInicio = findViewById(R.id.ImageViewInicio);

        imageViewInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }

}