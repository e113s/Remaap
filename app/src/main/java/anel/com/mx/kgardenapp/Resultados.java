package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import anel.com.mx.kgardenapp.Nivel1;
import anel.com.mx.kgardenapp.Nivel2;
import anel.com.mx.kgardenapp.R;

public class Resultados extends AppCompatActivity {
    TextView aciertos, fallos, tiempoTotal,errorNum0,errorNum1,errorNum2,errorNum3,errorNum4,errorNum5,errorNum6,errorNum7,errorNum8,errorNum9;
    ImageButton ImageButtonSiguiente;
    SQLiteDatabase base;
    Cursor cursor;
    //conectarBD conecta;
    int totalAciertos,totalFallos,error0,error1,error2,error3,error4,error5,error6,error7,error8,error9;
    double tiempo;
    MediaPlayer mp;
    boolean gana;
    int nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        //conecta=new conectarBD(this);

        aciertos=(TextView)findViewById(R.id.textViewNumAciertos);
        fallos=(TextView)findViewById(R.id.textViewNumErrores);
        tiempoTotal=(TextView)findViewById(R.id.textViewTiempoTotal);
        errorNum0 = findViewById(R.id.textViewError02);
        errorNum1 = findViewById(R.id.textViewError12);
        errorNum2 = findViewById(R.id.textViewError22);
        errorNum3 = findViewById(R.id.textViewError32);
        errorNum4 = findViewById(R.id.textViewError42);
        errorNum5 = findViewById(R.id.textViewError52);
        errorNum6 = findViewById(R.id.textViewError62);
        errorNum7 = findViewById(R.id.textViewError72);
        errorNum8 = findViewById(R.id.textViewError82);
        errorNum9 = findViewById(R.id.textViewError92);
        ImageButtonSiguiente=(ImageButton)findViewById(R.id.imageButtonSiguiente);

        totalAciertos=getIntent().getExtras().getInt("totalAciertos");
        totalFallos=getIntent().getExtras().getInt("totalErrores");
        tiempo=getIntent().getExtras().getDouble("tiempo");
        gana=getIntent().getExtras().getBoolean("gana");
        nivel=getIntent().getExtras().getInt("nivel");
        error0 = getIntent().getExtras().getInt("error0");
        error1 = getIntent().getExtras().getInt("error1");
        error2 = getIntent().getExtras().getInt("error2");
        error3 = getIntent().getExtras().getInt("error3");
        error4 = getIntent().getExtras().getInt("error4");
        error5 = getIntent().getExtras().getInt("error5");
        error6 = getIntent().getExtras().getInt("error6");
        error7 = getIntent().getExtras().getInt("error7");
        error8 = getIntent().getExtras().getInt("error8");
        error9 = getIntent().getExtras().getInt("error9");

        if(gana==false){
            ImageButtonSiguiente.setImageResource(R.drawable.robin_sad);
        }
        System.out.println("====================================================");
        System.out.println("gana: "+gana);

        if(gana==true){
            ImageButtonSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentSiguiente=new Intent(getApplicationContext(), Nivel2.class);
                    startActivity(intentSiguiente);
                    finish();
                }
            });
        }else{
            ImageButtonSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentSiguiente=new Intent(getApplicationContext(), Nivel1.class);
                    startActivity(intentSiguiente);
                    finish();
                }
            });
        }






        iniciaBase();
        //consultas();

    }




    public void iniciaBase(){

        if(gana==true){
            mp = MediaPlayer.create(this,R.raw.resultado);
        }else{
            mp=MediaPlayer.create(this,R.raw.perdisteresultados);
        }
        mp.start();

        try{
            //base=conecta.getWritableDatabase();
            Toast.makeText(this, "Base abierta correctamente", Toast.LENGTH_SHORT).show();

        }catch(Exception v){
            Toast.makeText(this, "Error: "+v, Toast.LENGTH_SHORT).show();
            System.out.println("====================================="+v);
        }

        aciertos.setText(totalAciertos+"");
        fallos.setText(totalFallos+"");
        tiempoTotal.setText(tiempo+" segs.");
        errorNum0.setText(error0 + " errores");
        errorNum1.setText(error1 + " errores");
        errorNum2.setText(error2 + " errores");
        errorNum3.setText(error3 + " errores");
        errorNum4.setText(error4 + " errores");
        errorNum5.setText(error5 + " errores");
        errorNum6.setText(error6 + " errores");
        errorNum7.setText(error7 + " errores");
        errorNum8.setText(error8 + " errores");
        errorNum9.setText(error9 + " errores");

    }

    public void consultas(){
        try{
            cursor=base.rawQuery("select * from eje", null);
            Toast.makeText(this, "total registros: "+cursor.getCount(), Toast.LENGTH_SHORT).show();
        }catch(Exception w){
            Toast.makeText(this, "Error en consulta: "+w, Toast.LENGTH_SHORT).show();
            System.out.println("__________________________________________"+w);
        }

    }
}
