package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class VoltajeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Button hallar_voltaje;
    Spinner spinner;
    String text;
    EditText resistencia3, resistencia2, resistencia1, corriente;
    CheckBox check_paralelo;
    AlertDialog.Builder builder;
    int res,res1,res2,res3,v1,v2,v3;
    float rest, corriente1,vt;
    ImageButton ayuda, home, exit;
    Funciones adminFunciones = new Funciones(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltaje);
        hallar_voltaje = findViewById(R.id.btnhallarvoltaje);
        hallar_voltaje.setOnClickListener(this);
        resistencia3 = findViewById(R.id.edtresist3);
        resistencia2 = findViewById(R.id.edtresist2);
        resistencia1 = findViewById(R.id.edtresist1);
        corriente = findViewById(R.id.edtcorriente);
        check_paralelo = findViewById(R.id.chb_paralelo);
        ayuda = (ImageButton) findViewById(R.id.imgbtnayuda);
        home = (ImageButton) findViewById(R.id.imgbtnhome);
        exit = (ImageButton) findViewById(R.id.imgbtnexit);


        spinner =findViewById(R.id.spresistencia);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        adminFunciones.menuHomeListener(ayuda,home,exit);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        if ("2".equals(text)){
            resistencia3.setText("");
            resistencia3.setEnabled(false);
            res = 2;
            //Toast.makeText(getApplicationContext(),""+res,Toast.LENGTH_LONG).show();
        }else{
            resistencia3.setEnabled(true);
            res = 3;
            //Toast.makeText(getApplicationContext(),""+res,Toast.LENGTH_LONG).show();
        }

        //
    }


    @Override
    public void onClick(View v) {
        adminFunciones.menuHomeAccion(ayuda,home,exit,v);

        switch (v.getId()){
            case R.id.btnhallarvoltaje:
                corriente1 = Float.valueOf(corriente.getText().toString());
                //Toast.makeText(getApplicationContext(),""+corriente1,Toast.LENGTH_LONG).show();
                if(check_paralelo.isChecked()){
                    if (res ==2){
                        res1 = Integer.valueOf(resistencia1.getText().toString());
                        res2 = Integer.valueOf(resistencia2.getText().toString());
                        rest = (res1*res2)/(res1+res2);
                        vt = rest * corriente1;

                        builder = new AlertDialog.Builder(this);
                        builder.setTitle("VOLTAJE");
                        builder.setMessage("El voltaje es: "+ vt);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();



                    }else{
                        res1 = Integer.valueOf(resistencia1.getText().toString());
                        res2 = Integer.valueOf(resistencia2.getText().toString());
                        res3 = Integer.valueOf(resistencia3.getText().toString());
                        rest = ((res1*res2*res3)/((res2*res3)+(res1*res3)+(res1*res2)));
                        vt = rest * corriente1;

                        builder = new AlertDialog.Builder(this);
                        builder.setTitle("VOLTAJE");
                        builder.setMessage("El voltaje es: "+ vt);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{
                    if (res == 2){
                        res1 = Integer.valueOf(resistencia1.getText().toString());
                        res2 = Integer.valueOf(resistencia2.getText().toString());
                        rest = res1+ res2;
                        vt = rest * corriente1;

                        builder = new AlertDialog.Builder(this);
                        builder.setTitle("VOLTAJE");
                        builder.setMessage("El voltaje es: "+ vt);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }else{
                        res1 = Integer.valueOf(resistencia1.getText().toString());
                        res2 = Integer.valueOf(resistencia2.getText().toString());
                        res3 = Integer.valueOf(resistencia3.getText().toString());
                        rest = res1+ res2 +res3;
                        vt = rest * corriente1;

                        builder = new AlertDialog.Builder(this);
                        builder.setTitle("VOLTAJE");
                        builder.setMessage("El voltaje es: "+ vt);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }


                }

                break;

        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}