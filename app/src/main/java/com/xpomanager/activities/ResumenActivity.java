package com.xpomanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xpomanager.R;

public class ResumenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resumen);
    }

}