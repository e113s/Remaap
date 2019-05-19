package anel.com.mx.kgardenapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 *
 */
public class GameMonedasSimples extends Game implements IGame{


    /**
     *
     * @param actividad
     */
    public GameMonedasSimples(Actividad actividad){
        super(actividad);

    }


        int[] numerosSimples ={1,2,5,10};

        int[] imagenesNumerosImagenes ={R.drawable.moneda_un_peso ,R.drawable.moneda_dos_pesos,R.drawable.moneda_cinco_peso,R.drawable.moneda_diez_pesos};
        //List<Integer> numAcertadosList = new ArrayList<Integer>();

        int numerosTotales =6;

       // int [][] numeros= new int[18][11];
        //0 es izquierda
        //1 derecha
        int[] side ={0,1};
        int numeroIzq =0;
        int numeroDer=0;
        int numeroFinal =0;
        //    int numeroSeleccionado=5;
        int lado=0;
        int numeroPregunta=0;

        int aciertosTotales=0, fallosTotales=0;






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
             if(numEcxluidosList.contains(Integer.valueOf(valor))){

                 if(numEcxluidosList.size() >= 4) {
                     return 666;
                 }else {
                        getNumberaleatorio();

                }
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
                aciertosTotales = aciertosTotales+1;
                // se pone dos por que fue el numero contra el que comparo
                if(numeroSeleccionado != numeroNoSeleccionado) {
                    numeros[numeroPregunta][numeroNoSeleccionado] = 2;
                }
                resultado= true;
                //numero.setIntentoExitoso(numero.getIntentoExitoso()+1);
            } else {
                //ingrsa 3 por que fallo
                numeros[numeroPregunta][numeroFinal] = 3;
                // se pone dos por que fue el numero contra el que comparo
                if(numeroSeleccionado != numeroNoSeleccionado) {
                    numeros[numeroPregunta][numeroNoSeleccionado] = 2;
                }
                fallosTotales= fallosTotales+1;
            }

            // se incrementa el contador para evaluar la siguiente pregunta
            numeroPregunta=numeroPregunta+1;
            return resultado;
        }


        public HashMap<Integer,Integer> getImageResource (){
            HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();


            map.put(Integer.valueOf(1),R.drawable.moneda_un_peso);
            map.put(Integer.valueOf(2),R.drawable.moneda_dos_pesos);
            map.put(Integer.valueOf(5),R.drawable.moneda_cinco_peso);
            map.put(Integer.valueOf(10),R.drawable.moneda_diez_pesos);

            map.put(Integer.valueOf(20),R.drawable.moneda_veinte_centavos);
            map.put(Integer.valueOf(50),R.drawable.moneda_cincuenta_centavos);


            return map;

        }


        public HashMap<Integer,String> getNumeroLetrasMap (){
            HashMap<Integer,String> map = new HashMap<Integer, String>();

            //map.put(Integer.valueOf(0),"cero");
            map.put(Integer.valueOf(1),"un");
            map.put(Integer.valueOf(2),"dos");
            map.put(Integer.valueOf(5),"cinco");
            map.put(Integer.valueOf(10),"diez");
            map.put(Integer.valueOf(20),"veinte");
            map.put(Integer.valueOf(50),"cincuenta");

            /*map.put(Integer.valueOf(3),"tres");
            map.put(Integer.valueOf(4),"cuatro");

            map.put(Integer.valueOf(6),"seis");
            map.put(Integer.valueOf(7),"siete");

            map.put(Integer.valueOf(8),"ocho");
            map.put(Integer.valueOf(9),"nueve");*/



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

           // map.put("cero", "R.raw.cero");
            map.put("uno", "R.raw.un");
            map.put("dos", "R.raw.dos");
            map.put("tres", "R.raw.tres");
            map.put("cinco", "R.raw.cinco");
            map.put("diez", "R.raw.diez");
            map.put("diez", "R.raw.veinte");
            map.put("diez", "R.raw.cincuenta");

            /*map.put("cuatro", "R.raw.cuatro");
            map.put("seis", "R.raw.seis");
            map.put("siete", "R.raw.siete");
            map.put("ocho", "R.raw.ocho");
            map.put("nueve", "R.raw.nueve");*/


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

    }


