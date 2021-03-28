package com.example.primertaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner sexo;
    Button regresar,verPassword,registrar;
    EditText password,correo,nombre,apellido;
    boolean controlVerPassword = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        sexo = findViewById(R.id.register_spinner_sexo);
        regresar = findViewById(R.id.btn_registro_regresar);
        registrar = findViewById(R.id.btn_registro_registrar);
        verPassword = findViewById(R.id.btn_registro_verPass);
        password = findViewById(R.id.register_password);
        correo = findViewById(R.id.register_correo);
        nombre = findViewById(R.id.register_nombre);
        apellido = findViewById(R.id.register_apellido);
        verPassword.setOnClickListener(this);
        regresar.setOnClickListener(this);
        registrar.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_sexo, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sexo.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registro_registrar:
                if(password.getText().toString().isEmpty()|| password.toString().length() <= 6 || correo.getText().toString().isEmpty()|| nombre.getText().toString().isEmpty() ||
                    apellido.getText().toString().isEmpty())
                {
                    if(password.getText().toString().length() <= 6) {
                        Toast.makeText(this, "La contraseña debe contener más de 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this, "Alguna(s) caja(s) de texto esta(n) vacia(s)", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(this,"Procedimiento para el registro" ,Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_registro_regresar:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_registro_verPass:
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
        }
    }
}