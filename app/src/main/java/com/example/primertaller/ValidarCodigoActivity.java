package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.sql.Types.NULL;

public class ValidarCodigoActivity extends AppCompatActivity implements View.OnClickListener {
    boolean email;
    int dig5;
    Button envcodigo, validar;
    EditText codigo, correo;
    TextView txgenerado;
    Funciones adminFunciones = new Funciones(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_codigo);
        codigo = findViewById(R.id.edtcodigo);
        envcodigo = findViewById(R.id.btnenviarcodigo);
        validar = findViewById(R.id.btnvalidar);
        correo = findViewById(R.id.edtcorreoveri);
        txgenerado = findViewById(R.id.txvcode);

        envcodigo.setOnClickListener(this);
        validar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnenviarcodigo:
                if (!correo.getText().toString().isEmpty()){
                    email = adminFunciones.checkEmail(correo.getText().toString());
                    if (email == true){
                        Random rnd = new Random();
                        dig5 = rnd.nextInt(90000)+10000; //siempre 5 digitos
                        new AlertDialog.Builder(this)
                                .setTitle("ENVIADO")
                                .setMessage("El código a sido generado. Por favor revise SPAM si no lo encuentra en la bandeja principal")
                                .setPositiveButton(android.R.string.yes, null).show();
                        txgenerado.setText("El código es: "+ dig5);
                    }else{
                        new AlertDialog.Builder(this)
                                .setTitle("ERROR")
                                .setMessage("El correo esta mal escrito o no se encuentra en la base de datos")
                                .setPositiveButton(android.R.string.yes, null).show();
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Ingrese un correo para enviar un código de verificación")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
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