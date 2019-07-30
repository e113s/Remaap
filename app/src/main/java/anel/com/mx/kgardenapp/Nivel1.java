package anel.com.mx.kgardenapp;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Stack;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.impl.DrawingActivity;

public class Nivel1 extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
    ImageView img1, img2, img3, img4, destinoIz, destinoDe;
    //TextView instrucciones,
    TextView txtvContador;
    String direc;
    int num, num2;
    int fallosTotales=0;
    int aciertosTotales=0;
    int band = 0;
    Stack<Integer> listaNumeros = new Stack<Integer>();
    boolean pos1, pos2, pos3, pos4;
    boolean pos1temp, pos2temp, pos3temp, pos4temp;
    int contadorPierde = 3;
    int contadorGana = 0;
    long tiempoInicio, tiempoFinal, tiempoDiferencia;
    MediaPlayer mp, mp_backup;
    ImageButton bocina;
    boolean gana;
    int nivel=1;
    int error0,error1,error2,error3,error4,error5,error6,error7,error8,error9;

    int listaImagenes[] = {
            R.drawable.percepcion_cero,
            R.drawable.percepcion_uno,
            R.drawable.percepcion_dos,
            R.drawable.percepcion_tres,
            R.drawable.percepcion_cuatro,
            R.drawable.percepcion_cinco,
            R.drawable.percepcion_seis,
            R.drawable.percepcion_siete,
            R.drawable.percepcion_ocho,
            R.drawable.percepcion_nueve};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);
        tiempoInicio=System.currentTimeMillis();

        asociar();
        instruccion();

        destinoIz.setOnDragListener(this);
        destinoDe.setOnDragListener(this);
    }

    public void asociar() {
        //instrucciones = (TextView) findViewById(R.id.textViewInstrucciones);
        txtvContador = findViewById(R.id.textViewContador);
        img1 = (ImageView) findViewById(R.id.imageViewImg1);
        img2 = (ImageView) findViewById(R.id.imageViewImg2);
        img3 = (ImageView) findViewById(R.id.imageViewImg3);
        img4 = (ImageView) findViewById(R.id.imageViewImg4);
        bocina=(ImageButton)findViewById(R.id.imageButtonBocina);

        destinoIz = findViewById(R.id.imageViewDestinoIz);
        destinoDe = findViewById(R.id.imageViewDestinoDe);


        bocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();
            }
        });


    }

    public void instruccion() {

        for (int g = 0; g <= 3; g++) {
            num = (int) (Math.random() * 9);
            if (listaNumeros.contains(num) == false)
                listaNumeros.push(num);
            else
                g--;
        }


        do {
            num2 = (int) (Math.random() * 9);
            if (listaNumeros.contains(num2))
                break;
        } while (true);

        
        double intdir = ((Math.random() * 2));
        //System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ>> " + intdir);
        if (intdir < 1) {
            direc = "izquierda";
        } else
            direc = "derecha";

        //instrucciones.setText("Arrastre a la " + direc + " el numero " + num2);

        if (direc.equals("izquierda")) {
            if (num2 == 0) {
                mp = MediaPlayer.create(this, R.raw.ceroi);
            } else if (num2 == 1) {
                mp = MediaPlayer.create(this, R.raw.unoi);
            } else if (num2 == 2) {
                mp = MediaPlayer.create(this, R.raw.dosi);
            } else if (num2 == 3) {
                mp = MediaPlayer.create(this, R.raw.tresi);
            } else if (num2 == 4) {
                mp = MediaPlayer.create(this, R.raw.cuatroi);
            } else if (num2 == 5) {
                mp = MediaPlayer.create(this, R.raw.cincoi);
            } else if (num2 == 6) {
                mp = MediaPlayer.create(this, R.raw.seisi);
            } else if (num2 == 7) {
                mp = MediaPlayer.create(this, R.raw.sietei);
            } else if (num2 == 8) {
                mp = MediaPlayer.create(this, R.raw.ochoi);
            } else if (num2 == 9) {
                mp = MediaPlayer.create(this, R.raw.nuevei);
            }
            mp_backup=mp;
            mp.start();
        }

        if (direc.equals("derecha")) {
            if (num2 == 0) {
                mp = MediaPlayer.create(this, R.raw.cerod);
            } else if (num2 == 1) {
                mp = MediaPlayer.create(this, R.raw.unod);
            } else if (num2 == 2) {
                mp = MediaPlayer.create(this, R.raw.dosd);
            } else if (num2 == 3) {
                mp = MediaPlayer.create(this, R.raw.tresd);
            } else if (num2 == 4) {
                mp = MediaPlayer.create(this, R.raw.cuatrod);
            } else if (num2 == 5) {
                mp = MediaPlayer.create(this, R.raw.cincod);
            } else if (num2 == 6) {
                mp = MediaPlayer.create(this, R.raw.seisd);
            } else if (num2 == 7) {
                mp = MediaPlayer.create(this, R.raw.sieted);
            } else if (num2 == 8) {
                mp = MediaPlayer.create(this, R.raw.ochod);
            } else if (num2 == 9) {
                mp = MediaPlayer.create(this, R.raw.nueved);
            }
            mp_backup=mp;
            mp.start();
        }

        txtvContador.setText("Te quedan: " + contadorPierde + " intentos");
        llenarImagenes();
    }

    public void llenarImagenes() {
        int pos = listaNumeros.search(num2);
        //System.out.println("posicionnnn>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + pos);

        img1.setImageResource(listaImagenes[listaNumeros.pop()]);
        img2.setImageResource(listaImagenes[listaNumeros.pop()]);
        img3.setImageResource(listaImagenes[listaNumeros.pop()]);
        img4.setImageResource(listaImagenes[listaNumeros.pop()]);


        if (pos == 4) {
            pos4 = true;
            pos1 = false;
            pos2 = false;
            pos3 = false;
        }

        if (pos == 3) {
            pos3 = true;
            pos1 = false;
            pos2 = false;
            pos4 = false;
        }

        if (pos == 2) {
            pos2 = true;
            pos1 = false;
            pos4 = false;
            pos3 = false;
        }

        if (pos == 1) {
            pos1 = true;
            pos4 = false;
            pos2 = false;
            pos3 = false;
        }

        arrastrar();
    }

    public void arrastrar() {
        img1.setOnTouchListener(this);
        img2.setOnTouchListener(this);
        img3.setOnTouchListener(this);
        img4.setOnTouchListener(this);
    }

    private void GoResultados(){
        tiempoFinal=System.currentTimeMillis();
        tiempoDiferencia=tiempoFinal-tiempoInicio;
        double segundosTranscurridos=tiempoDiferencia/1000.0;

        Intent res = new Intent(this, Resultados.class);
        res.putExtra("totalAciertos",aciertosTotales);
        res.putExtra("totalErrores",fallosTotales);
        res.putExtra("tiempo",segundosTranscurridos);
        res.putExtra("gana",gana);
        res.putExtra("nivel",nivel);
        res.putExtra("error0",error0);
        res.putExtra("error1",error1);
        res.putExtra("error2",error2);
        res.putExtra("error3",error3);
        res.putExtra("error4",error4);
        res.putExtra("error5",error5);
        res.putExtra("error6",error6);
        res.putExtra("error7",error7);
        res.putExtra("error8",error8);
        res.putExtra("error9",error9);
        startActivity(res);
        finish();

    }



    public void checarSiGana(){
        mp = MediaPlayer.create(this,R.raw.correcto);
        mp.start();
        aciertosTotales++;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(contadorGana >= 2){
            gana=true;
            GoResultados();
        }
        else{
            contadorPierde = 3;
            contadorGana++;
            instruccion();
        }
    }

    public void checarErrorNumero(){
        System.out.println(num2);
        if(num2 == 0){
            error0++;
        }
        if(num2 == 1){
            error1++;
        }
        if(num2 == 2){
            error2++;
        }
        if(num2 == 3){
            error3++;
        }
        if(num2 == 4){
            error4++;
        }
        if(num2 == 5){
            error5++;
        }
        if(num2 == 6){
            error6++;
        }
        if(num2 == 7){
            error7++;
        }
        if(num2 == 8){
            error8++;
        }
        if(num2 == 9){
            error9++;
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {

            //System.out.println("destinooooooooooooooooo " + String.valueOf(v.getTag()));

            if (direc.equals("izquierda")) {
                if (pos1 == true && pos1temp == true && v.getTag().equals("destinoIzquierda")) {

                    checarSiGana();
                } else if (pos2 == true && pos2temp == true && v.getTag().equals("destinoIzquierda")) {

                    checarSiGana();
                } else if (pos3 == true && pos3temp == true && v.getTag().equals("destinoIzquierda")) {

                    checarSiGana();
                } else if (pos4 == true && pos4temp == true && v.getTag().equals("destinoIzquierda")) {

                    checarSiGana();
                } else {
                    checarErrorNumero();
                    fallosTotales++;
                    if (contadorPierde == 1) {
                        //contador = 3;
                        gana=false;
                        mp = MediaPlayer.create(this, R.raw.perdiste);
                        mp.start();

                        GoResultados();

                    } else {
                        contadorPierde--;
                        mp = MediaPlayer.create(this, R.raw.perdistetono);
                        mp.start();

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mp = MediaPlayer.create(this, R.raw.intentalo);
                        mp.start();
                        instruccion();
                        txtvContador.setText("Te quedan: " + contadorPierde + " intentos");
                    }
                }
            } else if (direc.equals("derecha")) {
                if (pos1 == true && pos1temp == true && v.getTag().equals("destinoDerecha")) {

                    checarSiGana();
                } else if (pos2 == true && pos2temp == true && v.getTag().equals("destinoDerecha")) {

                    checarSiGana();
                } else if (pos3 == true && pos3temp == true && v.getTag().equals("destinoDerecha")) {

                    checarSiGana();
                } else if (pos4 == true && pos4temp == true && v.getTag().equals("destinoDerecha")) {

                    checarSiGana();
                } else {
                    checarErrorNumero();
                    fallosTotales++;
                    if (contadorPierde == 1) {
                        //contador = 3;
                        gana=false;
                        mp = MediaPlayer.create(this, R.raw.perdiste);
                        mp.start();

                        GoResultados();

                    } else {
                        contadorPierde--;

                        mp = MediaPlayer.create(this, R.raw.perdistetono);
                        mp.start();

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mp = MediaPlayer.create(this, R.raw.intentalo);
                        mp.start();
                        instruccion();
                        txtvContador.setText("Te quedan: " + contadorPierde + " intentos");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder sombra = new View.DragShadowBuilder(v);
        v.startDrag(data, sombra, v, 0);
        //System.out.println("presionado ======================" + String.valueOf(v.getTag()));

        if (String.valueOf(v.getTag()).contains("img1")) {
            pos1temp = true;
            pos2temp = false;
            pos3temp = false;
            pos4temp = false;
        } else if (String.valueOf(v.getTag()).contains("img2")) {
            pos1temp = false;
            pos2temp = true;
            pos3temp = false;
            pos4temp = false;
        } else if (String.valueOf(v.getTag()).contains("img3")) {
            pos1temp = false;
            pos2temp = false;
            pos3temp = true;
            pos4temp = false;
        } else if (String.valueOf(v.getTag()).contains("img4")) {
            pos1temp = false;
            pos2temp = false;
            pos3temp = false;
            pos4temp = true;
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);
        startActivity(intent);
        finish();
    }
}
