package anel.com.mx.kgardenapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
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
import anel.com.mx.kgardenapp.impl.GameComplemento;
import anel.com.mx.kgardenapp.impl.IGame;

/**
 * Created by Anel Gutierrez on 19/07/2018.
 * Esta clase represetna la pantalla de agrupacion de numeros
 */
public class  Num3_7Activity extends NumParentActivity implements View.OnClickListener {

    private String sexo;
    private String nombre;
    private String edad;
     TextView textViewUsuario;
    private ImageView imageViewUsuario;
    private IGame gameComplemento;
    private int a,b,c;
    private Integer imageResult,imageElment1,imageElment2,imageElment3,button;
    private ImageView  imageBResult,imageBElement1,imageBElement2,imageBElement3;
    private int imagesV[] ={R.id.imageView0, R.id.imageView1 ,R.id.imageView2,R.id.imageView3};
    private int resultado=0,elemento1=0,elemento2=0, elemento3=0, resultadoOperacion =0;
    private CardView card0,card1,card2,card3;
     //variables tiempo
    private long tInicio, tFinal, tDuracion;

    HashMap<Integer,Integer> numPantallaMap = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3_7);


       //obtiene informacion de un a actividad anterior
        //getInformacion();
        super.getInformation();
        gameComplemento = new GameComplemento(super.getActividad());

        //llamaResultado();

        imageBResult = findViewById(R.id.imageView0);
        imageBElement1=findViewById(R.id.imageView1);
        imageBElement2 =findViewById(R.id.imageView2);
        imageBElement3 =findViewById(R.id.imageView3);
        card0= findViewById(R.id.cardView0);
        card1= findViewById(R.id.cardView1);
        card2= findViewById(R.id.cardView2);
        card3= findViewById(R.id.cardView3);



        //genero los primeros numeros
        generaNumeros();
        // inicio de duración de la actividad
        // begin of timing about gamer
        tInicio = System.currentTimeMillis();


    }


    /**
     * obtiene todos los clicks que damos sobre la pantalla
     * @param view
     */
    @Override
    public void onClick(View view) {

        //obiene el view y hace un cast a ImageView
        ImageView iv = (ImageView) view;

        if(numPantallaMap.get(iv.getId()) != null){

            int numeroSeleccionado= numPantallaMap.get(iv.getId());

            Animation animation = new AlphaAnimation(1.0f,0.0f);
            animation.setDuration(500);
            iv.startAnimation(animation);
            iv.setVisibility(View.INVISIBLE);

            switch (iv.getId()){

                case R.id.imageView1:
                    card1.setVisibility(View.INVISIBLE);
                    break;

                case R.id.imageView2:
                    card2.setVisibility(View.INVISIBLE);
                    break;

                case R.id.imageView3:
                    card3.setVisibility(View.INVISIBLE);
                    break;
            }

            int evaluacion =gameComplemento.evalua(numeroSeleccionado, resultadoOperacion);

            if(evaluacion==1){
                llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);
                //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                evaluaTest();
                generaNumeros();

            }else if(evaluacion==2){
                llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);
                //Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                evaluaTest();
                generaNumeros();

            }else if(evaluacion==0){
                //llamarPopup(getResources().getString(R.string.selecciona_mas),R.drawable.robin);
                Toast.makeText(getApplicationContext(),"Selecciona más tarjetas ",Toast.LENGTH_SHORT).show();
            }

        }else{
            //Toast.makeText(getApplicationContext(),"Selecciona los numeros de abajo:::",Toast.LENGTH_LONG).show();
            llamarPopup(getResources().getString(R.string.selecciona_mumeros_abajo),R.drawable.robin);
        }


    }


    public void generaNumeros() {
        int numeroIteracion=1;

        resultadoOperacion = gameComplemento.getNumberaleatorio();
        //resultadoOperacion=-1;
       if(resultadoOperacion ==1){
            elemento1=0;
            elemento2=1;
            elemento3=0;
        }else if(resultadoOperacion >1){
            while(resultado!= resultadoOperacion) {
                elemento1=gameComplemento.getNumeroAleatorioRango(resultadoOperacion);
                elemento2=gameComplemento.getNumeroAleatorioRango(resultadoOperacion);
                elemento3=gameComplemento.getNumeroAleatorioRango(resultadoOperacion);
                resultado = elemento1 + elemento2 + elemento3;
                numeroIteracion++;
                //System.out.println("el resutlado--->"+numero+" para a es a---> "+a+"para b -->"+b+" para c-->"+c);
            }
            resultado=0;

        }
        // si hay elementos con cero le pondremos algun otro numero para confundir
        if(elemento1 ==0 ){
            elemento1=gameComplemento.getNumeroAleatorioSinCero(10);
        }
        if(elemento3 ==0){
            elemento3=gameComplemento.getNumeroAleatorioSinCero(10);
        }
        if(elemento2==0){
            elemento2=gameComplemento.getNumeroAleatorioSinCero(10);
        }


        if(resultadoOperacion ==-1){
            llamaResultado(super.getActividad(),  tFinal,  tInicio, Num3_1Activity.class,gameComplemento.getNumeros(),gameComplemento.getAciertosTotales(),gameComplemento.getFallosTotales(),gameComplemento.getNumEcxluidosList(),gameComplemento.getNumeroPregunta());
       }else {

       /*obitene las limagenes correspondientes*/
            imageResult = gameComplemento.getImageResource().get(Integer.valueOf(resultadoOperacion));
            imageElment1 = gameComplemento.getImageResource().get(Integer.valueOf(elemento1));
            imageElment2 = gameComplemento.getImageResource().get(Integer.valueOf(elemento2));
            imageElment3 = gameComplemento.getImageResource().get(Integer.valueOf(elemento3));
            //int number=gameComplemento.getImageResource().get(Integer.valueOf(imageResult));


            //asigna la imagen al boton
            imageBResult.setImageResource(imageResult);
            //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
            imageBResult.setLongClickable(true);
            imageBResult.setOnClickListener(this);
            //imageBResult.setVisibility(View.VISIBLE);

            //se agrega al mapa
            // numPantallaMap.put(imageBResult.getId(),number);

            // number=gameComplemento.getImageResource().get(Integer.valueOf(imageElment1));
            //asigna la imagen al boton
            imageBElement1.setImageResource(imageElment1);
            //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
            imageBElement1.setLongClickable(true);
            imageBElement1.setOnClickListener(this);
            imageBElement1.setVisibility(View.VISIBLE);
            card1.setVisibility(View.VISIBLE);
            numPantallaMap.put(imageBElement1.getId(), elemento1);


            //number=gameComplemento.getImageResource().get(Integer.valueOf(imageElment2));
            //asigna la imagen al boton
            imageBElement2.setImageResource(imageElment2);
            //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
            imageBElement2.setLongClickable(true);
            imageBElement2.setOnClickListener(this);
            imageBElement2.setVisibility(View.VISIBLE);
            card2.setVisibility(View.VISIBLE);
            numPantallaMap.put(imageBElement2.getId(), elemento2);


            //number=gameComplemento.getImageResource().get(Integer.valueOf(imageElment3));
            //asigna la imagen al boton
            imageBElement3.setImageResource(imageElment3);
            //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
            imageBElement3.setLongClickable(true);
            imageBElement3.setOnClickListener(this);
            imageBElement3.setVisibility(View.VISIBLE);
            card3.setVisibility(View.VISIBLE);
            numPantallaMap.put(imageBElement3.getId(), elemento3);

        }

    }


    /**
     *
     */
    public void evaluaTest(){

        int result =gameComplemento.isTestPass();
        if(result ==0){
//            llamaResultado();
           // llamaOtroNivel(Num3_5Activity.class);
            super.llamaOtroNivel(super.getActividad(),this.getClass(),Num3_5Activity.class,gameComplemento.getNumeros(),gameComplemento.getAciertosTotales(),gameComplemento.getFallosTotales(),gameComplemento.getNumEcxluidosList(),gameComplemento.getNumeroPregunta(), new UsuarioFB());
        }else if(result==1){
            //llamaVideo();
            super.llamaOtroNivel(super.getActividad(),this.getClass(),VideoNumeros.class,gameComplemento.getNumeros(),gameComplemento.getAciertosTotales(),gameComplemento.getFallosTotales(),gameComplemento.getNumEcxluidosList(),gameComplemento.getNumeroPregunta(), new UsuarioFB());
        }
    }



    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }


}
