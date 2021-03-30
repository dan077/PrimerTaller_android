package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VelocidadActivity extends AppCompatActivity implements View.OnClickListener {

    Button hallar;
    EditText distancia, tiempo;
    float velocidad, dist, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad);

        hallar = findViewById(R.id.btnhallarfuerza);
        distancia = findViewById(R.id.edtmasa);
        tiempo = findViewById(R.id.edtaceleracion);

        hallar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnhallarfuerza:
                if (!distancia.getText().toString().isEmpty() && !tiempo.getText().toString().isEmpty()){
                    dist = Float.valueOf(distancia.getText().toString());
                    time = Float.valueOf(tiempo.getText().toString());
                    if (dist >= 0 && time > 0){
                        velocidad = dist/time;
                        new AlertDialog.Builder(this)
                                .setTitle("TERMINADO")
                                .setMessage("La velocidad es: "+ velocidad+ " km/h")
                                .setPositiveButton(android.R.string.yes, null).show();
                    }else{
                        new AlertDialog.Builder(this)
                                .setTitle("ERROR")
                                .setMessage("Algo ha salido mal, tal vez colocaste un dato con signo negativo o el tiempo es cero")
                                .setPositiveButton(android.R.string.yes, null).show();
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("La velocidad no se puede calcular porque no ha ingresado todos los datos")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
                break;
        }
    }
}