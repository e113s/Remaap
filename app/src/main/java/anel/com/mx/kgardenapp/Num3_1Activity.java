package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.impl.DrawingActivity;
import anel.com.mx.kgardenapp.impl.Game;
import anel.com.mx.kgardenapp.impl.GameAtencion;
import anel.com.mx.kgardenapp.impl.GameComplemento;
import anel.com.mx.kgardenapp.impl.IGame;

/**
 * juego de las tarjetas
 */
public class Num3_1Activity extends NumParentActivity implements View.OnClickListener {


    private String sexo;
    private String nombre;
    private String edad;
    TextView textViewUsuario;
    private ImageView imageViewUsuario;
    private IGame gameAtencion;
    private int a,b,c;
    private Integer imageResult,imageElment1,imageElment2,imageElment3,button;
    private ImageView  imageBResult,imageBElement1,imageBElement2,imageBElement3, imageView;
    private int imagesV[] ={R.id.imageView1 ,R.id.imageView2,R.id.imageView3};
    private int resultado=0,elemento1=0,elemento2=0, elemento3=0, resultadoOperacion =0,imagenNumero;
    private CardView card1,card2,card3;
    public MediaPlayer mpInstruccion;
    private int imagesArray[] ={R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10,
    R.id.imageView11,R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19,R.id.imageView20,
            R.id.imageView21,R.id.imageView22,R.id.imageView23,R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27,R.id.imageView28,R.id.imageView29,R.id.imageView30};
    //variables tiempo
    private long tInicio, tFinal, tDuracion;

    HashMap<Integer,Integer> numPantallaMap = new HashMap<Integer, Integer>();

    private TextView textoLeyenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_3_1);
        textoLeyenda=findViewById(R.id.textViewText31);
        card1= findViewById(R.id.cardView1);
        card2= findViewById(R.id.cardView2);
        card3= findViewById(R.id.cardView3);

        super.getInformation();
        gameAtencion = new GameAtencion(super.getActividad());
        //genero los primeros numeros
        generaNumeros();
        // inicio de duración de la actividad
        // begin of timing about gamer
        tInicio = System.currentTimeMillis();

    }

    /**
     * captura los clicks
     * @param view
     */
    @Override
    public void onClick(View view) {

        int numeroSeleccionado=0;
        textoLeyenda.setText("");

        CardView iv = (CardView) view;

            if(numPantallaMap.get(iv.getId()) != null){

                numeroSeleccionado= numPantallaMap.get(iv.getId());

                Animation animation = new AlphaAnimation(1.0f,0.0f);
                animation.setDuration(500);
                
                switch (iv.getId()){

                    case R.id.cardView1:

                        card1.setVisibility(View.INVISIBLE);

                        break;

                    case R.id.cardView2:

                        card2.setVisibility(View.INVISIBLE);
                        break;


                    case R.id.cardView3:
                        card3.setVisibility(View.INVISIBLE);
                        break;
                }


                for(int i =0;i<30;i++) {
                    imageView =findViewById(imagesArray[i]);
                    imageView.setBackgroundResource(0);
                }
            }

                int evaluacion =gameAtencion.evalua(numeroSeleccionado, resultadoOperacion);

                if(evaluacion==1){
                    llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);
                    //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();

                    if(evaluaTest()) {
                        generaNumeros();
                    }


                }else if(evaluacion==2){
                    llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);
                    //Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                    if(evaluaTest()) {
                        generaNumeros();
                    }
                }else if(evaluacion==0){
                    //llamarPopup(getResources().getString(R.string.selecciona_mas),R.drawable.robin);
                    Toast.makeText(getApplicationContext(),"Selecciona más tarjetas ",Toast.LENGTH_SHORT).show();
                }


        }


    /**
     * gener los numeros aleatorios
     */
    public void generaNumeros() {
        int numeroIteracion=1;
       // int number=gameAtencion.getNumberaleatorio();

        resultadoOperacion = gameAtencion.getNumberaleatorio();


        if(resultadoOperacion ==-1){

            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta(),gameAtencion.getAciertosContinuos(),gameAtencion.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta());

        }else {


            int numeroTarjeta =gameAtencion.getNumeroAleatorioRango(2);


            elemento1=gameAtencion.getNumeroAleatorioSinCero(10);
            elemento3=gameAtencion.getNumeroAleatorioSinCero(10);
            elemento2=gameAtencion.getNumeroAleatorioSinCero(10);

            while(elemento1 ==resultadoOperacion ){
                elemento1=gameAtencion.getNumeroAleatorioSinCero(10);
            }
            while(elemento2 ==resultadoOperacion || elemento1==elemento2 ){
                elemento2=gameAtencion.getNumeroAleatorioSinCero(10);
            }
            while(elemento3 ==resultadoOperacion || elemento1==elemento2 || elemento2==elemento3 ){
                elemento3=gameAtencion.getNumeroAleatorioSinCero(10);
            }

            if(numeroTarjeta ==0){
                elemento1 =resultadoOperacion;
            }else if (numeroTarjeta ==1){
                elemento2 = resultadoOperacion;
            }else if(numeroTarjeta ==2){
                elemento3= resultadoOperacion;
            }

            //playAudio( R.raw.tocatarjetacon, 3500);

            //pinta la leyende toca la coleccion  con
            String cadena = getResources().getString(R.string.toca_coleccion);
            cadena = cadena.concat(" ").concat(String.valueOf(resultadoOperacion)).concat(" ").concat(getResources().getString(R.string.elementos));

            textoLeyenda.setText(cadena);
            // agrega auido
            //playAudioNumbers(resultadoOperacion);
           //super.playAudio( R.raw.elementos, 1500);





            Thread thread = new Thread() {

                public void run() {
                    try {
                        mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.tocatarjetacon);
                        mpInstruccion.start();
                        sleep(2500);
                        mpInstruccion.release();

                        if (resultadoOperacion == 0) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cero);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 1) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.uno);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 2) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.dos);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 3) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.tres);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 4) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cuatro);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 5) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cinco);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 6) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.seis);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 7) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.siete);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 8) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.ocho);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 9) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.nueve);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (resultadoOperacion == 10) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.diez);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        }



                        if (resultadoOperacion == 1) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.elemento);
                            mpInstruccion.start();
                            sleep(1400);
                            mpInstruccion.release();

                        } else  {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.elementos);
                            mpInstruccion.start();
                            sleep(1400);
                            mpInstruccion.release();

                        }


                        // finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();



            //llena las tarjetas
            card1.setVisibility(View.VISIBLE);
            card1.setOnClickListener(this);
            numPantallaMap.put(card1.getId(), elemento1);
            imagenNumero =gameAtencion.getImageNumber(0);

            int contador =0;
            for(int i=0 ; i<10;i++){

                if(contador== elemento1){
                    break;
                }else {
                    imageView =findViewById(imagesArray[i]);
                    //asigna la imagen al boton
                    imageView.setBackgroundResource(imagenNumero);
                    //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
                    //imageView.setOnClickListener(this);
                    imageView.setVisibility(View.VISIBLE);

                    //numPantallaMap.put(imageView.getId(), elemento1);
                    contador++;
                }

            }//fin primer for

            contador=0;


            card2.setVisibility(View.VISIBLE);
            card2.setOnClickListener(this);
            numPantallaMap.put(card2.getId(), elemento2);
            imagenNumero =gameAtencion.getImageNumber(0);

                for(int i=10 ; i<21;i++){

                if(contador== elemento2){
                    break;
                }else {
                    imageView =findViewById(imagesArray[i]);
                    //imagenNumero =gameAtencion.getImageNumber(0);
                    //asigna la imagen al boton
                    imageView.setBackgroundResource(imagenNumero);
                    //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
                    //imageView.setOnClickListener(this);
                    imageView.setVisibility(View.VISIBLE);
                    contador++;


                }


            }//fin tercer while


            contador=0;
            card3.setVisibility(View.VISIBLE);
            card3.setOnClickListener(this);
            numPantallaMap.put(card3.getId(), elemento3);
            imagenNumero =gameAtencion.getImageNumber(0);
            for(int i=20 ; i<30;i++){

                if(contador== elemento3){
                    break;
                }else {
                    imageView =findViewById(imagesArray[i]);
                    //imagenNumero =gameAtencion.getImageNumber(0);
                    //asigna la imagen al boton
                    imageView.setBackgroundResource(imagenNumero);
                    //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
                    //imageView.setOnClickListener(this);
                    imageView.setVisibility(View.VISIBLE);
                    contador++;

                }

            }//fin segundo while



        }//fin else

    }

    public boolean evaluaTest(){
        boolean retorno= true;

        int result =gameAtencion.isTestPass();
        if(result ==0){
//            llamaResultado();
            // llamaOtroNivel(Num3_5Activity.class);

            /*
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta(),gameAtencion.getAciertosContinuos(),gameAtencion.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            Intent intent = new Intent(getApplicationContext(), VideoNumeros.class);

            startActivity(intent);
*/

            retorno= true;

            // super.llamaOtroNivel(super.getActividad(),this.getClass(),Num3_2Activity.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta(), new UsuarioFB());
        }else if(result==1){
            //llamaVideo();
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta(),gameAtencion.getAciertosContinuos(),gameAtencion.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            super.llamaOtroNivel(super.getActividad(),this.getClass(),VideoNumeros.class,gameAtencion.getNumeros(),gameAtencion.getAciertosTotales(),gameAtencion.getFallosTotales(),gameAtencion.getNumEcxluidosList(),gameAtencion.getNumeroPregunta(), new UsuarioFB());


            retorno= false;

        }
        return retorno;
    }


    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }

}
