package com.example.primertaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.Math.pow;

public class GeometriaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String opciones;
    float x1,y1,x2,y2, pendiente;
    double distancia;
    Spinner spinner;
    EditText coorx1, coory1, coorx2, coory2;
    Button calcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometria);

        coorx1 = findViewById(R.id.edtcoorx1);
        coory1 = findViewById(R.id.edtcoory1);
        coorx2 = findViewById(R.id.edtcoorx2);
        coory2 = findViewById(R.id.edtcoory2);
        calcular = findViewById(R.id.btnhallarfuerza);
        spinner = findViewById(R.id.spinnergeometria);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_geometria ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        calcular.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        opciones = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), opciones, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnhallarfuerza:
                if (!coorx1.getText().toString().isEmpty() && !coory1.getText().toString().isEmpty() &&
                    !coorx2.getText().toString().isEmpty() && !coory2.getText().toString().isEmpty()){
                    x1 = Float.valueOf(coorx1.getText().toString());
                    y1 = Float.valueOf(coory1.getText().toString());
                    x2 = Float.valueOf(coorx2.getText().toString());
                    y2 = Float.valueOf(coory2.getText().toString());

                    if ("ubicación en cuadrantes".equals(opciones)){
//-------------------------------PARA EL PRIMER PUNTO--------------------------------------------------------------------------------
                        if (x1 > 0 && y1 >0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el primer cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 < 0 && y1 > 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el segundo cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 < 0 && y1 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el tercer cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 > 0 && y1 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el cuarto cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 == 0 && y1 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el origen")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 > 0 && y1 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el eje x positivo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 < 0 && y1 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el eje x negativo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 == 0 && y1 > 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el eje y positivo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x1 == 0 && y1 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 1")
                                    .setMessage("El punto ("+x1+", "+y1+") está en el eje y negativo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }

//------------------------------------------PARA EL SEGUNDO PUNTO ----------------------------------------------------------------------
                        if (x2 > 0 && y2 >0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el primer cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 < 0 && y2 > 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el segundo cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 < 0 && y2 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el tercer cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 > 0 && y2 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+","+y2+") está en el cuarto cuadrante")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 == 0 && y2 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el origen")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 > 0 && y2 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el eje x positivo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 < 0 && y2 == 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el eje x negativo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 == 0 && y2 > 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el eje y positivo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                        if (x2 == 0 && y2 < 0){
                            new AlertDialog.Builder(this)
                                    .setTitle("RESULTADO PUNTO 2")
                                    .setMessage("El punto ("+x2+", "+y2+") está en el eje y negativo")
                                    .setPositiveButton(android.R.string.yes, null).show();
                        }
                    }
                    if ("Pendiente".equals(opciones)){
                        pendiente = (y2-y1)/(x2-x1);
                        new AlertDialog.Builder(this)
                                .setTitle("RESULTADO")
                                .setMessage("La pendiente entre los puntos: ("+x1+", "+y1+") y ("+x2+", "+y2+") es: "+ pendiente)
                                .setPositiveButton(android.R.string.yes, null).show();
                    }
                    if ("Distancia".equals(opciones)){
                        distancia = Math.sqrt((pow((x2-x1),2))+(pow((y2-y1),2)));
                        new AlertDialog.Builder(this)
                                .setTitle("RESULTADO")
                                .setMessage("La distancia entre los puntos: ("+x1+", "+y1+") y ("+x2+", "+y2+") es: "+ distancia)
                                .setPositiveButton(android.R.string.yes, null).show();

                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Faltan campos por llenar.")
                            .setPositiveButton(android.R.string.yes, null).show();
                }
                break;
        }
    }
}