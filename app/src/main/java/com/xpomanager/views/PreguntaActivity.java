package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Pregunta;
import com.xpomanager.models.PreguntaIdioma;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class PreguntaActivity extends AppCompatActivity {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // - - - - - MaGoMo - Set CONSTANTS
    private final static String APP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/XPOmanager/data/";
    private final static String IMAGES_FOLDER = APP_FOLDER + "Imagenes/Elements/";
    private final static String QSTIMG_FOLDER = APP_FOLDER + "Imagenes/Preguntas/";
    private static CountDownTimer timer;
    public int tiempo = 11;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /*************
     * ATRIBUTOS *
     *************/
    ControladorJuego controladorJuego;
    PreguntaIdioma preguntaIdioma;
    ConstraintLayout constraintLayoutImagenPregunta;
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
    TextView textViewTiempo;
    TextView textViewIntroduccionPregunta;

    /************
     * MÉTODOES *
     ************/
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

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

        declareElements();

    }

    private void declareElements() {
        // TextViews
        // textViewEntrarId = findViewById(R.id.TextViewEntrarId);
        // textViewEntrarId.setText(controladorJuego.getGameId());
        textViewResumen = findViewById(R.id.TextViewResumen);
        textViewResumen.setText(controladorJuego.getStringProgress());
        textViewTiempo = findViewById(R.id.TextViewTimpo);
        //IMAGEN PREGUNTA
        constraintLayoutImagenPregunta = findViewById(R.id.ConstraintLayoutLateralPregunta);
        LoadImagenPregunta();

        // RESPUESTAS ConstraintLayouts
        constraintLayoutRespuesta1 = findViewById(R.id.ConstraintLayoutLateralRespuesta1);
        constraintLayoutRespuesta2 = findViewById(R.id.ConstraintLayoutLateralRespuesta2);
        constraintLayoutRespuesta3 = findViewById(R.id.ConstraintLayoutLateralRespuesta3);
        constraintLayoutRespuesta4 = findViewById(R.id.ConstraintLayoutLateralRespuesta4);
        if(!RespIsText())
        {
            LoadImagenesRespuestas();
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - MaGoMo - Set left block images & buttons
        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        Bitmap bitmap;
        // - - - - - - - - - - Imagen de BackGround
        ConstraintLayout constraintLayoutBackground = findViewById(R.id.ConstraintLayoutBackground);
        String filename = preguntaIdioma.getLinkRespuestaCorrecta();
        if ( (filename != null) && (new File( QSTIMG_FOLDER + filename ).exists()) )
        {
            bitmap = BitmapFactory.decodeFile( QSTIMG_FOLDER + filename );
        }
        else if ( new File( IMAGES_FOLDER + "appimage.jpg" ).exists() )
        {
            bitmap = BitmapFactory.decodeFile(IMAGES_FOLDER + "appimage.jpg" );
        }
        else
        {
            bitmap = BitmapFactory.decodeFile(IMAGES_FOLDER + "defaultbg.jpg");
        }
        constraintLayoutBackground.setBackground( new BitmapDrawable(getResources(), bitmap));
        // - - - - - - - - - - Imagen de Personaje
        ImageView imageViewPersonaje = findViewById(R.id.ImageViewPersonaje);
        bitmap = controladorPrincipal.getPersonajeImageBitmap(controladorJuego.getPersonaje());
        imageViewPersonaje.setImageBitmap(bitmap);
        // - - - - - - - - - - Imagen de Idioma
        ImageView imageViewBandera = findViewById(R.id.ImageViewBandera);
        bitmap = controladorPrincipal.getIdiomaImageBitmap(controladorJuego.getIdioma());
        imageViewBandera.setImageBitmap(bitmap);
        // - - - - - - - - - - Texto nivel
        // presentamos un textbox con el literal de nivel
        // se calcula el color de fondo en base a posicion y longitud de arralist de niveles
        // PE, si es de 3 sera de 0=#00FF00 1=#FFFF00 2=@FF0000
        TextView textViewDificultad = findViewById(R.id.TextViewDificultad);
        //textViewDificultad.setText( controladorJuego.getNivel().getNombre() );
        textViewDificultad.setText(controladorJuego.getNivel().getTraducciones().get(controladorJuego.getIdioma()));
        textViewDificultad.setBackgroundColor(Color.rgb(50, 130, 50));
        // - - - - - - - - - - Imagen de Inicio
        ImageView imageViewInicio = findViewById(R.id.ImageViewInicio);
        imageViewInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                timer.cancel();
                finish();
            }
        });
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // RESPUESTAS TextViews

        textViewIntroduccionPregunta = findViewById(R.id.TextViewIntroduccionPregunta);
        textViewPregunta = findViewById(R.id.TextViewPregunta);
        textViewRespuesta1 = findViewById(R.id.TextViewRespuesta1);
        textViewRespuesta2 = findViewById(R.id.TextViewRespuesta2);
        textViewRespuesta3 = findViewById(R.id.TextViewRespuesta3);
        textViewRespuesta4 = findViewById(R.id.TextViewRespuesta4);

        Idioma idioma = controladorJuego.getIdioma();
        Exposicion exposicion = controladorJuego.getExposicion();
        ExposicionIdioma exposicionIdioma = exposicion.getExposicionIdiomas().get(idioma);

        if (exposicionIdioma != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(exposicionIdioma.getQstnTXT1()).append(" ");
            sb.append(controladorJuego.getPersonaje().getNombre());
            sb.append(exposicionIdioma.getQstnTXT2());

            textViewIntroduccionPregunta.setText(sb.toString());
        }

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

/*        timer = new CountDownTimer(11000, 1000) //10 second Timer
        {
            public void onTick(long l)
            {
                int tiempo = Integer.parseInt(textViewTiempo.getText().toString());
                tiempo--;
                textViewTiempo.setText(String.valueOf(tiempo));
            }
            @Override
            public void onFinish() { responder(findViewById(R.id.TextViewTimpo)); }
        }.start();
  */
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - Activacion de contador por segundos y ejecucion de llamada a otro activity
        if ( controladorJuego.getExposicion().getQuestionTimeOut() > 0 )
        {
            tiempo = controladorJuego.getExposicion().getQuestionTimeOut() + 1;
            //Log.wtf("MAGOMO", "CountDownTimer -> set tiempo by Expo" );
        }
        //Log.wtf("MAGOMO", "CountDownTimer -> Start" );
        timer = new CountDownTimer((tiempo*1000), 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                textViewTiempo.setText( Integer.toString(--tiempo) );
                //Log.wtf("MAGOMO", "CountDownTimer -> cicle " + tiempo );
            }
            public void onFinish()
            {
                //Log.wtf("MAGOMO", "CountDownTimer -> Exit by Timeout" );
                try { responder( findViewById( R.id.TextViewTimpo ) ); }
                catch (Exception ex) { Log.wtf("MAGOMO", "CountDownTimer -> Exception " + ex.getMessage()); }
            }
        }.start();
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    }

    private void responder(View view) {
        responder(view, null);
    }

    private void responder(View view, TextView textView) {

        timer.cancel();

        Intent intent = new Intent(view.getContext(), RespuestaActivity.class);

        if (view !=  null && textView != null) {
            if (textView.getText().equals(preguntaIdioma.getRespuestaCorrecta())) {
                controladorJuego.asignarRespuesta(preguntaIdioma, true);
                intent.putExtra("Acierto", true);
            } else {
                controladorJuego.asignarRespuesta(preguntaIdioma, false);
                intent.putExtra("Acierto", false);
            }
        } else {
            controladorJuego.asignarRespuesta(preguntaIdioma, false);
        }

        startActivity(intent);
        finish();
    }
    public String GetImagenSRC()
    {
        int preguntan = (controladorJuego.getCurrentPregunta(controladorJuego.getCurrentPreguntaIdioma()));
        ControladorPrincipal controladorPrincipal = ((ControladorPrincipal) super.getApplication());
        String retu = controladorPrincipal.getExposicion().getPreguntas().get(controladorJuego.getNivel()).get((preguntan -1)).getImagenRespuestaCorrectaSrc();
        return retu;
    }
    private boolean RespIsText()
    {
        int preguntan=(controladorJuego.getCurrentPregunta(controladorJuego.getCurrentPreguntaIdioma()));
        Pregunta pre = controladorJuego.getExposicion().getPreguntas().get(controladorJuego.getNivel()).get((preguntan -1));
        if(pre.getImagenRespuestaIncorrecta1Src() != null && pre.getImagenRespuestaIncorrecta2Src() !=null && pre.getImagenRespuestaIncorrecta3Src() != null && pre.getImagenRespuestaCorrectaSrc() != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private void LoadImagenesRespuestas()
    {

        int preguntan=(controladorJuego.getCurrentPregunta(controladorJuego.getCurrentPreguntaIdioma()));
        Pregunta pre = controladorJuego.getExposicion().getPreguntas().get(controladorJuego.getNivel()).get((preguntan -1));

        String respCorrecta = pre.getImagenRespuestaCorrectaSrc();
        String respIncorrecta1 = pre.getImagenRespuestaIncorrecta1Src();
        String respIncorrecta2= pre.getImagenRespuestaIncorrecta2Src();
        String respIncorrecta3 = pre.getImagenRespuestaIncorrecta3Src();

        Bitmap bitmaprespCorrecta = null;
        bitmaprespCorrecta = BitmapFactory.decodeFile(   QSTIMG_FOLDER + respCorrecta);

        Bitmap bitmaprespIncorrecta1 = BitmapFactory.decodeFile(   QSTIMG_FOLDER + respIncorrecta1);
        Bitmap bitmaprespIncorrecta2 = BitmapFactory.decodeFile(   QSTIMG_FOLDER + respIncorrecta2);
        Bitmap bitmaprespIncorrecta3 = BitmapFactory.decodeFile(   QSTIMG_FOLDER + respIncorrecta3);
        if(bitmaprespCorrecta != null)
        {
            constraintLayoutRespuesta1.setBackground( new BitmapDrawable(getResources(), bitmaprespCorrecta));
        }

        constraintLayoutRespuesta2.setBackground( new BitmapDrawable(getResources(), bitmaprespIncorrecta1));
        constraintLayoutRespuesta3.setBackground( new BitmapDrawable(getResources(), bitmaprespIncorrecta2));
        constraintLayoutRespuesta4.setBackground( new BitmapDrawable(getResources(), bitmaprespIncorrecta3));
    }
    public void LoadImagenPregunta()
    {
        String filename = GetImagenSRC();
        Bitmap  bitmap;
        if ( (filename != null) && (new File( QSTIMG_FOLDER + filename ).exists()) )
        {

            bitmap = BitmapFactory.decodeFile( QSTIMG_FOLDER + filename );
        }
        else if ( new File( IMAGES_FOLDER + "appimage.jpg" ).exists() )
        {
            bitmap = BitmapFactory.decodeFile(IMAGES_FOLDER + "appimage.jpg" );
        }
        else
        {
            bitmap = BitmapFactory.decodeFile(IMAGES_FOLDER + "defaultbg.jpg");
        }
        constraintLayoutImagenPregunta.setBackground( new BitmapDrawable(getResources(), bitmap));
    }
}
