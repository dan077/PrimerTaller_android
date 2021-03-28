package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import static java.sql.Types.NULL;

public class ValidarCodigoActivity extends AppCompatActivity implements View.OnClickListener {
    int dig5;
    Button envcodigo, validar;
    EditText codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_codigo);
        codigo = findViewById(R.id.edtcodigo);
        envcodigo = findViewById(R.id.btnenviarcodigo);
        validar = findViewById(R.id.btnvalidar);

        envcodigo.setOnClickListener(this);
        validar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnenviarcodigo:
                Random rnd = new Random();
                dig5 = rnd.nextInt(90000)+10000; //siempre 5 digitos
                Toast.makeText(getApplicationContext(), ""+dig5, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnvalidar:
                if (!codigo.getText().toString().isEmpty()){
                    int numero = Integer.parseInt(codigo.getText().toString());
                    if (numero == dig5){
                        Intent i = new Intent(getApplicationContext(),RestablecerActivity.class);
                        startActivity(i);
                    }else{
                        new AlertDialog.Builder(this)
                                .setTitle("ERROR")
                                .setMessage("El código que ingresó no es correcto")
                                .setPositiveButton(android.R.string.yes, null).show();
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Ingrese el código que se le envió a su correo")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
                break;
        }
    }
}