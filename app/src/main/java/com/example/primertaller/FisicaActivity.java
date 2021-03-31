package com.example.primertaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FisicaActivity extends AppCompatActivity implements View.OnClickListener {

    Button velocidad, fuerza, voltaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);

        velocidad = findViewById(R.id.btnvelocidad);
        fuerza = findViewById(R.id.btnfuerza);
        voltaje = findViewById(R.id.btnvoltaje);

        velocidad.setOnClickListener(this);
        fuerza.setOnClickListener(this);
        voltaje.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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