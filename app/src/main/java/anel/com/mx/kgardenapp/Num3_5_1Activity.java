package anel.com.mx.kgardenapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.dto.Player;
import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.impl.DrawingActivity;
import anel.com.mx.kgardenapp.impl.Game;
import anel.com.mx.kgardenapp.impl.GameMultiplesNumLetras;
import anel.com.mx.kgardenapp.impl.GameMultiplesNumeros;
import anel.com.mx.kgardenapp.impl.GameNumerosSimples;
import anel.com.mx.kgardenapp.impl.IGame;

/**
 * @author Anel Gutierrez
 * @version 1.0
 *
 */
public class Num3_5_1Activity extends  NumParentActivity  implements View.OnClickListener{

    public MediaPlayer mp;
    public MediaPlayer mpInstruccion;
    private String sexo;
    private String nombre;
    private String edad;
    private TextView textViewUsuario;
    private  TextView textViewEdad;
    private ImageView imageView;
    private Player player;
    TextView texto;

    private int buttons[] ={R.id.imageButton1,R.id.imageButton2,R.id.imageButton3,R.id.imageButton4,R.id.imageButton5,R.id.imageButton6,
                            R.id.imageButton7,R.id.imageButton8,R.id.imageButton9,R.id.imageButton10,R.id.imageButton11,R.id.imageButton12};
    HashMap<Integer,Integer> numerosIzquierda = new HashMap<Integer, Integer>();
    HashMap<Integer,Integer> numerosDerecha = new HashMap<Integer, Integer>();
    private SharedPreferences preferences ;

    private IGame gameMultiplesNumeros;

    private long tInicio, tFinal, tDuracion;



    private android.widget.ImageButton button;
    //varibales control
    int imagenNumberI =0,imagenNumberD=0,imagenNumberFinal =0,ladoCorrecto=0,numeroFinal=0, numI =0, numD=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3_5_1);
        texto =findViewById(R.id.textViewNumber);
        //genera los primeros numeros para preguntar
        super.getInformation();
        gameMultiplesNumeros = new GameMultiplesNumLetras(super.getActividad());
        gameMultiplesNumeros.inicializa();
        generaNumeros();

        tInicio = System.currentTimeMillis();


    }

    @Override
    public void onClick(View view) {

        ImageButton b = (ImageButton) view;

        if(numerosIzquierda.get(b.getId()) != null){
            int numeroIzq= numerosIzquierda.get(b.getId());
            int ladoSeleccionado=0;
           // Toast.makeText(getApplicationContext(),"presionaste el boton con el id"+b.getId(),Toast.LENGTH_LONG).show();

            if(gameMultiplesNumeros.evalua(numI,ladoSeleccionado,numD)){
                //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);
            }else{
                //Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);
            }


            if(evaluaTest()) {
                generaNumeros();
            }

        }else if(numerosDerecha.get(b.getId()) != null){
            int numeroDer= numerosDerecha.get(b.getId());
           // Toast.makeText(getApplicationContext(),"presionaste el boton con el id"+b.getId(),Toast.LENGTH_LONG).show();

            int ladoSeleccionado=1;
            if(gameMultiplesNumeros.evalua(numD,ladoSeleccionado,numI)){
                //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);
            }else{
                //Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);
            }


            if(evaluaTest()) {
                generaNumeros();
            }
        }

        //int resultado =this.game.getNumberFromResource().get(b.getDrawingCacheBackgroundColor());
        /*
        ImageButton b = (ImageButton) view;
        Integer res=(Integer)b.getId();
        Toast.makeText(getApplicationContext(),"presionaste el boton con el id"+id,Toast.LENGTH_LONG).show();
        int resultado =this.game.getNumberFromResource().get(res);

        Toast.makeText(getApplicationContext(),"tiene el numero::::"+resultado,Toast.LENGTH_LONG).show();
        */
    }

    public void generaNumeros() {

        int number =0;
        int numeroSeleccionado=gameMultiplesNumeros.getNumberaleatorio();
        if(numeroSeleccionado == 666){
            //llamaResultado(super.getActividad(),  tFinal,  tInicio, Num3_1Activity.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta());
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta(),gameMultiplesNumeros.getAciertosContinuos(),gameMultiplesNumeros.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());
            llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta());
        }else {
            gameMultiplesNumeros.addNumerosSet(numeroSeleccionado);
            for (int i = 1; i < 6; i++) {
                //obtiene numeros del costalito menos el numero seleccionado
                number = gameMultiplesNumeros.getNumeroArreglo();
                //agrego numeros
                gameMultiplesNumeros.addNumerosSet(number);
            }
            List<Integer> lista = new ArrayList<Integer>();
            Iterator<Integer> iter = gameMultiplesNumeros.getNumerosSet().iterator();

            while (iter.hasNext()) {

                lista.add(iter.next().intValue());

            }
            Collections.shuffle(lista, new Random());

            int v = 0;
            for (Integer valor : lista) {
                button = findViewById(buttons[v]);
                // agrego numeros de la izquierda
                numerosIzquierda.put(buttons[v], valor);
                //obtiene el resource de la imagen correspondiente al numero obtenido
                int resource = gameMultiplesNumeros.getImageResource().get(valor);
                button.setBackgroundResource(resource);
                button.setOnClickListener(this);
                v++;
            }


            numI = numeroSeleccionado;
            //limpia los valores
            gameMultiplesNumeros.clearNumerosSet();

            // asgina los numeros de la derecha
            numeroSeleccionado = gameMultiplesNumeros.getNumberaleatorio();
            if(numeroSeleccionado ==666){
                numeroSeleccionado=5;
            }

            gameMultiplesNumeros.addNumerosSet(numeroSeleccionado);
            for (int i = 1; i < 6; i++) {
                //obtiene numeros del costalito menos el numero seleccionado
                number = gameMultiplesNumeros.getNumeroArreglo();
                //agrego numeros
                gameMultiplesNumeros.addNumerosSet(number);
            }
            lista = new ArrayList<Integer>();
            iter =gameMultiplesNumeros.getNumerosSet().iterator();

            while(iter.hasNext()) {

                lista.add(iter.next().intValue());

            }
            Collections.shuffle(lista,new Random());
            Collections.shuffle(lista,new Random());

            //llena los numeros del 6 al 12
            v=6;
            for (Integer valor : lista) {
                button = findViewById(buttons[v]);
                // agrego numeros de la izquierda
                numerosDerecha.put(buttons[v], valor);
                //obtiene el resource de la imagen correspondiente al numero obtenido
                int resource = gameMultiplesNumeros.getImageResource().get(valor);
                button.setBackgroundResource(resource);
                button.setOnClickListener(this);
                v++;
            }

            numD = numeroSeleccionado;
            gameMultiplesNumeros.getNumerosSet().clear();

            ladoCorrecto = gameMultiplesNumeros.getSide();
            //Toast.makeText(getApplicationContext(),"lado que a seleccionar:::"+ladoCorrecto,Toast.LENGTH_LONG).show();
            numeroFinal = gameMultiplesNumeros.getNumeroCorrecto(numI, numD, ladoCorrecto);
            //Toast.makeText(getApplicationContext(),"numero final:::"+numeroFinal,Toast.LENGTH_LONG).show();


            String cadena = "Toca el número ";
            cadena = cadena.concat(gameMultiplesNumeros.getNumeroLetrasMap().get(numeroFinal).toString()).concat(" de la ").
                    concat(gameMultiplesNumeros.getladoLetrasMap().get(Integer.valueOf(ladoCorrecto)).toString());
            texto.setText(cadena);


            // agrega auido
            Thread thread = new Thread() {

                public void run() {
                    try {
                        mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.tocael);
                        mpInstruccion.start();
                        sleep(1500);
                        mpInstruccion.release();

                        if (numeroFinal == 0) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cero);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 1) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.uno);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 2) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.dos);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 3) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.tres);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 4) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cuatro);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 5) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.cinco);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 6) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.seis);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 7) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.siete);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 8) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.ocho);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();
                        } else if (numeroFinal == 9) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.nueve);
                            mpInstruccion.start();
                            sleep(1200);
                            mpInstruccion.release();
                        } else if (numeroFinal == 10) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.diez);
                            mpInstruccion.start();
                            sleep(1200);
                            mpInstruccion.release();
                        }


                        mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.dela);
                        mpInstruccion.start();
                        sleep(900);
                        mpInstruccion.release();

                        if (ladoCorrecto == 0) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.izquierda);
                            mpInstruccion.start();
                            sleep(1200);
                            mpInstruccion.release();

                        } else if (ladoCorrecto == 1) {
                            mpInstruccion = MediaPlayer.create(getApplicationContext(), R.raw.derecha);
                            mpInstruccion.start();
                            sleep(1200);
                            mpInstruccion.release();

                        }


                        // finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }//fin del else

    }


    public boolean evaluaTest(){
        boolean retorno= true;
        int result = gameMultiplesNumeros.isTestPass();
        if(result ==0){
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta(),gameMultiplesNumeros.getAciertosContinuos(),gameMultiplesNumeros.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta());
            //Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);

            //startActivity(intent);
//            super.llamaOtroNivel(super.getActividad(),Num3_7Activity.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta());
            retorno= true;

        }else if(result==1){

            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, VideoNumeros.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta(),gameMultiplesNumeros.getAciertosContinuos(),gameMultiplesNumeros.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());
            super.llamaOtroNivel(super.getActividad(),this.getClass(),VideoNumeros.class,gameMultiplesNumeros.getNumeros(),gameMultiplesNumeros.getAciertosTotales(),gameMultiplesNumeros.getFallosTotales(),gameMultiplesNumeros.getNumEcxluidosList(),gameMultiplesNumeros.getNumeroPregunta(), new UsuarioFB());
            retorno= false;
        }

        return retorno;
    }

    /**
     *
     * @return
     */
    public HashMap<Integer,Integer> getNum (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

        map.put(R.drawable.num_cero_color,Integer.valueOf(0));
        map.put(R.drawable.num_uno_color,Integer.valueOf(1));
        map.put(R.drawable.num_dos_color,Integer.valueOf(2));
        map.put(R.drawable.num_tres_color,Integer.valueOf(3));
        map.put(R.drawable.num_cuatro_color,Integer.valueOf(4));
        map.put(R.drawable.num_cinco_color,Integer.valueOf(5));
        map.put(R.drawable.num_seis_color,Integer.valueOf(6));
        map.put(R.drawable.num_siete_color,Integer.valueOf(7));
        map.put(R.drawable.num_ocho_color,Integer.valueOf(8));
        map.put(R.drawable.num_nueve_color,Integer.valueOf(9));
        map.put(R.drawable.num_diez_color,Integer.valueOf(10));

        //String[] letras ={"F","P","S","E","A","G"};
        map.put(R.drawable.letra_f,Integer.valueOf(11));
        map.put(R.drawable.letra_ppng,Integer.valueOf(12));
        map.put(R.drawable.letra_s,Integer.valueOf(13));
        map.put(R.drawable.letra_e,Integer.valueOf(14));
        map.put(R.drawable.letra_a,Integer.valueOf(15));
        map.put(R.drawable.letra_g,Integer.valueOf(16));



        return map;

    }



    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }


}
