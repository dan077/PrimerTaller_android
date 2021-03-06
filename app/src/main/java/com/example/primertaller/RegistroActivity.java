package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner sexo;
    Button regresar,verPassword,registrar;
    EditText password,correo,nombre,apellido;
    boolean controlVerPassword = true;
    Funciones adminFunciones = new Funciones(this);
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

        switch (v.getId())
        {

            case R.id.btn_registro_registrar:
                String _password = password.getText().toString();
                String _correo = correo.getText().toString();
                String _nombre = nombre.getText().toString();
                String _apellido = apellido.getText().toString();
                String _sexo = sexo.getSelectedItem().toString();
                boolean checkCorreo = adminFunciones.checkEmail(_correo);
                boolean validEmail = !adminFunciones.isValidEmail(_correo);

                if(_password.isEmpty()|| _password.length() <= 6 || validEmail || _nombre.isEmpty() ||
                    _apellido.isEmpty() || checkCorreo)
                {
                    // *** validaciones. ***
                    // no valido el sexo por que siempre va a seleccionar alguno de los dos.

                    if(_nombre.isEmpty()){
                        nombre.setError("Caja vacia");
                    }

                    if(_apellido.isEmpty()){
                        apellido.setError("Caja vacia");
                    }

                    //Validaciones password
                    if (_password.isEmpty()){
                        password.setError("Caja vacia");
                    }
                    else if(_password.length() <= 6) {
                        password.setError("La contrase??a debe contener m??s de 6 caracteres");
                    }

                    //validaciones correo.
                    if(_correo.isEmpty()){
                       correo.setError("Caja vacia");
                    }
                    else if(checkCorreo){
                        correo.setError("ya existe un usuario con ese correo");
                    }
                    else if(validEmail){
                        correo.setError("Email no valido.");
                    }

                }
                else {

                    String nuevoUsuario = String.format("{\"correo\":\"%s\",\"pass\":\"%s\",\"nombre\":\"%s\",\"apellido\":\"%s\",\"sexo\":\"%s\"}",_correo,_password,_nombre,_apellido,_sexo);

                    JsonParser parser = new JsonParser();
                    JsonObject user = (JsonObject) parser.parse(nuevoUsuario);
                    JsonArray gsonArr = parser.parse(adminFunciones.ObtenerUsuarios()).getAsJsonArray();
                    gsonArr.add(user);

                    adminFunciones.CreateUsuarios(gsonArr.toString());
                    Toast.makeText(this,"Usuario Registrado",Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(this)
                            .setTitle("Registrado con exito")
                            .setMessage("ser?? redirigido a la pantalla de login.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();

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
    @Override public void onBackPressed() { return; }

}