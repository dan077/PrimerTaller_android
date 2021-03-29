package com.example.primertaller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.EditText;

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

    public String IniciarSesion(String _correo, String _password){
        String usuarios = ObtenerUsuarios();

        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(usuarios).getAsJsonArray();

        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String correo = gsonObj.get("correo").getAsString();
            String password = gsonObj.get("pass").getAsString();

            if(correo.equalsIgnoreCase(_correo) && password.equals(_password)){
                return obj.toString();
            }

        }
        return  "";
    }

    public Boolean checkEmail(String _correo)
    {
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
    public void guardarPreferencias(Boolean seleccionado,String user, String pass, Boolean check){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

            edit.putString("user", user);
            edit.putString("password", pass);
            edit.putBoolean("recordar", check);

        edit.commit();
    }

    public void NoguardarPreferencias(){
        SharedPreferences preferences = pref.getSharedPreferences("Credenciales",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
            //Borra el contenido de las preferencias.
            edit.clear();
        edit.commit();
    }
    public   void cargarPreferencias(EditText user, EditText password, CheckBox check_recordar)
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
        if(getUsuarios == null) {
            edit.putString("users", "[{\"correo\":\"admin@gmail.com\",\"pass\":\"12345\",\"nombre\":\"daniel\",\"apellido\":\"vega\",\"sexo\":\"M\"},{\"correo\":\"admin123@gmail.com\",\"pass\":\"12345\",\"nombre\":\"andres\",\"apellido\":\"felizzola\",\"sexo\":\"F\"}]");
        }

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

}
