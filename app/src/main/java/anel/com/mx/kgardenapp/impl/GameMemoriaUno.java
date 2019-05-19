package anel.com.mx.kgardenapp.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by Anel Gutierrez
 * esta clase tendra la logica de juego para los numeros y su memoria
 */

public class GameMemoriaUno extends Game implements IGame {

    int[] numerosSimples ={1,2,3,4,5,6,7,8,9,10};

    int[] imagenes ={R.drawable.num_cero_azul,R.drawable.num_uno_azul,R.drawable.num_dos_azul,R.drawable.num_tres_azul,
            R.drawable.num_cuatro_azul,R.drawable.num_cinco_azul,R.drawable.num_seis_azul,R.drawable.num_siete_azul,R.drawable.num_ocho_azul,R.drawable.num_nueve_azul,R.drawable.num_diez_azul};

    Random random = new Random();
    private Set<Integer> numerosSet = new HashSet<Integer>();

    int resultado = 0;

    int dificultad =1;
    int numerosElejidos[]= new int[5];

    /**
     *
     * @param actividad
     */
    public GameMemoriaUno(Actividad actividad) {
        super(actividad);
    }



    public void inicializa(){

        numerosSet.clear();
    }

    /**
     * obtiene el numero aleatorio
     * @return numeor entero que no se puede repetir
     */
    public int getNumberaleatorio() {
        int posicion = random.nextInt(numerosSimples.length);
        int valor = numerosSimples[posicion];

        try{

            if(numEcxluidosList.size() == 11) {
                valor=-1;
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

    /**
     * obtiene el resource que le corresponde al numero
     * @param numeros
     * @return
     */
    public int getImageNumber(int  numeros) {
        Random random = new Random();
        int posicion = random.nextInt(numerosSimples.length);
        int valor = imagenes[posicion];

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

    public void setFallosTotales(int fallosTotales) {
        this.fallosTotales = fallosTotales;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int[] getNumerosElejidos() {
        return numerosElejidos;
    }

    public void setNumerosElejidos(int[] numerosElejidos) {
        this.numerosElejidos = numerosElejidos;
    }
}
