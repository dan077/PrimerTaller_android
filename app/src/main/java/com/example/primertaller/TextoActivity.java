package com.example.primertaller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class TextoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText texto;
    CheckBox negrita,italica,subrayada;
    Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

        texto = findViewById(R.id.edtmtextouser);
        negrita = findViewById(R.id.cbnegrita);
        italica = findViewById(R.id.cbitalica);
        subrayada = findViewById(R.id.cbsubrayada);
        validar = findViewById(R.id.btnvalidarcb);
        texto.setMovementMethod(new ScrollingMovementMethod());

        validar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnvalidarcb:

                //negrita & italic
                if(negrita.isChecked() && italica.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                }
                else if (negrita.isChecked() && !italica.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
                else if(!negrita.isChecked() && italica.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
                else{
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }

                //subrayado

                if (subrayada.isChecked()){
                    texto.setPaintFlags(texto.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                }else{
                    texto.setPaintFlags(texto.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
                }

                break;
        }
    }
}