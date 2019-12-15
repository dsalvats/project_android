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

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Ya tienes permisos", Toast.LENGTH_LONG);
        } else {
            requestStoragePermission();
        }
    }

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

    private void fillControladorPrincipal() {
        controladorPrincipal = (ControladorPrincipal) super.getApplication();
        controladorPrincipal.loadInfo();
    }

    private void setElementsListeners() {
        setConstraintLayoutMainListeners();
        setImageViewJugarAhoraListeners();
        setImageViewPersonajeListeners();
        setImageViewIdiomaListeners();
        setTextViewNivelListeners();
    }

    private void setConstraintLayoutMainListeners() {
        constraintLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConstraintLayoutRecyclerViewsInvisible();
            }
        });
    }

    private void setConstraintLayoutRecyclerViewsInvisible() {
        constraintLayoutRecyclerViewPersonaje.setVisibility(View.INVISIBLE);
        constraintLayoutRecyclerViewIdioma.setVisibility(View.INVISIBLE);
        constraintLayoutRecyclerViewNivel.setVisibility(View.INVISIBLE);
    }

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

    private void updateIdioma() {
        fillRecyclerViews();
        fillExpoInfo();
    }

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

    private Boolean isAnyConstraintLayoutOpened() {
        boolean result = false;

        if (constraintLayoutRecyclerViewPersonaje.getVisibility() == View.VISIBLE ||
                constraintLayoutRecyclerViewIdioma.getVisibility() == View.VISIBLE ||
                constraintLayoutRecyclerViewNivel.getVisibility() == View.VISIBLE) {
            result = true;
        }

        return result;
    }

    private Personaje getSelectedPersonaje() {
        return (Personaje) imageViewPersonaje.getTag();
    }

    private Idioma getSelectedIdioma() {
        return (Idioma) imageViewIdioma.getTag();
    }

    private Nivel getSelectedNivel() {
        return (Nivel) textViewNivel.getTag();
    }

    private void declareElements() {
        // ConstraintLayouts
        constraintLayoutMain = findViewById(R.id.ConstraintLayoutMain);
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

    private void setDefaultSettings() {
        setPersonaje(controladorPrincipal.getExposicion().getPersonajes().get(DEFAULT_INT_PERSONAJE));
        setIdioma(controladorPrincipal.getExposicion().getIdiomas().get(DEFAULT_INT_IDIOMA));
        setNivel(controladorPrincipal.getExposicion().getNiveles().get(DEFAULT_INT_NIVEL));
    }

    private void setImages() {
        setAppImage();
        //setGroupLogo();
        setMuseoLogo();
        setExpoURLQR();
        setPlayNowImage();
    }

    private void setPlayNowImage() {
        imageViewJugarAhora.setImageBitmap(controladorPrincipal.getJugarAhoraBitmap());
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        pulse.setRepeatCount(Animation.INFINITE);
        pulse.setRepeatMode(Animation.REVERSE);
        imageViewJugarAhora.startAnimation(pulse);
    }

    private void setExpoURLQR() {
        imageViewQRMuseo.setImageBitmap(controladorPrincipal.getMainQRBitMap(getSelectedIdioma()));
    }

    private void setAppImage() {
        if (controladorPrincipal.isAppImageVideo()) {
            controladorPrincipal.setAppVideo(videoViewMain);
        } else {
            Drawable drawable = new BitmapDrawable(this.getResources(), controladorPrincipal.getAppImageBitmap());
            videoViewMain.setBackground(drawable);
        }
    }

    /*private void setGroupLogo() {
        imageViewLogoGrupo.setImageBitmap(controladorPrincipal.getLogoGrupoBitMap());
    }*/

    private void setMuseoLogo() {
        imageViewLogoMuseo.setImageBitmap(controladorPrincipal.getLogoMuseoBitmap());
    }

    private void setPersonaje(Personaje personaje) {
        imageViewPersonaje.setTag(personaje);
        setPersonajeImagen();
    }

    private void setPersonajeImagen() {
        Bitmap bitmap = controladorPrincipal.getPersonajeImageBitmap(getSelectedPersonaje());

        imageViewPersonaje.setImageBitmap(bitmap);
    }

    private void setIdioma(Idioma idioma) {
        imageViewIdioma.setTag(idioma);
        setIdiomaImagen();
        currentIdioma = idioma;
    }

    private void setIdiomaImagen() {
        Bitmap bitmap = controladorPrincipal.getIdiomaImageBitmap(getSelectedIdioma());

        imageViewIdioma.setImageBitmap(bitmap);
    }

    private void setNivel(Nivel nivel) {
        textViewNivel.setTag(nivel);
        setNivelText();
    }

    private void setNivelText() {
        textViewNivel.setText(getSelectedNivel().getTraducciones().get(getSelectedIdioma()));
    }

    private void fillRecyclerViews() {
        List<Personaje> personajes = controladorPrincipal.getExposicion().getPersonajes();
        List<Idioma> idiomas = controladorPrincipal.getExposicion().getIdiomas();
        List<Nivel> niveles = controladorPrincipal.getExposicion().getNiveles();

        setListToRecyclerView(personajes, imageViewPersonaje);
        setListToRecyclerView(idiomas, imageViewIdioma);
        setListToRecyclerView(niveles, textViewNivel);
    }

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

    private void fillElements() {
        fillExpoInfo();
        fillRecyclerViews();
        setImages();
    }
}
