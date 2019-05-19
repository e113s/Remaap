package anel.com.mx.kgardenapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotifiactionPopUpActivity extends Activity {

    public  String nombre,sexo,edad,aciertosTotales,fallosTotales,numeroPregunta;
    private TextView textViewUsuario;
    private  TextView textViewEdad,textViewAciertos,textViewFallos,textViewPreugntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiaction_pop_up);

        nombre =getIntent().getExtras().getString("nombre");
        sexo =getIntent().getExtras().getString("sexo");
        edad =getIntent().getExtras().getString("edad");


        aciertosTotales =getIntent().getExtras().getString("aciertosTotales");
        fallosTotales =getIntent().getExtras().getString("fallosTotales");
        numeroPregunta =getIntent().getExtras().getString("numeroPregunta");

        textViewAciertos=  findViewById(R.id.textViewAciertoR);
        textViewFallos= findViewById(R.id.textViewFallosR);
        textViewPreugntas = findViewById(R.id.textViewPreguntasR);


        textViewAciertos.setText(aciertosTotales);
        textViewFallos.setText(fallosTotales);
        textViewPreugntas.setText(numeroPregunta);




    }
}
