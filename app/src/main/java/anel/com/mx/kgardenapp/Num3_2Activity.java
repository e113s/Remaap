package anel.com.mx.kgardenapp;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.impl.GameOperacionesAritmeticas;

/**
 * Actividad que va a implementar el juego de operaciones aritmeticas basicas
 * @version 1.0 la implementación es operaciones basicas suma y resta
 *
 * @author Anel Guitierres/ALberto Delgadillo
 * Esta actividad es para el juego de sumas y restas
 * Es iun juego donde se combinan los primeros diez numeros y se realizan sumas acorde a la edad del niño
 *
 */
public class Num3_2Activity extends NumParentActivity implements View.OnClickListener {

    private CardView cardView,cardViewOpcionUno,cardViewOpcionDos,cardViewOpcionTres;
    private ImageView imageViewNumeroUno,imageViewOperador, imageViewNumeroDos;
    private ImageView imageViewOpcionUno,imageViewOpcionDos, imageViewOpcionTres;

    private GameOperacionesAritmeticas gameOperaciones;
    private int elemento1R=0,elemento2R=0,operadorR=0, opcion1R=0,opcion2R=0,opcion3R=0;

    private int resultado=0,elemento1=0,elemento2=0,operador=0, opcion1=0,opcion2=0,opcion3=0,
            numeroObtenido =0,imagenNumero, numeroSeleccionado;


    //variables tiempo
    private long tInicio, tFinal, tDuracion;

    HashMap<Integer,Integer> numPantallaMap = new HashMap<Integer, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3_2);


        //obtiene informacion de un a actividad anterior
        //getInformacion();
        super.getInformation();
        gameOperaciones = new GameOperacionesAritmeticas(super.getActividad());

        //aginando los elementos de la pantalla
        cardView = findViewById(R.id.cardViewNumeroUno);
        imageViewNumeroUno=findViewById(R.id.imageViewNumeroUno);
        imageViewOperador=findViewById(R.id.imageViewOperador);
        imageViewNumeroDos=findViewById(R.id.imageViewNumeroDos);

        cardViewOpcionUno= findViewById(R.id.cardViewOpcionUno);
        cardViewOpcionDos= findViewById(R.id.cardViewOpcionDos);
        cardViewOpcionTres =findViewById(R.id.cardViewOpcionTres);

        imageViewOpcionUno= findViewById(R.id.imageViewOpcionUno);
        imageViewOpcionDos= findViewById(R.id.imageViewOpcionDos);
        imageViewOpcionTres= findViewById(R.id.imageViewOpcionTres);


        //genero los primeros numeros
        generaNumeros();
        // inicio de duración de la actividad
        // begin of timing about gamer
        tInicio = System.currentTimeMillis();


    }

    public void generaNumeros() {
        int numeroIteracion = 1;

        numeroObtenido = gameOperaciones.getNumberaleatorio();

        if(numeroObtenido ==-1){

            llamaResultado(super.getActividad(),  tFinal,  tInicio, Num3_5_0Activity.class,gameOperaciones.getNumeros(),gameOperaciones.getAciertosTotales(),gameOperaciones.getFallosTotales(),gameOperaciones.getNumEcxluidosList(),gameOperaciones.getNumeroPregunta());

        }else {

            /*realiza las sumas*/

            int operador =gameOperaciones.getSide();

            // si el operador es cero es suma
            if(operador ==0){
                operador=11;
                if(numeroObtenido ==1){
                    elemento1=1;
                    elemento2=0;
                }else if(numeroObtenido >1){
                    while(resultado!= numeroObtenido) {
                        elemento1=gameOperaciones.getNumeroAleatorioRango(numeroObtenido);
                        elemento2=gameOperaciones.getNumeroAleatorioRango(numeroObtenido);
                        resultado = elemento1 + elemento2 ;
                        numeroIteracion++;
                        //System.out.println("el resutlado--->"+numero+" para a es a---> "+a+"para b -->"+b+" para c-->"+c);
                    }
                    resultado=0;

                }
                // si el operador es uno es resta
            }else if(operador ==1){
               operador=12;
                if(numeroObtenido ==1){
                    elemento1=0;
                    elemento2=1;
                }else if(numeroObtenido >1){
                    while(resultado!= numeroObtenido) {
                        elemento1=gameOperaciones.getNumeroAleatorioRango(numeroObtenido);
                        elemento2=gameOperaciones.getNumeroAleatorioRango(numeroObtenido);
                        resultado = elemento1 - elemento2 ;
                        numeroIteracion++;
                        //System.out.println("el resutlado--->"+numero+" para a es a---> "+a+"para b -->"+b+" para c-->"+c);
                    }
                    resultado=0;

                }
            }

            /*Fina realiza las sumas*/

            opcion1=gameOperaciones.getNumeroAleatorioSinCero(10);
            opcion2=gameOperaciones.getNumeroAleatorioSinCero(10);
            opcion3=gameOperaciones.getNumeroAleatorioSinCero(10);

            while(opcion1 == numeroObtenido){
                elemento1=gameOperaciones.getNumeroAleatorioSinCero(10);
            }
            while(opcion2 == numeroObtenido || opcion1==opcion2 ){
                elemento2=gameOperaciones.getNumeroAleatorioSinCero(10);
            }
            while(opcion3 == numeroObtenido || opcion1==opcion2 || opcion2==opcion3 ){
                opcion3=gameOperaciones.getNumeroAleatorioSinCero(10);
            }

            int numeroTarjeta =gameOperaciones.getNumeroAleatorioRango(1);
            if(numeroTarjeta ==0){
                opcion1 = numeroObtenido;
            }else if (numeroTarjeta ==1){
                opcion2 = numeroObtenido;
            }else if(numeroTarjeta ==2){
                opcion3= numeroObtenido;
            }



             /*obitene las limagenes correspondientes*/
            elemento1R = gameOperaciones.getImageResource().get(Integer.valueOf(elemento1));
            operadorR = gameOperaciones.getImageResource().get(Integer.valueOf(operador));
            elemento2R = gameOperaciones.getImageResource().get(Integer.valueOf(elemento2));



            imageViewNumeroUno.setImageResource(elemento1R);
            imageViewOperador.setImageResource(operadorR);
            imageViewNumeroDos.setImageResource(elemento2R);
            //coloco la propiedad listener para poder escuchar cuando hagla touch en la imagen
            cardView.setOnClickListener(this);

            cardView.setVisibility(View.VISIBLE);


            opcion1R= gameOperaciones.getImageResource().get(Integer.valueOf(opcion1));
            opcion2R= gameOperaciones.getImageResource().get(Integer.valueOf(opcion2));
            opcion3R= gameOperaciones.getImageResource().get(Integer.valueOf(opcion3));

            imageViewOpcionUno.setImageResource(opcion1R);
            imageViewOpcionDos.setImageResource(opcion2R);
            imageViewOpcionTres.setImageResource(opcion3R);

            cardViewOpcionUno.setOnClickListener(this);

            cardViewOpcionUno.setVisibility(View.VISIBLE);
            numPantallaMap.put(cardViewOpcionUno.getId(), opcion1);

            cardViewOpcionDos.setOnClickListener(this);

            cardViewOpcionDos.setVisibility(View.VISIBLE);
            numPantallaMap.put(cardViewOpcionDos.getId(), opcion2);

            cardViewOpcionTres.setOnClickListener(this);

            cardViewOpcionTres.setVisibility(View.VISIBLE);
            numPantallaMap.put(cardViewOpcionTres.getId(), opcion3);

        }


    }




    @Override
    public void onClick(View view) {

        CardView iv = (CardView) view;




        if (numPantallaMap.get(iv.getId()) != null) {

            numeroSeleccionado = numPantallaMap.get(iv.getId());

            Animation animation = new RotateAnimation(0,80);
            animation.setDuration(500);

            int evaluacion =gameOperaciones.evalua(numeroSeleccionado, numeroObtenido);

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


        }
    }




    public void evaluaTest(){

        int result =gameOperaciones.isTestPass();
        if(result ==0){
//            llamaResultado();
            // llamaOtroNivel(Num3_5Activity.class);

            super.llamaOtroNivel(super.getActividad(),this.getClass(),Num3_2Activity.class,gameOperaciones.getNumeros(),gameOperaciones.getAciertosTotales(),gameOperaciones.getFallosTotales(),gameOperaciones.getNumEcxluidosList(),gameOperaciones.getNumeroPregunta(),new UsuarioFB());
        }else if(result==1){
            //llamaVideo();
            super.llamaOtroNivel(super.getActividad(),this.getClass(),VideoNumeros.class,gameOperaciones.getNumeros(),gameOperaciones.getAciertosTotales(),gameOperaciones.getFallosTotales(),gameOperaciones.getNumEcxluidosList(),gameOperaciones.getNumeroPregunta(), new UsuarioFB());
        }
    }


    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }

}
