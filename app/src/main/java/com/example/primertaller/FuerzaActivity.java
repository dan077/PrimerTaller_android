package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FuerzaActivity extends AppCompatActivity implements View.OnClickListener {

    Button hallarfuerza;
    EditText masa,aceleracion;
    float fuerza,acceleration,dough;
    ImageButton ayuda, home, exit;
    Funciones adminFunciones = new Funciones(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza);

        hallarfuerza = findViewById(R.id.btnhallarfuerza);
        masa = findViewById(R.id.edtmasa);
        aceleracion = findViewById(R.id.edtaceleracion);
        ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
        home = (ImageButton) findViewById(R.id.imgbtnhome);
        exit = (ImageButton) findViewById(R.id.imgbtnexit);

        hallarfuerza.setOnClickListener(this);
        adminFunciones.menuHomeListener(ayuda,home,exit);

    }

    @Override
    public void onClick(View v) {
        adminFunciones.menuHomeAccion(ayuda,home,exit,v);
        switch (v.getId()){
            case R.id.btnhallarfuerza:
                if (!masa.getText().toString().isEmpty() && !aceleracion.getText().toString().isEmpty()){
                    dough =Float.valueOf(masa.getText().toString());
                    acceleration = Float.valueOf(aceleracion.getText().toString());
                    fuerza = dough * acceleration;
                    new AlertDialog.Builder(this)
                            .setTitle("TERMINADO")
                            .setMessage("La fuerza es: "+ fuerza + " N")
                            .setPositiveButton(android.R.string.yes, null).show();
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("La fuerza no se pudo hallar porque faltan un datos")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
                break;
        }
    }
}