package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.utils.QR;

public class ResumenActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    ControladorPrincipal controladorPrincipal;
    ControladorJuego controladorJuego;

    ConstraintLayout backgroundLayout;

    ImageView imageViewInicio;
    ImageView imageViewCharachter;
    ImageView imageViewQR;

    TextView textViewQRExposicionName;
    TextView textViewReview;
    TextView textViewRespuesta;

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

        //Principal Controler
        controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorJuego = controladorPrincipal.getControladorJuego();

        //ConstraintLayout to set ImageBackground
        backgroundLayout = findViewById(R.id.ConstraintLayoutBackground);

        // TextViews
        textViewReview = findViewById(R.id.TextViewReview);
        textViewRespuesta = findViewById(R.id.TextViewDescripcionRespuesta);
        textViewQRExposicionName = findViewById(R.id.textViewExposicionName);

        // ImageViews
        imageViewInicio = findViewById(R.id.ImageViewInicio);
        imageViewCharachter = findViewById(R.id.ImageViewPersonaje);
        imageViewQR = findViewById(R.id.imageViewQRCode);

        cargarElementos();

        //Events
        imageViewInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected void cargarElementos()
    {
        Idioma idioma = controladorJuego.getIdioma();
        ExposicionIdioma exposicionIdioma = controladorJuego.getExposicion().getExposicionIdiomas().get(idioma);

        //Cargar Imagen Fondo
        Drawable drawable = new BitmapDrawable(this.getResources(), controladorPrincipal.getResumImageBitmap());
        backgroundLayout.setBackground(drawable);

        //Cargar ImagenPersonaje
        System.out.println(controladorJuego.getPersonaje().getNombre());
        Bitmap PersonajeBitmap = controladorPrincipal.getPersonajeImageBitmap(controladorJuego.getPersonaje());
        imageViewCharachter.setImageBitmap(PersonajeBitmap);

        //Cargar Imagen HOMEBUTTOM
        imageViewInicio.setImageBitmap(controladorPrincipal.getHomeButtomBitmap());

        //Cargar QR
        String Link = exposicionIdioma.getSummURL ();
        imageViewQR.setImageBitmap(QR.encodeAsBitmap(Link));

        //Cargar els textViews
        textViewReview.setText(controladorJuego.getStringProgress());

        if (exposicionIdioma != null) {
            String mensaje1 = exposicionIdioma.getSummCongrats();
            String mensaje2 = exposicionIdioma.getSummComment();
            String mensaje3 = exposicionIdioma.getSummGameText();
            String mensaje4 = exposicionIdioma.getSummURL();
            String mensaje5 = exposicionIdioma.getSummURLText();
            String mensaje6 = exposicionIdioma.getSummBye();

            textViewQRExposicionName.setText(mensaje5);

            StringBuilder sb = new StringBuilder();

            sb.append(mensaje1).append(" ");
            sb.append(mensaje2).append(" ");
            sb.append(mensaje3);

            textViewRespuesta.setText(sb.toString());
        }

    }

}