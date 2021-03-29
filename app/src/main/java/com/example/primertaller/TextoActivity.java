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
                if (negrita.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }else{
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }

                if (italica.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                }else{
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    if (negrita.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                    }
                }

                if (subrayada.isChecked()){
                    texto.setPaintFlags(texto.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    if (negrita.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    }
                    if (italica.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    }
                    if (italica.isChecked() && negrita.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                    }
                }else{
                    texto.setPaintFlags(texto.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
                    if (negrita.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    }
                    if (italica.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    }
                    if (italica.isChecked() && negrita.isChecked()){
                        texto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                    }
                }
                break;
        }
    }
}