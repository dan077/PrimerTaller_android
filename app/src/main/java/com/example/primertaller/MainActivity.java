package com.example.primertaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button verPassword,ingresar,registrar;
    EditText user,password;
    TextView txtError;
    TextView olvidar;
    Boolean controlVerPassword = true;
    CheckBox check_recordar, check_terminos;
    ArrayList<String> usuarios;
    Funciones adminFunciones = new Funciones(this); //Crear un objeto de función

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verPassword = findViewById(R.id.btn_verPassword);
        ingresar = findViewById(R.id.btn_ingresar);
        registrar = findViewById(R.id.btn_registrar_form);
        user = findViewById(R.id.edit_user);
        password = findViewById(R.id.edit_password);
        check_recordar = findViewById(R.id.check_recordar);
        check_terminos = findViewById(R.id.check_terminos);
        txtError = findViewById(R.id.txt_login_error);
        olvidar = findViewById(R.id.txvolvidar);
        check_terminos.setOnClickListener(this);
        verPassword.setOnClickListener(this);
        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        ingresar.setEnabled(false);
        txtError.setText("");
        olvidar.setOnClickListener(this);

        adminFunciones.cargarPreferencias(user,password,check_recordar);
        adminFunciones.CreateUsuarios();
        adminFunciones.cargarPreferencias(user,password,check_recordar);

        if(adminFunciones.isActiveSesion()){
            adminFunciones.IniciarSesion(user.getText().toString() , password.getText().toString() , check_recordar.isChecked());
        }



    }

    @Override
    public void onClick(View v) {
        String _user = user.getText().toString();
        String _pass = password.getText().toString();
        switch (v.getId()){
            case R.id.btn_registrar_form:
                adminFunciones.guardarPreferencias(_user,_pass,check_recordar.isChecked());
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
            case R.id.check_terminos:
                if(check_terminos.isChecked()){
                    ingresar.setEnabled(true);
                }
                else{
                    ingresar.setEnabled(false);
                }
                break;
            case R.id.btn_verPassword:

                if(controlVerPassword) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    verPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password_hide, 0, 0, 0);
                    verPassword.setBackgroundColor(Color.rgb(104,236,142));

                }
                else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    verPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.password_show, 0);
                    verPassword.setBackgroundColor(Color.RED);

                }

                //Posiciona el puntero en el ultimo caracter
                password.setSelection(password.getText().length());

                //Se cambia el estado de ver/no ver
                controlVerPassword = !controlVerPassword;
                break;
            case R.id.btn_ingresar:


                if(!adminFunciones.isValidEmail(_user)) //en la funcion se verifica que no esté vacio.
                {
                    user.setError("Email no valido");
                }
                else if(_pass.isEmpty()){
                    password.setError("Campo vacio");
                }
                else{
                        boolean valores = adminFunciones.IniciarSesion(_user,_pass,check_recordar.isChecked());

                        if(!valores){
                            txtError.setText("Datos Invalidos");
                        }
                }
                break;
            case R.id.txvolvidar:
                Intent i  = new Intent(getApplicationContext(),ValidarCodigoActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override public void onBackPressed() { return; }


}