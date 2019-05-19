package anel.com.mx.kgardenapp.impl;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import anel.com.mx.kgardenapp.IM_P1;
import anel.com.mx.kgardenapp.Num3_7a_Activity;
import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by ddarredondo on 19/07/2018.
 */

public class GameComplemento extends Game implements IGame{



    public GameComplemento(Actividad actividad) {
        super(actividad);
    }



    //numeros con los que se trabajara
    int[] numerosSimples ={1,2,3,4,5,6,7,8,9,10};
    //imagenes asocidadas al resource
    int[] imagenesNumerosImagenes ={R.drawable.col_uno,R.drawable.col_dos,R.drawable.col_tres,R.drawable.col_cuatro, R.drawable.col_cinco,R.drawable.col_seis,R.drawable.col_siete,R.drawable.col_ocho,R.drawable.col_nueve,R.drawable.col_diez };
    //List<Integer> numAcertadosList = new ArrayList<Integer>();

    int numerosTotales =10;

    //int [][] numeros= new int[11][33];
    int numeroFinal =0;
    int numeroPregunta=0;
    int aciertosTotales=0, fallosTotales=0;
    private int elemento1=0,elemento2=0, elemento3=0,numeroObtenido=0;

    //variables usadas cuando el usuario da click para los resultados
    int valorUno, valorDos,valorTres, valorResultado;

    //variables para el metodo evalua
    int resultado = 0;
    int suma=0;
    Random random = new Random();



    /**
     * obtiene valor de la imagen
     * @param numeros
     * @return
     */
    public int getImageNumber(int  numeros) {
        Random random = new Random();
        int posicion = random.nextInt(numerosSimples.length);
        int valor = imagenesNumerosImagenes[posicion];

        return valor;
    }

    /**
     *
     * @return
     */
    public int getNumberaleatorio() {

        int posicion = random.nextInt(numerosSimples.length);

        int valor = numerosSimples[posicion];

        if(numEcxluidosList.size() == 10) {

            valor=-1;
        }else  if(numEcxluidosList.contains(Integer.valueOf(valor))){
            valor =getNumberaleatorio();

        }

        return valor;
    }


    /**
     *
     * @param numMAx numero maximo con el que se realizara un random
     * @return un numero entero del valor numMax hasta cero
     */
    public int getNumeroAleatorioRango(int numMAx) {
        int numero = random.nextInt(numMAx);
        return numero;
    }

    /**
     *
     * @param numMAx
     * @return
     */
    public int getNumeroAleatorioSinCero(int numMAx) {
        int posicion = random.nextInt(numerosSimples.length);
        return numerosSimples[posicion];
    }



    /**
     *
     * @param numeroSeleccionado
     * @param resultadoOperacion
     * @return uno si la suma es igual al resultado de la operacion , 2 si la suma es mayor
     */
    public int evalua(int numeroSeleccionado, int resultadoOperacion) {
        resultado=0;
        suma +=numeroSeleccionado;
        if(resultadoOperacion == suma){
            resultado = 1;
            suma =0;
            numEcxluidosList.add(Integer.valueOf(resultadoOperacion));
            aciertosTotales = aciertosTotales+1;
        }else if( suma > resultadoOperacion){
            resultado =2;
            suma =0;
            fallosTotales= fallosTotales+1;
        }

        return resultado;
    }

    /**
     *
     */
    public void resetEvalua(){
        resultado = 0;
        suma=0;
    }


    public HashMap<Integer,Integer> getImageResource (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        map.put(Integer.valueOf(0),R.drawable.fill_blank);
        map.put(Integer.valueOf(1),R.drawable.col_uno);
        map.put(Integer.valueOf(2),R.drawable.col_dos);
        map.put(Integer.valueOf(3),R.drawable.col_tres);
        map.put(Integer.valueOf(4),R.drawable.col_cuatro);
        map.put(Integer.valueOf(5),R.drawable.col_cinco);
        map.put(Integer.valueOf(6),R.drawable.col_seis);
        map.put(Integer.valueOf(7),R.drawable.col_siete);
        map.put(Integer.valueOf(8),R.drawable.col_ocho);
        map.put(Integer.valueOf(9),R.drawable.col_nueve);
        map.put(Integer.valueOf(10),R.drawable.col_diez);



        return map;

    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public int getAciertosTotales() {
        return aciertosTotales;
    }

    public void setAciertosTotales(int aciertosTotales) {
        this.aciertosTotales = aciertosTotales;
    }

    public int getFallosTotales() {
        return fallosTotales;
    }

    public void setFallosTotales(int fallosTotales) {
        this.fallosTotales = fallosTotales;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getElemento1() {
        return elemento1;
    }

    public void setElemento1(int elemento1) {
        this.elemento1 = elemento1;
    }

    public int getElemento2() {
        return elemento2;
    }

    public void setElemento2(int elemento2) {
        this.elemento2 = elemento2;
    }

    public int getElemento3() {
        return elemento3;
    }

    public void setElemento3(int elemento3) {
        this.elemento3 = elemento3;
    }

    public int getNumeroObtenido() {
        return numeroObtenido;
    }

    public void setNumeroObtenido(int numeroObtenido) {
        this.numeroObtenido = numeroObtenido;
    }
}
