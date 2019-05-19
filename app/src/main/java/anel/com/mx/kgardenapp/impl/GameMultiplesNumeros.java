package anel.com.mx.kgardenapp.impl;

import android.util.ArraySet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by ddarredondo on 18/05/2018.
 */

public class GameMultiplesNumeros extends Game implements IGame {



    int[] numerosSimples ={0,1,2,3,4,5,6,7,8,9,10};

    int[] imagenes ={R.drawable.num_cero_color ,R.drawable.num_uno_color,R.drawable.num_dos_color,R.drawable.num_tres_color,R.drawable.num_cuatro_color,R.drawable.num_cinco_color,R.drawable.num_seis_color,R.drawable.num_siete_color,R.drawable.num_ocho_color,R.drawable.num_nueve_color,R.drawable.num_diez_color};
    //int[] imagenesLetras ={R.drawable.letra_f,R.drawable.letra_ppng,R.drawable.letra_s,R.drawable.letra_e,R.drawable.letra_a,R.drawable.letra_g};
    //int [][] numeros= new int[10][33];
    //0 es izquierda
    //1 derecha
    int[] side ={0,1};
    int numeroIzq =0;
    int numeroDer=0;
    int numeroFinal =0;
    //    int numeroSeleccionado=5;
    int lado=0;
    int aciertos=0, fallos=0;

    //Set<Integer> numEcxluidosList = new HashSet<Integer>();
    Set<Integer> numSortearList = new HashSet<>();


    //int aciertosTotales=0, fallosTotales=0,erroresContinuos=0,aciertosContinuos=0;

    public Random random = new Random();
    private Set<Integer> numerosSet = new HashSet<Integer>();


    /**
     *
     * @param actividad
     */
    public GameMultiplesNumeros(Actividad actividad){
     super(actividad);
    }



    public void inicializa(){
        numeroIzq =0;
        numeroDer=0;
        numeroFinal =0;
        lado=0;
        /*for(int i=0 ; i <10 ; i++){
            for(int j=0 ; j <33 ; j++){
                numeros[i][j]=0;
            }
        }*/
        numSortearList.clear();
        //numEcxluidosList.clear();
        numerosSet.clear();
    }



    public int getImageNumber(int  numeros) {
        Random random = new Random();
        int posicion = random.nextInt(numerosSimples.length);
        int valor = imagenes[posicion];

        return valor;
    }

    /**
     *
     * @return
     */
    public int getNumberaleatorio() {
        int posicion = random.nextInt(numerosSimples.length);
        int valor = numerosSimples[posicion];

        try{

            if(numEcxluidosList.size() == 11) {
                valor=666;
            }else {
                if(!numEcxluidosList.contains(Integer.valueOf(valor)) ){
                    numerosSet.add(Integer.valueOf(valor));
                }else{

                    return getNumberaleatorio();
                }
            }
        }catch (StackOverflowError sof){

            numEcxluidosList.size();


        }


        return valor;
    }




    public int getNumeroArreglo() {
        int posicion = random.nextInt(numerosSimples.length);
        int valor = numerosSimples[posicion];

        try{
                if(!numerosSet.contains(Integer.valueOf(valor)) ){
                    numerosSet.add(Integer.valueOf(valor));
                }else{

                    return getNumeroArreglo();
                }

        }catch (StackOverflowError sof){

            return 0;
        }


        return valor;
    }



    @Override
    public void clearNumerosSet() {

        numerosSet.clear();
    }






    public int esNumeroSeleccionado() {
        Random random = new Random();
        int valor = random.nextInt(6);

        if(numEcxluidosList.contains(Integer.valueOf(valor))){
            valor =getNumberaleatorio();
        }

        return valor;
    }

    /**
     * esta funcion se utiliza para agregar a la lista de numeros qeu se debe de exluir para pintar
     * @param numero
     */
    public void addExlutionList(int numero){
        numEcxluidosList.add(Integer.valueOf(numero));
    }



    @Override
    public int isNumeroElejido(Set numerosSet) {


        //numerosSet.removeIf((Integer entero) -> entero >= 11);
        int [] numerosInt = new int[6];

        Iterator<Integer> iter= numerosSet.iterator();
        int i=0;
        while(iter.hasNext()) {

            numerosInt[i++]=iter.next().intValue();

        }

        int posicion = random.nextInt(numerosInt.length);

        int valor = numerosInt[posicion];

        return valor;
    }

    public void clearExlutionList(int numero){
        numEcxluidosList.clear();
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
    }
*/


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



    public HashMap<Integer,Integer> getNumberFromResource (){
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
    public Set<Integer> getNumerosSet() {
        return numerosSet;
    }

    public void setNumerosSet(Set<Integer> numerosSet) {
        this.numerosSet = numerosSet;
    }

    public void addNumerosSet(int numero){
        numerosSet.add(Integer.valueOf(numero));
    }
    public void removeNumerosSet(int numero){
        numerosSet.remove(numero);
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
