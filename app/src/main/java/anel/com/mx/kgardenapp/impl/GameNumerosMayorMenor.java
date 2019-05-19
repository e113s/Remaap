package anel.com.mx.kgardenapp.impl;

import android.support.v4.util.ArraySet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by agutierrez on 19/05/2018.
 */

public class GameNumerosMayorMenor extends Game implements IGame{

    int[] numerosMayorMenor ={1,2,3,4,5,6,7,8,9,10};
    int[] imagenesNumerosMayorMenorImagenes ={R.drawable.col_un_sombrero, R.drawable.col_dos_sombrero, R.drawable.col_tres_sombrero, R.drawable.col_cuatro_sombrero, R.drawable.col_cinco_sombrero, R.drawable.col_seis_sombrero, R.drawable.col_siete_sombrero, R.drawable.col_ocho_sombrero, R.drawable.col_nueve_sombrero, R.drawable.col_diez_sombrero};
    Set<Integer> numEcxluidosList = new ArraySet<>();

    int numerosTotales =10;
//Para buscar errores
    //int [][] numeros= new int[33][11];
    //0 es izquierda
    //1 derecha
    int[] side ={0,1};
    int numeroIzq =0;
    int numeroDer=0;
    int numeroFinal =0;
    //    int numeroSeleccionado=5;
    int lado=0;
    //int numeroPregunta=0;
int contador=0;
   // int aciertosTotales=0, fallosTotales=0;

    Random random = new Random();
    public GameNumerosMayorMenor(Actividad actividad){

        super(actividad);

    }

    public void inicializa(){
        numeroIzq =0;
        numeroDer=0;
        numeroFinal =0;
        lado=0;

    }


    public int getImageNumber(int  numeros) {
        //Random random = new Random();
        int posicion = random.nextInt(numerosMayorMenor.length);
        int valor = imagenesNumerosMayorMenorImagenes [posicion];

        return valor;
    }

    public int getNumberaleatorio() {

        int posicion = random.nextInt(numerosMayorMenor.length);

        int valor = numerosMayorMenor[posicion];
//numEcxluidosList tienen los numeros que ya paso
        if(numEcxluidosList.size() == 10) {

            valor=666;
        }else if(numEcxluidosList.contains(Integer.valueOf(valor))){
            valor =getNumberaleatorio();
        }
        return valor;
    }



    public int getSide() {
        Random random = new Random();
        int posicion = random.nextInt(2);
        int valor = side[posicion];

        return valor;
    }

    public int getNumeroCorrecto( int numeroIzq,int numeroDer, int ladoCorrecto){

        if(ladoCorrecto ==0){
            numeroFinal=numeroIzq;
            lado =0;
        }else{
            numeroFinal= numeroDer;
            lado=1;
        }
        return numeroFinal;
    }


    public boolean evalua(int numeroSeleccionado , int ladoCorrecto, int numeroNoSeleccionado) {
        boolean resultado = false;


        if (numeroFinal == numeroSeleccionado && lado == ladoCorrecto) {
            //Exito ingresa 1 por que lo marca para ponerlo como exito
            numeros[numeroPregunta][numeroSeleccionado] = 1;
            numEcxluidosList.add(Integer.valueOf(numeroSeleccionado));
            aciertosContinuos =aciertosContinuos+1;
            aciertosTotales = aciertosTotales+1;
            erroresContinuos=0;

            resultado= true;
            //numero.setIntentoExitoso(numero.getIntentoExitoso()+1);
        } else {
            //ingrsa 3 por que fallo
            numeros[numeroPregunta][numeroFinal] = 3;
            fallosTotales= fallosTotales+1;
            erroresContinuos = erroresContinuos+1;
            aciertosContinuos =0;
        }

        // se incrementa el contador para evaluar la siguiente pregunta
        numeroPregunta=numeroPregunta+1;
        return resultado;
    }



    //TODO tres aciertos continuos pasa
/*
    public int isTestPass(){

        //numEcxluidosList.clear();

/*
        for(int i=0 ; i <= numeroPregunta ; i++){
            for(int j=0 ; j <11 ; j++){

                int valor=numeros[i][j];

                //si el valor es uno quiere decir que tiuvo acierto
                if(valor ==1){
                    aciertos =aciertos+1;
                    numEcxluidosList.add(Integer.valueOf(j));
                    //si el valor es dos quiere decir que tiuvo error
                }else if(valor ==3){
                    fallos = fallos+1;
                }

            }//fin segundo for

        if(aciertosTotales ==3 && fallosTotales ==0 ){
           // aciertosTotales =0;
            //fallosTotales =0;

            return 0;
        }

        if( fallosTotales ==3 ){
            aciertosTotales =0;
            fallosTotales =0;
            return 1;
        }

       /* if( aciertosTotales >1 & fallosTotales >=1 ){
            aciertosTotales =0;
            fallosTotales =0;
            //return 1;
        }*/

        //}//fin primer for


//        return 3;
 //   }

    public HashMap<Integer,Integer> getImageResource (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

        map.put(Integer.valueOf(1),R.drawable.num_cero);
        map.put(Integer.valueOf(1),R.drawable.col_un_sombrero);
        map.put(Integer.valueOf(2),R.drawable.col_dos_sombrero);
        map.put(Integer.valueOf(3),R.drawable.col_tres_sombrero);
        map.put(Integer.valueOf(4),R.drawable.col_cuatro_sombrero);
        map.put(Integer.valueOf(5),R.drawable.col_cinco_sombrero);
        map.put(Integer.valueOf(6),R.drawable.col_seis_sombrero);
        map.put(Integer.valueOf(7),R.drawable.col_siete_sombrero);
        map.put(Integer.valueOf(8),R.drawable.col_ocho_sombrero);
        map.put(Integer.valueOf(9),R.drawable.col_nueve_sombrero);
        map.put(Integer.valueOf(10),R.drawable.col_diez_sombrero);


        return map;

    }

    public HashMap<Integer,String> getNumeroLetrasMap (){
        HashMap<Integer,String> map = new HashMap<Integer, String>();

        map.put(Integer.valueOf(1),"1");
        map.put(Integer.valueOf(2),"2");
        map.put(Integer.valueOf(3),"3");
        map.put(Integer.valueOf(4),"4");
        map.put(Integer.valueOf(5),"5");
        map.put(Integer.valueOf(6),"6");
        map.put(Integer.valueOf(7),"7");
        map.put(Integer.valueOf(8),"8");
        map.put(Integer.valueOf(9),"9");
        map.put(Integer.valueOf(10),"10");

        return map;

    }



    public HashMap<Integer,String> getladoLetrasMap (){
        HashMap<Integer,String> map = new HashMap<Integer, String>();

        map.put(Integer.valueOf(0),"izquierda");
        map.put(Integer.valueOf(1),"derecha");

        return map;

    }


    @Override
    public int isNumeroElejido(Set numerosSet) {
        return 0;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public int getAciertosTotales() {
        return aciertosTotales;
    }

    public int getFallosTotales() {
        return fallosTotales;
    }


}

