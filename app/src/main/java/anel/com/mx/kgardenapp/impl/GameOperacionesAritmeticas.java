package anel.com.mx.kgardenapp.impl;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.dto.Player;

/**
 * Created by anel Gutierrez on 24/08/2018.
 */

public class GameOperacionesAritmeticas extends Game implements IGame {


    private Random random = new Random();
    //numeros con los que se trabajara
    int[] numerosSimples ={1,2,3,4,5,6,7,8,9,10};
    //imagenes asocidadas al resource  37 imagenes


    int resultado = 0;
    int suma=0;
    /**
     *
     * @param actividad
     */
    public GameOperacionesAritmeticas(Actividad actividad){
        super(actividad);
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
    @Override
    public int evalua(int numeroSeleccionado, int resultadoOperacion) {
        resultado=0;

        if(numeroSeleccionado == resultadoOperacion){
            resultado = 1;
            suma =0;
            numEcxluidosList.add(Integer.valueOf(resultadoOperacion));
            aciertosTotales = aciertosTotales+1;
        }else {
            resultado =2;
            suma =0;
            fallosTotales= fallosTotales+1;
        }

        numeroPregunta=numeroPregunta+1;
        return resultado;
    }

    /**
     *
     * @return a Map, This contains all reources related with the first ten numbers.
     */
    public HashMap<Integer,Integer> getImageResource (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        map.put(Integer.valueOf(0),R.drawable.num_cero_azul);
        map.put(Integer.valueOf(1),R.drawable.num_uno_azul);
        map.put(Integer.valueOf(2),R.drawable.num_dos_azul);
        map.put(Integer.valueOf(3),R.drawable.num_tres_azul);
        map.put(Integer.valueOf(4),R.drawable.num_cuatro_azul);
        map.put(Integer.valueOf(5),R.drawable.num_cinco_azul);
        map.put(Integer.valueOf(6),R.drawable.num_seis_azul);
        map.put(Integer.valueOf(7),R.drawable.num_siete_azul);
        map.put(Integer.valueOf(8),R.drawable.num_ocho_azul);
        map.put(Integer.valueOf(9),R.drawable.num_nueve_azul);
        map.put(Integer.valueOf(10),R.drawable.num_diez_azul);
        map.put(Integer.valueOf(11),R.drawable.signo_mas);
        map.put(Integer.valueOf(12),R.drawable.signo_menos);

        return map;

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
     * @return
     */
    public int getNumberaleatorio() {

        int posicion = random.nextInt(numerosSimples.length);

        int valor = numerosSimples[posicion];

        if(numEcxluidosList.size() == 11) {

            valor=-1;
        }else  if(numEcxluidosList.contains(Integer.valueOf(valor))){
            valor =getNumberaleatorio();

        }

        return valor;
    }


}
