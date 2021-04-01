package com.example.primertaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FisicaActivity extends AppCompatActivity implements View.OnClickListener {

    Button velocidad, fuerza, voltaje;
    ImageButton ayuda, home, exit;
    Funciones adminFunciones = new Funciones(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);

        velocidad = findViewById(R.id.btnvelocidad);
        fuerza = findViewById(R.id.btnfuerza);
        voltaje = findViewById(R.id.btnvoltaje);
        ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
        home = (ImageButton) findViewById(R.id.imgbtnhome);
        exit = (ImageButton) findViewById(R.id.imgbtnexit);

        velocidad.setOnClickListener(this);
        fuerza.setOnClickListener(this);
        voltaje.setOnClickListener(this);

        adminFunciones.menuHomeListener(ayuda,home,exit);
    }

    @Override
    public void onClick(View v) {
        adminFunciones.menuHomeAccion(ayuda,home,exit,v);
        Intent i;
        switch (v.getId()){
            case R.id.btnvelocidad:
                i = new Intent(getApplicationContext(), VelocidadActivity.class);
                startActivity(i);
                break;
            case R.id.btnfuerza:
                i = new Intent(getApplicationContext(), FuerzaActivity.class);
                startActivity(i);
                break;
            case R.id.btnvoltaje:
                i = new Intent(getApplicationContext(), VoltajeActivity.class);
                startActivity(i);
                break;
        }
    }
}