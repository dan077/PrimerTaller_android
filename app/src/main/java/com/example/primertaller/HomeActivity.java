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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button fisica,geometria,texto;
    ImageButton ayuda, home, exit;
    AlertDialog.Builder builder, help;
    Funciones adminFunciones = new Funciones(this);
    JsonParser parser;
    JsonObject gsonObj;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         fisica = findViewById(R.id.btnfisica);
         geometria = findViewById(R.id.btngeometria);
         texto = findViewById(R.id.btntexto);
        nombre = findViewById(R.id.nombre_home_user);

         ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
         home = (ImageButton) findViewById(R.id.imgbtnhome);
         exit = (ImageButton) findViewById(R.id.imgbtnexit);
         parser = new JsonParser();
         gsonObj = parser.parse(adminFunciones.obtenerUsuario()).getAsJsonObject();

         fisica.setOnClickListener(this);
         geometria.setOnClickListener(this);
         texto.setOnClickListener(this);

        // ayuda.setOnClickListener(this);
         //home.setOnClickListener(this);
         //exit.setOnClickListener(this);
        adminFunciones.menuHomeListener(ayuda,home,exit);

        nombre.setText(gsonObj.get("nombre").getAsString()+ " " +gsonObj.get("apellido").getAsString());

    }


    @Override
    public void onClick(View v) {
        Intent i;
        adminFunciones.menuHomeAccion(ayuda,home,exit,v);
        switch (v.getId()){
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