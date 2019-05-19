package anel.com.mx.kgardenapp.impl;

import android.os.Bundle;
import android.support.v4.util.ArraySet;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**getAciertosTotales
 * Created by ddarredondo on 26/04/2018.
 */

public class GameNumerosSimples extends Game implements IGame{

    int[] numerosSimples ={0,1,2,3,4,5,6,7,8,9,10};

    int[] imagenesNumerosImagenes ={R.drawable.num_cero_color ,R.drawable.num_uno_color,R.drawable.num_dos_color,R.drawable.num_tres_color,R.drawable.num_cuatro_color,R.drawable.num_cinco_color,R.drawable.num_seis_color,R.drawable.num_siete_color,R.drawable.num_ocho_color,R.drawable.num_nueve_color,R.drawable.num_diez_color};
//    Set<Integer> numEcxluidosList = new ArraySet<>();

    int numerosTotales =10;

   // int [][] numeros= new int[33][11];
    //0 es izquierda
    //1 derecha
    int[] side ={0,1};
    int numeroIzq =0;
    int numeroDer=0;
    int numeroFinal =0;
//    int numeroSeleccionado=5;
    int lado=0;

    /**
     *
     */
    public GameNumerosSimples(Actividad actividad){

        super(actividad);

    }




    public void inicializa(){
         numeroIzq =0;
         numeroDer=0;
         numeroFinal =0;
         lado=0;


        //numeroPregunta=0;
       // numEcxluidosList.clear();
        /*
        for(int i=0 ; i <33 ; i++){
            for(int j=0 ; j <11 ; j++){
                numeros[i][j]=0;
            }
        }*/
    }



    public int getImageNumber(int  numeros) {
        Random random = new Random();
        int posicion = random.nextInt(numerosSimples.length);
        int valor = imagenesNumerosImagenes[posicion];

        return valor;
    }

    public int getNumberaleatorio() {
        Random random = new Random();
        int posicion = random.nextInt(numerosSimples.length);

        int valor = numerosSimples[posicion];
        if(numEcxluidosList.size() == 11) {

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

@Override
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

/*
    public boolean esAgregadoLista(int numeroRepeticiones, int numero){

        if(numeroRepeticiones ==3){
            numEcxluidosList.add(numero);
            return true;
        }
        return false;
    }*/

//TODO tres aciertos continuos pasa
    /*
    public int isTestPass(){

        //numEcxluidosList.clear();

        int cero=0,uno=0,dos=0,tres=0,cuatro=0,cinco=0,seis=0,siete=0,ocho=0,nueve=0,diez=0;
        for(int i=0 ; i <= numeroPregunta ; i++){
            for(int j=0 ; j <11 ; j++){

                int valor=numeros[i][j];
                if(valor ==3) {
                    switch (j) {

                        case 0:
                            cero = cero + 1;
                            if(esAgregadoLista(cero,j));
                            break;
                        case 1:
                            uno = uno + 1;
                            if(esAgregadoLista(uno,j));
                            break;
                        case 2:
                            dos=dos+1;
                            if(esAgregadoLista(dos,j));
                            break;
                        case 3:
                            tres=tres+1;
                            if(esAgregadoLista(tres,j));
                            break;
                        case 4:
                            cuatro=cuatro+1;
                            if(esAgregadoLista(cuatro,j));
                            break;
                        case 5:
                            cinco=cinco+1;
                            if(esAgregadoLista(cinco,j));
                            break;
                        case 6:
                            seis=seis+1;
                            if(esAgregadoLista(seis,j));
                            break;
                        case 7:
                            siete=siete+1;
                            if(esAgregadoLista(siete,j));
                            break;
                        case 8:
                            ocho=ocho+1;
                            if(esAgregadoLista(ocho,j));
                            break;
                        case 9:
                            nueve=nueve+1;
                            if(esAgregadoLista(nueve,j));
                            break;
                        case 10:
                            diez=diez+1;
                            if(esAgregadoLista(diez,j));
                            break;
                    }


                }
            }//fin segundo for

            if(aciertosContinuos ==3 ){
                aciertosContinuos =0;
                return 0;
            }
            if( erroresContinuos ==3 ){
                aciertosContinuos =0;
                return 1;
            }


        }//fin primer for

        return 3;
    }

*/

    public HashMap<Integer,Integer> getImageResource (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

        map.put(Integer.valueOf(0),R.drawable.num_cero_color);
        map.put(Integer.valueOf(1),R.drawable.num_uno_color);
        map.put(Integer.valueOf(2),R.drawable.num_dos_color);
        map.put(Integer.valueOf(3),R.drawable.num_tres_color);
        map.put(Integer.valueOf(4),R.drawable.num_cuatro_color);
        map.put(Integer.valueOf(5),R.drawable.num_cinco_color);
        map.put(Integer.valueOf(6),R.drawable.num_seis_color);
        map.put(Integer.valueOf(7),R.drawable.num_siete_color);
        map.put(Integer.valueOf(8),R.drawable.num_ocho_color);
        map.put(Integer.valueOf(9),R.drawable.num_nueve_color);
        map.put(Integer.valueOf(10),R.drawable.num_diez_color);


        return map;

    }


    public HashMap<Integer,String> getNumeroLetrasMap (){
        HashMap<Integer,String> map = new HashMap<Integer, String>();

        map.put(Integer.valueOf(0),"cero");
        map.put(Integer.valueOf(1),"uno");
        map.put(Integer.valueOf(2),"dos");
        map.put(Integer.valueOf(3),"tres");
        map.put(Integer.valueOf(4),"cuatro");
        map.put(Integer.valueOf(5),"cinco");
        map.put(Integer.valueOf(6),"seis");
        map.put(Integer.valueOf(7),"siete");
        map.put(Integer.valueOf(8),"ocho");
        map.put(Integer.valueOf(9),"nueve");
        map.put(Integer.valueOf(10),"diez");


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

    public HashMap<String, String> getAudioNumero (){
        HashMap<String,String> map = new HashMap<String, String>();

        map.put("cero", "R.raw.cero");
        map.put("uno", "R.raw.uno");
        map.put("dos", "R.raw.dos");
        map.put("tres", "R.raw.tres");
        map.put("cuatro", "R.raw.cuatro");
        map.put("cinco", "R.raw.cinco");
        map.put("seis", "R.raw.seis");
        map.put("siete", "R.raw.siete");
        map.put("ocho", "R.raw.ocho");
        map.put("nueve", "R.raw.nueve");
        map.put("diez", "R.raw.diez");


        return map;

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


    public int[][] getNumeros() {
        return numeros;
    }

    public int getErroresContinuos() {
        return erroresContinuos;
    }

    public void setErroresContinuos(int erroresContinuos) {
        this.erroresContinuos = erroresContinuos;
    }

    public int getAciertosContinuos() {
        return aciertosContinuos;
    }

    public void setAciertosContinuos(int aciertosContinuos) {
        this.aciertosContinuos = aciertosContinuos;
    }

}
