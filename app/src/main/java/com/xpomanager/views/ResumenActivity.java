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
import com.xpomanager.utils.QR;

import java.io.Console;

public class ResumenActivity extends AppCompatActivity {

    /*************
     * ATRIBUTOS *
     *************/
    ControladorJuego controladorJuego;
    ConstraintLayout BackgroundLayout;

    ImageView ImageViewInicio;
    ImageView ImageViewCharachter;
    ImageView ImageViewQR;

    TextView TextViewQRExposicionName;
    TextView TextViewReview;
    TextView TextViewRespuesta;

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
        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorJuego = controladorPrincipal.getControladorJuego();

        //ConstraintLayout to set ImageBackground
        BackgroundLayout = findViewById(R.id.ConstraintLayoutBackground);
        // TextViews
        TextViewReview = findViewById(R.id.TextViewReview);
        TextViewRespuesta = findViewById(R.id.TextViewDescripcionRespuesta);
        TextViewQRExposicionName = findViewById(R.id.textViewExposicionName);
        // ImageViews
        ImageViewInicio = findViewById(R.id.ImageViewInicio);
        ImageViewCharachter = findViewById(R.id.ImageViewPersonaje);
        ImageViewQR = findViewById(R.id.imageViewQRCode);

        CargarElementos(controladorPrincipal);

        //Events
        ImageViewInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }
    protected void CargarElementos(ControladorPrincipal cntrlPrincipal)
    {
        //Cargar Imagen Fondo
        Drawable drawable = new BitmapDrawable(this.getResources(), cntrlPrincipal.getResumImageBitmap());
        BackgroundLayout.setBackground(drawable);
        //Cargar ImagenPersonaje
        System.out.println(controladorJuego.getPersonaje().getNombre());
        Bitmap PersonajeBitmap = cntrlPrincipal.getPersonajeImageBitmap(controladorJuego.getPersonaje());
        ImageViewCharachter.setImageBitmap(PersonajeBitmap);
        //Cargar Imagen HOMEBUTTOM
        ImageViewInicio.setImageBitmap(cntrlPrincipal.getHomeButtomBitmap());
        //Cargar QR
        String Link = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummURL ();
        System.out.println(Link);
        ImageViewQR.setImageBitmap(QR.encodeAsBitmap(Link));
        //Cargar els textViews
        TextViewReview.setText(controladorJuego.getStringProgress());

        String mensaje1 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummCongrats();
        String mensaje2 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummComment();
        String mensaje3 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummGameText();
        String mensaje4 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummURL();
        String mensaje5 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummURLText();
        String mensaje6 = cntrlPrincipal.getExposicion().getExposicionIdiomas().get(controladorJuego.getIdioma()).getSummBye();




        TextViewQRExposicionName.setText(mensaje5);
        TextViewRespuesta.setText(mensaje1+" "+mensaje2+" "+mensaje3);

    }

}