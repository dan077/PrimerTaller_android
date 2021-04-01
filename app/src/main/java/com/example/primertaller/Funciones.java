package com.example.primertaller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static android.content.Context.MODE_PRIVATE;

public class Funciones
{
    Context pref;
    public Funciones(Context _pref){
        pref = _pref;
    }

    //Obtener info de usuarios.

    public String obtenerUsuario(){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
       String correo =  preferences.getString("user","");
       return  SearchUser(correo);

    }

    public final static boolean isValidEmail(String target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



    public  void CreateUsuarios(String usuarios)
    {
        SharedPreferences preferences =   pref.getSharedPreferences("usuarios", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        String getUsuarios= preferences.getString("users", null);
        //Crea los usuarios predeterminados si no existen.
        if(getUsuarios != null) {
            edit.putString("users", usuarios);
        }
        edit.commit();
    }

    public  String ObtenerUsuarios(){
        SharedPreferences preferences = pref.getSharedPreferences("usuarios", MODE_PRIVATE);
        return preferences.getString("users","");
    }

    public boolean IniciarSesion(String _correo, String _password, boolean check){
        String usuarios = ObtenerUsuarios();

        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(usuarios).getAsJsonArray();

        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String correo = gsonObj.get("correo").getAsString();
            String password = gsonObj.get("pass").getAsString();

            if(correo.equalsIgnoreCase(_correo) && password.equals(_password))
            {
                guardarPreferencias(_correo,password,check);
                ActiveSesion();
                Intent i = new Intent(pref,HomeActivity.class);
                i.putExtra("obj",obj.toString());
                pref.startActivity(i);
                return true;
            }

        }
        return  false;
    }

    public String SearchUser(String _correo){
        if(_correo.isEmpty()){
            return  "";
        }

        String usuarios = ObtenerUsuarios();

        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(usuarios).getAsJsonArray();

        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String correo = gsonObj.get("correo").getAsString();
            if(correo.equalsIgnoreCase(_correo)){
                return gsonObj.toString();
            }

        }
        return "";
    }

    public Boolean checkEmail(String _correo)
    {
        if(_correo.isEmpty()){
            return  false;
        }
        String usuarios = ObtenerUsuarios();

        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(usuarios).getAsJsonArray();

        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String correo = gsonObj.get("correo").getAsString();
            if(correo.equalsIgnoreCase(_correo)){
                return true;
            }

        }
        return false;
    }

    public  boolean isActiveSesion(){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        boolean status = preferences.getBoolean("sesion",false);
        return  status;
    }

    public void ActiveSesion(){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("sesion", true);
        edit.commit();
    }

    public void guardarPreferencias(String user, String pass,boolean check){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        //Como va a iniciar sesion no puedo borrar estos datos.
        edit.putString("user", user);
        edit.putString("password", pass);
        edit.putBoolean("recordar", check);
        edit.commit();
    }

    public void CerrarSesion(){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

            //Borra el contenido de las preferencias si no se seleccionó previamente el check de recordar.
        if(!preferences.getBoolean("recordar",false)){
           edit.clear();
        }
        edit.putBoolean("sesion", false);
        edit.commit();

        Intent i = new Intent(pref,MainActivity.class);
        pref.startActivity(i);
    }

    public void cargarPreferencias(EditText user, EditText password, CheckBox check_recordar)
    {
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        user.setText(preferences.getString("user",""));
        password.setText(preferences.getString("password",""));
        check_recordar.setChecked(preferences.getBoolean("recordar",false));
    }

    public   void CreateUsuarios()
    {
        SharedPreferences preferences = pref.getSharedPreferences("usuarios", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        String getUsuarios= preferences.getString("users", null);

        //Crea los usuarios predeterminados si no existen.
        if(getUsuarios == null)
            edit.putString("users", "[{\"correo\":\"admin@gmail.com\",\"pass\":\"1234567\",\"nombre\":\"daniel\",\"apellido\":\"vega\",\"sexo\":\"M\"},{\"correo\":\"admin123@gmail.com\",\"pass\":\"1234567\",\"nombre\":\"andres\",\"apellido\":\"felizzola\",\"sexo\":\"F\"},{\"correo\":\"sara@gmail.com\",\"pass\":\"1234567\",\"nombre\":\"andres\",\"apellido\":\"felizzola\",\"sexo\":\"F\"}]");


        edit.commit();
    }


    public boolean SetPassword(String _correo, String contraseña){
        int cont = 0;
        String usuarios = ObtenerUsuarios();

        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(usuarios).getAsJsonArray();

        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String correo = gsonObj.get("correo").getAsString();
            if(correo.equalsIgnoreCase(_correo)){
                String _apellido = gsonObj.get("apellido").getAsString();
                String _sexo = gsonObj.get("sexo").getAsString();
                String _nombre = gsonObj.get("nombre").getAsString();
                String nuevoUsuario = String.format("{\"correo\":\"%s\",\"pass\":\"%s\",\"nombre\":\"%s\",\"apellido\":\"%s\",\"sexo\":\"%s\"}",_correo,contraseña,_nombre,_apellido,_sexo);
                JsonObject user = (JsonObject) parser.parse(nuevoUsuario);
                gsonArr.set(cont, user);
                CreateUsuarios(gsonArr.toString());
                return true;
            }
            cont ++;
        }
        return false;
    }

    public void menuHomeListener(ImageButton ayuda, ImageButton home, ImageButton cerrarSesion){
        ayuda.setOnClickListener((View.OnClickListener) pref);
        home.setOnClickListener((View.OnClickListener) pref);
        cerrarSesion.setOnClickListener((View.OnClickListener) pref);
    }

    public void menuHomeAccion(ImageButton ayuda, ImageButton home ,ImageButton cerrarSesion, View accion){
        if(ayuda.getId() == accion.getId()){
            new AlertDialog.Builder(pref)
                    .setTitle("Ayuda")
                    .setMessage("APP v1, realizada por DANIEL VEGA, LUIS PUELLO, ANDRES CASTRO")
                    .setPositiveButton(android.R.string.yes, null).show();
        }

        if(home.getId() == accion.getId()) {
            Intent i = new Intent(pref,HomeActivity.class);
            pref.startActivity(i);
        }
        if (cerrarSesion.getId() == accion.getId()){
            CerrarSesion();
        }

    }
}
