package anel.com.mx.kgardenapp.impl;

import java.util.HashMap;
import java.util.Random;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by ddarredondo on 16/08/2018.
 */

public class GameAtencion extends Game implements IGame{



    /**
     *
     * @param actividad
     */
    public GameAtencion(Actividad actividad) {
        super(actividad);
    }


    //numeros con los que se trabajara
        int[] numerosSimples ={1,2,3,4,5,6,7,8,9,10};
        //imagenes asocidadas al resource  37 imagenes
        int[] imagenesNumerosImagenes ={R.drawable.elemento_arana,R.drawable.elemento_ave,R.drawable.elemento_avellana,R.drawable.elemento_buhito,R.drawable.elemento_buho,
                                        R.drawable.elemento_cangrejo,R.drawable.elemento_caracol,R.drawable.elemento_cera,R.drawable.elemento_cuellolargo,R.drawable.elemento_dino,
                                        R.drawable.elemento_dinosaurio,R.drawable.elemento_elefante,R.drawable.elemento_erizo,R.drawable.elemento_gato,R.drawable.elemento_hipopotamo,
                                        R.drawable.elemento_hongo,R.drawable.elemento_jirafa,R.drawable.elemento_leon,R.drawable.elemento_leon,R.drawable.elemento_manzana,
                                        R.drawable.elemento_mariposa,R.drawable.elemento_mono,R.drawable.elemento_oso,R.drawable.elemento_paleta,R.drawable.elemento_pato,
                                        R.drawable.elemento_pelicano,R.drawable.elemento_pez,R.drawable.elemento_puercoespin,R.drawable.elemento_reno,R.drawable.elemento_rino,
                                        R.drawable.elemento_serpiente,R.drawable.elemento_terodactilo,R.drawable.elemento_tigre,R.drawable.elemento_trescuernos,R.drawable.elemento_trex,
                                        R.drawable.elemento_venadito,R.drawable.elemento_zorra};
        //List<Integer> numAcertadosList = new ArrayList<Integer>();

        int numerosTotales =10;

        //int [][] numeros= new int[11][33];
        int numeroFinal =0;
        //int numeroPregunta=0;
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
        int posicion = random.nextInt(37);
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

        if(numEcxluidosList.size() >= 10) {

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
    @Override
    public int evalua(int numeroSeleccionado, int resultadoOperacion) {
        resultado=0;

        if(numeroSeleccionado == resultadoOperacion){
            resultado = 1;
            suma =0;
            numeros[numeroPregunta][numeroSeleccionado] = 1;
            numEcxluidosList.add(Integer.valueOf(numeroSeleccionado));
            aciertosContinuos =aciertosContinuos+1;
            aciertosTotales = aciertosTotales+1;
            erroresContinuos=0;


        }else {
            resultado =2;
            suma =0;
            numeros[numeroPregunta][numeroFinal] = 3;
            fallosTotales= fallosTotales+1;
            erroresContinuos = erroresContinuos+1;
            aciertosContinuos =0;
        }

        numeroPregunta=numeroPregunta+1;
        return resultado;
    }

    /**
     *
     */
    public void resetEvalua(){
        resultado = 0;
        suma=0;
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
