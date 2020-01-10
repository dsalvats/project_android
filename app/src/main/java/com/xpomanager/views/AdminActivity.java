// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
// AdminActivity.java
// Let Shutdown the App & Load App Config Set
// ...............................................................................
// MaGoMo - Created: 2019.12.16 | Last UpDate: 2020.01.08
// SET Intent Extra "nologin" = true to set NO LOGIN mode
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
package com.xpomanager.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorPrincipal;

public class AdminActivity extends AppCompatActivity {

    private final static String APP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final static String XPO_FOLDER = APP_FOLDER + "/XPOmanager/";
    public static CountDownTimer mcdadm;

    // - - - - - - - - - - - - - - - - - - - - - ATRIBUTOS
    String adminUser = "Admin", adminPass = "Admin";

    @Override
    public void onBackPressed() {  }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (getSupportActionBar() != null) { getSupportActionBar().hide(); }

        // - - - - Control de intent by NO LOGIN
        if ( ! getIntent().getBooleanExtra("nologin",false) ) // - - - - IF LOGIN (standar)
        {
            // - - - - - - - - - - llama funcion de cuenta atras para salida automatica
            theCountDown();
            // - - - - - - - - - - carga info de objeto controladorPRINCIPAL y controladorJUEGO
            ControladorPrincipal controladorPrincipal = (ControladorPrincipal) super.getApplication();
            // - - - - - - - - - - Control de existencia de datos de user y pass
            if (controladorPrincipal.getExposicion() != null) {
                if (controladorPrincipal.getExposicion().getAdminUser() != null && controladorPrincipal.getExposicion().getAdminUser().length() > 0) {
                    // adminUser = controladorPrincipal.getExposicion().getAdminUser();
                }
                if (controladorPrincipal.getExposicion().getAdminPassword() != null && controladorPrincipal.getExposicion().getAdminPassword().length() > 0) {
                    // adminPass = controladorPrincipal.getExposicion().getAdminPassword();
                }
            }
            // - - - - - - - - - - LOGIN Button
            Button buttonLogin = findViewById(R.id.ButtonLogin);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // - - - - - - - - - - CONTENEDORES
                    LinearLayout loginContainer = findViewById(R.id.linearLayoutLogin);
                    LinearLayout buttonsContainer = findViewById(R.id.linearLayoutButtons);
                    // - - - - - - - - - - LOGIN BOXES
                    EditText editTextUser = findViewById(R.id.EditTextUser);
                    EditText editTextPass = findViewById(R.id.EditTextPassword);
                    if (editTextUser.getText().toString().equals( adminUser )) {
                        if (editTextUser.getText().toString().equals( adminPass )) {
                            mcdadm.cancel();
                            loginContainer.setVisibility(View.INVISIBLE);
                            buttonsContainer.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
        else // - - - - NO LOGIN (por no SET files)
        {
            // - - - - - - - - - - CONTENEDORES
            LinearLayout loginContainer = findViewById(R.id.linearLayoutLogin);
            LinearLayout buttonsContainer = findViewById(R.id.linearLayoutButtons);
            loginContainer.setVisibility(View.INVISIBLE);
            buttonsContainer.setVisibility(View.VISIBLE);
        }

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

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    }

    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - metodo de salida de aplicacion
    public void appShutdown()
    {
        mcdadm.cancel();
        finishAndRemoveTask();
    }

    // - - - - - - - - - metodo de SALIDA de activity
    public void theExecActivity()
    {
        mcdadm.cancel(); // cancela timerout
        Intent intent = new Intent( getBaseContext(), MainActivity.class );
        startActivity( intent );
        finish();
    }
    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -

    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - metodo de update de aplicacion
    public void appUpdate()
    {
        Log.wtf("MAGOMO", "appUpdate -> start");
        // - - - - creacion de activity para cuadro de dialogo de seleccion de archivo para descomprimir
        //Intent intentFileDiag = new Intent( Intent.ACTION_GET_CONTENT ).addCategory( Intent.CATEGORY_OPENABLE ).setType( "*/*" );
        Intent intentFileDiag = new Intent( Intent.ACTION_OPEN_DOCUMENT ).addCategory( Intent.CATEGORY_OPENABLE ).setType( "*/*" );
        try { startActivityForResult( Intent.createChooser(intentFileDiag, "Select a File to Upload"), 123); }
        catch (android.content.ActivityNotFoundException ex) { Toast.makeText( this, "Please install a File Manager.", Toast.LENGTH_SHORT).show(); }
        Log.wtf("MAGOMO", "appUpdate -> exit");
    }

    // - - - - - - - - - - metodo de PERMISSIONS
    public void setThePermissions()
    {
        // Check whether this app has write external storage permission or not.
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(AdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        // If do not grant write external storage permission.
        if(writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
        {
            Log.wtf("MAGOMO", "setThePermissions -> NO PERMISSION");
            // Request user to grant write external storage permission.
            ActivityCompat.requestPermissions(AdminActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 555);
        }
        else { Log.wtf("MAGOMO", "setThePermissions -> PERMISSION_GRANTED !!"); }
    }

    // - - - - - - - - - - reescritura de metodo para obtener uri de archivo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData)
    {
        finishActivity(123);
        setThePermissions();
        Log.wtf("MAGOMO", "onActivityResult -> finishActivity executed");
        super.onActivityResult( requestCode, resultCode, intentData );
        // control codigo de activity y resultado
        if( requestCode == 123 && resultCode == RESULT_OK )
        {
            Uri fileUri = intentData.getData();
            String fileName = fileUri.toString();
            Log.wtf("MAGOMO", "onActivityResult -> fileName = " + fileName );
            // - - - - - Ejecuta descompresion de archivo ZIP
            try {
                if ( unpackZip( fileUri ) ) { Toast.makeText(this, "SET de datos CORRECTA", Toast.LENGTH_LONG).show(); }
                else { Toast.makeText(this, "ERROR en SET de datos", Toast.LENGTH_LONG).show(); }
            } catch (IOException e) { e.printStackTrace(); }
            // - - - - / Ejecuta descompresion de archivo ZIP
        }
        Log.wtf("MAGOMO", "onActivityResult -> exit" );
    }

    // - - - - - - - - - - metodo de ELIMINACION de DIRECTORIO
    private void deleteZipDir( String delFolder, int thecicle )
    {
        Log.wtf("MaGoMo >> ", "deleteZipDir (" + thecicle + ") -> delFolder = " + delFolder );
        // XPO_FOLDER directorio base de aplicacion
        File dir = new File( delFolder );
        String mensaje;
        // - - - - si existe y es directorio
        if ( dir.isDirectory() && dir.exists() )
        {
            // - - - - - recoge vector de string con nombres de contenido de directorio
            String[] children = dir.list();
            // - - - - - ciclo de eliminacion de archivos
            for (int i = 0; i < children.length; i++)
            {
                File elementDir = new File( dir, children[i] );
                if ( elementDir.isDirectory() && elementDir.exists() ) { deleteZipDir( elementDir.toString(), thecicle+1 ); }
                else
                {
                    mensaje = "deleteZipDir (" + thecicle + ") item " + elementDir.getName();
                    if ( elementDir.exists() )
                    {
                        mensaje += " Existe";
                        if ( elementDir.canWrite() ) { mensaje += " & Writable"; }
                        else { mensaje += " & UNWritable"; }
                        if ( this.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED)
                        { mensaje += " (CON permiso)"; } else { mensaje += " (SIN permiso)"; }
                        if ( elementDir.canRead() ) { mensaje += " & Readable"; } else { mensaje += " & UNReadable"; }
                        if (elementDir.delete()) { mensaje += " & ELIMINADO !"; } else { mensaje += " & DEL_ERROR !"; }
                    }
                    else { mensaje += " NO Existe !!!"; }
                    Log.wtf("MaGoMo >> ", mensaje );
                }
            }
            mensaje = "deleteZipDir (" + thecicle + ") -> " + dir.getName();
            if ( dir.delete() ) { mensaje += " & ELIMINADO !"; }
            else { mensaje += " & DEL_ERROR !"; }
            Log.wtf("MaGoMo >> ", mensaje );
        }
        Log.wtf("MaGoMo >> ", "deleteZipDir -> endprocess ("+thecicle+")" );
    }

    // - - - - - - - - - - metodo de DESCOMPRESION de archivo ZIP
    private boolean unpackZip( Uri zipfile ) throws IOException
    {
        Boolean returnBoolean = true;
        String mensaje;
        File fmd;

        Log.wtf("MaGoMo >> ", "unpackZip -> Start");

        // APP_FOLDER: path de aplicacion | XPO_FOLDER: path de datos de aplicacion
        String expandFolder = XPO_FOLDER + "MaGoMo";
        File baseDirectory = new File( expandFolder );
        Log.wtf("MaGoMo >> ", "unpackZip -> expandFolder = " + expandFolder );

        // - - - - - elimina directorio si existe para eliminar copia anterior
        if ( baseDirectory.isDirectory() && baseDirectory.exists() )
        {
            Log.wtf("MaGoMo >> ", "unpackZip -> expandFolder es directorio... elimina" );
            deleteZipDir( expandFolder, 0 );
        }

        // - - - - - crea directorio nuevamente
        if ( baseDirectory.mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + baseDirectory.getName() + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + baseDirectory.getName() + " ERROR !" ); }

        // control try - catch
        try {
            String filename;
            InputStream inputStream = getContentResolver().openInputStream( zipfile );
            ZipInputStream bufferedInputStream = new ZipInputStream( new BufferedInputStream( inputStream ) );

            Log.wtf("MaGoMo >> ", "unpackZip -> inputStream start" );

            ZipEntry zipEntry;
            byte[] buffer = new byte[1024];
            int count;

            // ciclo por cada elemento del zip
            while ( ( zipEntry = bufferedInputStream.getNextEntry() ) != null )
            {
                // lee entrada en archivo comprimido
                filename = zipEntry.getName();

                Log.wtf("MaGoMo >> ", "unpackZip -> entry name = " + expandFolder + "/" + filename );

                if ( zipEntry.isDirectory() )
                {
                    fmd = new File(expandFolder + "/" + filename );
                    if ( fmd.mkdirs() ) { mensaje = "unpackZip -> SUCCESSFUL directory creating " + fmd.getPath(); }
                    else { mensaje = "unpackZip -> ERROR creating directory " + fmd.getPath(); }
                    Log.wtf("MaGoMo >> ", mensaje + fmd.getName() );
                }
                else
                {
                    // - - - - - Creacion de directorios - split the array using '/' as a delimiter
                    String[] thedirs = filename.split("/"); String pathname = "";
                    for ( int i = 0; i < ( thedirs.length - 1); i++) { if (i>0) { pathname += "/"; } pathname += thedirs[i]; }
                    Log.wtf("MaGoMo >> ", "unpackZip -> CREATING DIRECTORY " + expandFolder + "/" + pathname );
                    new File(expandFolder + "/" + pathname ).mkdirs();

                    Log.wtf("MaGoMo >> ", "unpackZip -> CREATING FILE " + expandFolder + "/" + filename );
                    // - - - - - Graba archivo
                    FileOutputStream fout = new FileOutputStream( expandFolder + "/" + filename );
                    while ( ( count = bufferedInputStream.read(buffer)) > 0 ) { fout.write(buffer, 0, count); }
                    fout.close();
                    bufferedInputStream.closeEntry();
                }

            }

            bufferedInputStream.close();

        } catch(IOException e) { e.printStackTrace(); returnBoolean = false; Log.wtf("MaGoMo >> ", "unpackZip -> IOException: " + e.getMessage() ); }


        Log.wtf("MaGoMo >> ", "unpackZip -> salida" );

        return returnBoolean;
    }
    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -

    // - - - - - - - - - metodo de activacion de contador por segundos y ejecucion de llamada a otro activity
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
                try { theExecActivity(); } catch (Exception ex) { Log.wtf("MaGoMo >> ", "theCountDown -> Exception: " + ex.getMessage() );  }
            }
        }.start();
    }
}
