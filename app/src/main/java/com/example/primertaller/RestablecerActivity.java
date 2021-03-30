package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RestablecerActivity extends AppCompatActivity implements View.OnClickListener {
    String correo, aux1,aux2;
    EditText newcontraseña, repcontraseña;
    Button restablecer;
    Funciones adminFunciones = new Funciones(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);

        newcontraseña = findViewById(R.id.edtnewcontrasena);
        repcontraseña = findViewById(R.id.edtrepcontrasena);
        restablecer = findViewById(R.id.btnrestablecer);

        Intent i = getIntent();
        correo = i.getStringExtra("correo");
        restablecer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrestablecer:

                if (!newcontraseña.getText().toString().trim().isEmpty() && !repcontraseña.getText().toString().trim().isEmpty()){
                    aux1 = newcontraseña.getText().toString();
                    if(aux1.length() <= 6){
                        new AlertDialog.Builder(this)
                                .setTitle("ERROR")
                                .setMessage("Algo a salido mal, la contraseña debe tener más de 6 caracteres")
                                .setPositiveButton(android.R.string.yes, null).show();
                    }else{
                        if (aux1.equals(repcontraseña.getText().toString())){
                            adminFunciones.SetPassword(correo, aux1);
                            new AlertDialog.Builder(this)
                                    .setTitle("Corregida")
                                    .setMessage("Su contraseña ha sido restablecida. Será redireccionado al login")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(i);
                                        }
                                    }).show();

                        }else{
                            new AlertDialog.Builder(this)
                                    .setTitle("ERROR")
                                    .setMessage("Algo a salido mal, la contraseñas no coinciden")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Algo a salido mal, llene los campos")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
                break;
        }
    }
}