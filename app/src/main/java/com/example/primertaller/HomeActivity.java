package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button fisica,geometria,texto;
    ImageButton ayuda, home, exit;
    AlertDialog.Builder builder, help;
    Funciones adminFunciones = new Funciones(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         fisica = findViewById(R.id.btnfisica);
         geometria = findViewById(R.id.btngeometria);
         texto = findViewById(R.id.btntexto);
         ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
         home = (ImageButton) findViewById(R.id.imgbtnhome);
         exit = (ImageButton) findViewById(R.id.imgbtnexit);



         fisica.setOnClickListener(this);
         geometria.setOnClickListener(this);
         texto.setOnClickListener(this);
         ayuda.setOnClickListener(this);
         home.setOnClickListener(this);
         exit.setOnClickListener(this);

         help = new AlertDialog.Builder(this);
         help.setTitle("ayuda");
         help.setMessage("APP v1, realizada por DANIEL VEGA, LUIS PUELLO, ANDRES CASTRO");
         help.setPositiveButton("Ok", null);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Ayuda");
        builder.setMessage("¿Quieres cerrar sesión?");

        builder.setPositiveButton("Ok", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"saliendo",Toast.LENGTH_LONG).show();
                adminFunciones.CerrarSesion();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("Cancelar", null);

    }


    @Override
    public void onClick(View v) {
        Intent i;

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
            case R.id.btnfisica:
                i = new Intent(this,FisicaActivity.class);
                startActivity(i);
                break;
            case R.id.btngeometria:
                i = new Intent(this,GeometriaActivity.class);
                startActivity(i);
                break;

            case R.id.btntexto:
                i = new Intent(this,TextoActivity.class);
                startActivity(i);
                break;

        }
    }

    @Override public void onBackPressed() { return; }

}