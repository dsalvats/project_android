package com.xpomanager.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.xpomanager.R;
import com.xpomanager.adapters.AdaptadorObjects;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Exposicion;
import com.xpomanager.models.ExposicionIdioma;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.Personaje;
import com.xpomanager.utils.DateUtils;
import com.xpomanager.utils.Resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private final static int DEFAULT_INT_PERSONAJE = 0;
    private final static int DEFAULT_INT_IDIOMA = 0;
    private final static int DEFAULT_INT_NIVEL = 0;

    /*************
     * ATRIBUTOS *
     *************/
    // Controladores
    private ControladorPrincipal controladorPrincipal;

    // Constraints
    private ConstraintLayout constraintLayoutMain;
    private ConstraintLayout constraintLayoutCentral;
    private ConstraintLayout constraintLayoutRecyclerViewPersonaje;
    private ConstraintLayout constraintLayoutRecyclerViewIdioma;
    private ConstraintLayout constraintLayoutRecyclerViewNivel;

    // TextViews
    private TextView textViewLiteralExposicion;
    private TextView textViewLiteralJugarAhora;
    private TextView textViewNombreExposicion;
    private TextView textViewDescripcion;
    private TextView textViewDescripcion2;
    private TextView textViewLinkExposicion;
    private TextView textViewNivel;

    // ImageViews
    private ImageView imageViewPersonaje;
    private ImageView imageViewIdioma;
    private ImageView imageViewJugarAhora;
    //private ImageView imageViewLogoGrupo;
    private ImageView imageViewLogoMuseo;
    private ImageView imageViewQRMuseo;

    // Other
    private VideoView videoViewMain;
    private Idioma currentIdioma;

    /***********
     * MÉTODOS *
     ***********/
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

    /**
     * Evento generado al cargar la activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        checkPermissions();

        fillControladorPrincipal();
        declareElements();
        setDefaultSettings();
        fillElements();
        setElementsListeners();
    }

    /**
     * Comprueba los permisos que tiene la aplicación
     */
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Ya tienes permisos", Toast.LENGTH_LONG);
        } else {
            requestStoragePermission();
        }
    }

    /**
     * Pregunta al usuario para dar permisos de lectura en el almacenamiento externo
     */
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

    }

    /**
     * Asigna una lista de objetos a una vista dada si es posible
     * @param objects Lista de objetos a asignar
     * @param linkedView Vista donde se asignarán los objetos
     */
    private void setListToRecyclerView(List<?> objects, View linkedView) {
        RecyclerView recyclerView = null;
        Map<String, Object> extraData = new HashMap<>();

        if (!objects.isEmpty()) {
            if (objects.get(0) instanceof Personaje) {
                recyclerView = findViewById(R.id.RecyclerViewPersonaje);
            } else if (objects.get(0) instanceof Idioma) {
                recyclerView = findViewById(R.id.RecyclerViewIdioma);
            } else if (objects.get(0) instanceof Nivel) {
                recyclerView = findViewById(R.id.RecyclerViewNivel);
                extraData.put("Idioma", getSelectedIdioma());
            }
        }

        if (recyclerView != null) {
            final AdaptadorObjects adaptadorObjects = new AdaptadorObjects(objects, extraData, controladorPrincipal, linkedView);

            recyclerView.setAdapter(adaptadorObjects);

            recyclerView.setLayoutManager(
                    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }


    }

    /**
     * Inicializamos el controlador principal en esta activity
     */
    private void fillControladorPrincipal() {
        controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorPrincipal.loadInfo();
    }

    /**
     * Asignamos los listeners a los elementos deseados
     */
    private void setElementsListeners() {
        setConstraintLayoutMainListeners();
        setImageViewJugarAhoraListeners();
        setImageViewPersonajeListeners();
        setImageViewIdiomaListeners();
        setTextViewNivelListeners();
    }

    /**
     * Asignación de listeners de ConstraintLayoutMain
     */
    private void setConstraintLayoutMainListeners() {
        constraintLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConstraintLayoutRecyclerViewsInvisible();
            }
        });
    }

    /**
     * Oculta los ConstraintLayoutRecyclerView de la activity
     */
    private void setConstraintLayoutRecyclerViewsInvisible() {
        constraintLayoutRecyclerViewPersonaje.setVisibility(View.INVISIBLE);
        constraintLayoutRecyclerViewIdioma.setVisibility(View.INVISIBLE);
        constraintLayoutRecyclerViewNivel.setVisibility(View.INVISIBLE);
    }

    /**
     * Asignación de listeners de ImageViewPersonaje
     */
    private void setImageViewPersonajeListeners() {
        imageViewPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayoutRecyclerViewPersonaje.getVisibility() == View.INVISIBLE) {
                    setConstraintLayoutRecyclerViewsInvisible();
                    constraintLayoutRecyclerViewPersonaje.setVisibility(View.VISIBLE);
                } else {
                    constraintLayoutRecyclerViewPersonaje.setVisibility(View.INVISIBLE);
                    setConstraintLayoutRecyclerViewsInvisible();
                }
            }
        });
    }

    /**
     * Asignación de listners de ImageViewIdioma
     */
    private void setImageViewIdiomaListeners() {
        imageViewIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayoutRecyclerViewIdioma.getVisibility() == View.INVISIBLE) {
                    setConstraintLayoutRecyclerViewsInvisible();
                    constraintLayoutRecyclerViewIdioma.setVisibility(View.VISIBLE);
                } else {
                    constraintLayoutRecyclerViewIdioma.setVisibility(View.INVISIBLE);
                    setConstraintLayoutRecyclerViewsInvisible();
                }
            }
        });

        imageViewIdioma.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (!getSelectedIdioma().equals(currentIdioma)) {
                    updateIdioma();
                    currentIdioma = getSelectedIdioma();
                }
            }
        });
    }

    /**
     * Funcionalidad para cuando cambiamos de idioma se cambien
     * los literales y alguna información más vinculada al idioma
     */
    private void updateIdioma() {
        fillRecyclerViews();
        setNivel(getSelectedNivel());
        fillExpoInfo();
    }

    /**
     * Asignación de listeners a TextViewNivel
     */
    private void setTextViewNivelListeners(){
        textViewNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayoutRecyclerViewNivel.getVisibility() == View.INVISIBLE) {
                    setConstraintLayoutRecyclerViewsInvisible();
                    constraintLayoutRecyclerViewNivel.setVisibility(View.VISIBLE);
                } else {
                    constraintLayoutRecyclerViewNivel.setVisibility(View.INVISIBLE);
                    setConstraintLayoutRecyclerViewsInvisible();
                }
            }
        });
    }

    /**
     * Asignación de listeners de ImageViewJugarAhora
     */
    private void setImageViewJugarAhoraListeners() {
        imageViewJugarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAnyConstraintLayoutOpened()) {
                    setConstraintLayoutRecyclerViewsInvisible();
                } else {
                    Personaje personaje = getSelectedPersonaje();
                    Idioma idioma = getSelectedIdioma();
                    Nivel nivel = getSelectedNivel();

                    controladorPrincipal.iniciarJuego(personaje, idioma, nivel);

                    if (controladorPrincipal.getControladorJuego().hasNextPregunta()) {
                        Intent intent = new Intent(view.getContext(), PreguntaActivity.class);
                        startActivityForResult(intent, 0);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * Comprueba si algun ConstraintLayoutRecyclerView está abierto
     * @return Si algún ConstraintLayoutRecyclerView está abierto devuelve true.
     * En caso contrario, devuelve null.
     */
    private Boolean isAnyConstraintLayoutOpened() {
        boolean result = false;

        if (constraintLayoutRecyclerViewPersonaje.getVisibility() == View.VISIBLE ||
                constraintLayoutRecyclerViewIdioma.getVisibility() == View.VISIBLE ||
                constraintLayoutRecyclerViewNivel.getVisibility() == View.VISIBLE) {
            result = true;
        }

        return result;
    }

    /**
     * Devuelve el personaje que el usuario tiene seleccionado
     * @return Personaje que el usuario tiene seleccionado
     */
    private Personaje getSelectedPersonaje() {
        return (Personaje) imageViewPersonaje.getTag();
    }

    /**
     * Devuelve el idioma que el usuario tiene seleccionado
     * @return Idioma que el usuario tiene seleccionado
     */
    private Idioma getSelectedIdioma() {
        return (Idioma) imageViewIdioma.getTag();
    }

    /**
     * Devuelve el nivel que el usuario tiene seleccionado
     * @return Nivel que el usuario tiene seleccionado
     */
    private Nivel getSelectedNivel() {
        return (Nivel) textViewNivel.getTag();
    }

    /**
     * Inicializa todos los atributos de tipo vista con sus respectivas referencias en el context
     */
    private void declareElements() {
        // ConstraintLayouts
        constraintLayoutMain = findViewById(R.id.ConstraintLayoutMain);
        constraintLayoutCentral = findViewById(R.id.ConstraintLayoutCentral);
        constraintLayoutRecyclerViewPersonaje = findViewById(R.id.ConstraintLayoutRecyclerViewPersonaje);
        constraintLayoutRecyclerViewIdioma = findViewById(R.id.ConstraintLayoutRecyclerViewIdioma);
        constraintLayoutRecyclerViewNivel = findViewById(R.id.ConstraintLayoutRecyclerViewNivel);

        // TextViews
        textViewLiteralExposicion = findViewById(R.id.TextViewLiteralExposicion);
        textViewLiteralJugarAhora = findViewById(R.id.TextViewLiteralJugarAhora);
        textViewNombreExposicion = findViewById(R.id.TextViewNombreExposicion);
        textViewDescripcion = findViewById(R.id.TextViewDescripcion);
        textViewDescripcion2 = findViewById(R.id.TextViewDescripcion2);
        textViewLinkExposicion = findViewById(R.id.TextViewLinkExposicion);
        textViewNivel = findViewById(R.id.TextViewNivel);

        // ImageViews
        imageViewPersonaje = findViewById(R.id.ImageViewPersonaje);
        imageViewIdioma = findViewById(R.id.ImageViewIdioma);
        imageViewJugarAhora = findViewById(R.id.ImageViewJugarAhora);
        // imageViewLogoGrupo = findViewById(R.id.ImageViewLogoGrupo);
        imageViewLogoMuseo = findViewById(R.id.ImageViewLogoMuseo);
        imageViewQRMuseo = findViewById(R.id.ImageViewQRMuseo);

        // Other
        videoViewMain = findViewById(R.id.VideoViewMain);

    }

    /**
     * Asigna un personaje, idioma y nivel por defecto al abrir la aplicación
     */
    private void setDefaultSettings() {
        setPersonaje(controladorPrincipal.getExposicion().getPersonajes().get(DEFAULT_INT_PERSONAJE));
        setIdioma(controladorPrincipal.getExposicion().getIdiomas().get(DEFAULT_INT_IDIOMA));
        setNivel(controladorPrincipal.getExposicion().getNiveles().get(DEFAULT_INT_NIVEL));
    }

    /**
     * Asigna las imagenes de la aplicación
     */
    private void setImages() {
        setAppImage();
        setMuseoLogo();
        setExpoURLQR();
        setPlayNowImage();
    }

    /**
     * Asigna la imagen de jugar ahora y su texto a mostrar dependiendo del idioma
     */
    private void setPlayNowImage() {
        imageViewJugarAhora.setImageResource(Resources.getPlayNow());
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        pulse.setRepeatCount(Animation.INFINITE);
        constraintLayoutCentral.startAnimation(pulse);
    }

    /**
     * Asigna la imagen del QR dependiendo de la URL de la exposición
     */
    private void setExpoURLQR() {
        imageViewQRMuseo.setImageBitmap(controladorPrincipal.getMainQRBitMap(getSelectedIdioma()));
    }

    /**
     * Asigna un video de fondo o una imagen dependiendo del JSON
     */
    private void setAppImage() {
        if (controladorPrincipal.isAppImageVideo()) {
            controladorPrincipal.setAppVideo(videoViewMain);
        } else {
            Drawable drawable = new BitmapDrawable(this.getResources(), controladorPrincipal.getAppImageBitmap());
            videoViewMain.setBackground(drawable);
        }
    }

    /**
     * Asigna la imagen del logo del museo
     */
    private void setMuseoLogo() {
        imageViewLogoMuseo.setImageResource(Resources.getMuseumLogo());
    }

    /**
     * Selecciona un personaje
     * @param personaje Personaje a seleccionar
     */
    private void setPersonaje(Personaje personaje) {
        imageViewPersonaje.setTag(personaje);
        setPersonajeImagen();
    }

    /**
     * Asigna la imagen de un personaje
     */
    private void setPersonajeImagen() {
        Bitmap bitmap = controladorPrincipal.getPersonajeImageBitmap(getSelectedPersonaje());

        imageViewPersonaje.setImageBitmap(bitmap);
    }

    /**
     * Selecciona un idioma
     * @param idioma idioma a seleccionar
     */
    private void setIdioma(Idioma idioma) {
        imageViewIdioma.setTag(idioma);
        setIdiomaImagen();
        currentIdioma = idioma;
    }

    /**
     * Asigna la imagen de una bandera
     */
    private void setIdiomaImagen() {
        Bitmap bitmap = controladorPrincipal.getIdiomaImageBitmap(getSelectedIdioma());

        imageViewIdioma.setImageBitmap(bitmap);
    }

    /**
     * Selecciona un nivel
     * @param nivel Nivel a seleccionar
     */
    private void setNivel(Nivel nivel) {
        textViewNivel.setTag(nivel);
        setNivelText();
    }

    /**
     * Asigna un texto al nivel dependiendo del idioma seleccionado
     */
    private void setNivelText() {
        textViewNivel.setText(getSelectedNivel().getTraducciones().get(getSelectedIdioma()));
    }

    /**
     * Asigna los personajes, idiomas y niveles a los recycler view
     */
    private void fillRecyclerViews() {
        List<Personaje> personajes = controladorPrincipal.getExposicion().getPersonajes();
        List<Idioma> idiomas = controladorPrincipal.getExposicion().getIdiomas();
        List<Nivel> niveles = controladorPrincipal.getExposicion().getNiveles();

        setListToRecyclerView(personajes, imageViewPersonaje);
        setListToRecyclerView(idiomas, imageViewIdioma);
        setListToRecyclerView(niveles, textViewNivel);
    }

    /**
     * Asigna la información de la exposición
     */
    private void fillExpoInfo() {
        Exposicion exposicion = controladorPrincipal.getExposicion();
        Idioma idioma = getSelectedIdioma();
        ExposicionIdioma exposicionIdioma = exposicion.getExposicionIdiomas().get(idioma);

        if (exposicionIdioma != null) {
            textViewLiteralExposicion.setText(exposicionIdioma.getStartExposition());
            textViewNombreExposicion.setText(exposicionIdioma.getStartExpoTitle());
            textViewDescripcion.setText(exposicionIdioma.getStartExpoIntro());

            StringBuilder sb = new StringBuilder();

            if (exposicionIdioma.getStartExpoDateFrom() != null) {
                String dateFrom = DateUtils.rawDateToFormated(exposicion.getFechaInicio());
                sb.append(exposicionIdioma.getStartExpoDateFrom());
                sb.append(" ");
                sb.append(dateFrom);
            }

            if (!sb.toString().equals("")) {
                sb.append(" ");
            }

            if (exposicionIdioma.getStartExpoDateTo() != null) {
                String dateTo = DateUtils.rawDateToFormated(exposicion.getFechaFin());
                sb.append(exposicionIdioma.getStartExpoDateTo());
                sb.append(" ");
                sb.append(dateTo);
            }

            textViewDescripcion2.setText(sb.toString());
            textViewLinkExposicion.setText(exposicionIdioma.getStartExpoURL());
            textViewLiteralJugarAhora.setText(exposicionIdioma.getStartPlayButton());
        }

    }

    /**
     * Llama a otros métodos para acabar de llenar/settear elementos
     */
    private void fillElements() {
        fillExpoInfo();
        fillRecyclerViews();
        setImages();
    }
}
