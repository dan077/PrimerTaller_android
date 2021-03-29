package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button fisica,velocidad,voltaje,fuerza;
    ImageButton ayuda, home, exit;
    AlertDialog.Builder builder, help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         fisica = findViewById(R.id.btnfisica);
         velocidad = findViewById(R.id.btnvelocidad);
         voltaje = findViewById(R.id.btnvoltaje);
         fuerza = findViewById(R.id.btnfuerza);
         ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
         home = (ImageButton) findViewById(R.id.imgbtnhome);
         exit = (ImageButton) findViewById(R.id.imgbtnexit);


         fisica.setOnClickListener(this);
         velocidad.setOnClickListener(this);
         voltaje.setOnClickListener(this);
         fuerza.setOnClickListener(this);
         ayuda.setOnClickListener(this);
         home.setOnClickListener(this);
         exit.setOnClickListener(this);

         help = new AlertDialog.Builder(this);
         help.setTitle("ayuda");
         help.setMessage("APP v1, realizada por DANIEL VEDA, LUIS PUELLO, ANDRES CASTRO");
         help.setPositiveButton("Ok", null);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Ayuda");
        builder.setMessage("¿Quieres cerrar sesión?");

        builder.setPositiveButton("Ok", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"saliendo",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtnexit:
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.imgbtnayuda:
                AlertDialog dialog1 = help.create();
                dialog1.show();
                break;
            case R.id.imgbtnhome:
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                break;

        }
    }
}