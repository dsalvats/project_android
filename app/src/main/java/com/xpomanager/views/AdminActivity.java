package com.xpomanager.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import androidx.appcompat.app.AppCompatActivity;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorJuego;
import com.xpomanager.controllers.ControladorPrincipal;

public class AdminActivity extends AppCompatActivity {

    private final static String APP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final static String XPO_FOLDER = APP_FOLDER + "/XPOmanager/";
    public static CountDownTimer mcdadm;

    // - - - - - - - - - - - - - - - - - - - - - ATRIBUTOS

    String appUser, appPass;

    @SuppressLint("MissingSuperCall")

    @Override
    public void onBackPressed() {  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (getSupportActionBar() != null) { getSupportActionBar().hide(); }


        // - - - - - - - - - - carga info de objeto controladorPRINCIPAL
        ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
        // - - - - - - - - - - carga info de objeto controladorJUEGO
        ControladorJuego controladorJuego = controladorPrincipal.getControladorJuego();

        // - - - - - - - - - - Crea contador para salida por no login
        theCountDown();

        // - - - - - - - - - - LOGIN Button
        Button buttonLogin = findViewById(R.id.ButtonLogin);
        buttonLogin.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                LinearLayout loginContainer = findViewById(R.id.linearLayoutLogin);
                LinearLayout buttonsContainer = findViewById(R.id.linearLayoutButtons);
                EditText editTextUser = findViewById(R.id.EditTextUser);
                EditText editTextPass = findViewById(R.id.EditTextPassword);
                if (editTextUser.getText().toString().equals("Admin"))
                {
                    if (editTextUser.getText().toString().equals("Admin"))
                    {
                        mcdadm.cancel();
                        loginContainer.setVisibility(View.INVISIBLE);
                        buttonsContainer.setVisibility(View.VISIBLE);
                    }
                }
            }
        } );


        // - - - - - - - - - - SHUTDOWN Button
        Button buttonShutdown = findViewById(R.id.ButtonShutdown);
        buttonShutdown.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { appShutdown(); }
        } );

        // - - - - - - - - - - UPDATE Button
        Button buttonUpdate = findViewById(R.id.ButtonUpdate);
        buttonUpdate.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick(View view) { appUpdate(); }
            } );

        // - - - - - - - - - - CLOSE Button
        TextView textClose = findViewById(R.id.TextClose);
        textClose.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { theExecActivity(); }
        } );



    }

    public void appShutdown()
    {
        mcdadm.cancel();
        finishAndRemoveTask();
    }

    public boolean appUpdate()
    {
        boolean theReturn = false;
        //
        //Intent intent = new Intent().setType("*/*").setAction( Intent.ACTION_GET_CONTENT );
        //startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
        //intent.onActivityResult()// stringUri = uri.toString();
        return theReturn;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
        }
    }

    // metodo de ejecucion de activity segun estado de pregunta
    public void theExecActivity()
    {
        mcdadm.cancel();
        Intent intent;
        intent = new Intent( getBaseContext(), RespuestaActivity.class );
        startActivity( intent );
        finish();
    }

    // metodo de activacion de contador por segundos y ejecucion de llamada a otro activity
    public void theCountDown()
    {
        mcdadm = new CountDownTimer(60000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                /*mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);*/
            }
            public void onFinish()
            {
                try { theExecActivity(); } catch (Exception ex) {  }
            }
        }.start();
    }

}
