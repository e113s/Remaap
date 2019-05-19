package anel.com.mx.kgardenapp.impl;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.dto.Player;

/**
 * interface para la clase Juego 16/04/2018.
 */

public interface IGame {

    /**
     *
     * @return preferencias del jugador null si el usuario es nuevo
     */
    String getUIDUsuario();

    /**
     *
     * @param player this method should be containts values on its fields otherwise
     *               it wont retrive values if you going to use getPlayerInfoPreferences method
     * @return true if the preferences have been saved ok
     */
    public boolean setPlayerInfoPreferences(Player player);

    /**
     *
     * @return Palyer if the preferences have been get ok
     */
    Player getPlayerInfoPreferences();




        /**
         * Obtiene la información del jugador
         * @return
         */
    Player getPlayerInfo();

    /*el jugado existe*/

    /**
     *
     * @return
     */
    boolean isPlayerExist();

    /**
     * obtiene el identificado unico del usuario
     * @return
     */
    String getUserUID();



    /**
     *
     * @return
     */
    Player setPlayerInfo();

    /**
     *
     */
    public void inicializa();

    /**
     *
     * @param numero
     * @return
     */
    public int getImageNumber(int  numero);




    /**
     *
     * @return
     */
    public int getNumberaleatorio();

    /**
     *
     * @return
     */
    public int getSide() ;

    /**
     *
     * @param numeroIzq
     * @param numeroDer
     * @param ladoCorrecto
     * @return
     */
    public int getNumeroCorrecto( int numeroIzq,int numeroDer, int ladoCorrecto);

    /**
     *
     * @param numeroSeleccionado
     * @return
     */
    public boolean evalua(int numeroSeleccionado);

    /**
     *
     * @param numeroSeleccionado
     * @param ladoCorrecto
     * @param numeroNoSeleccionado
     * @return
     */
    public boolean evalua(int numeroSeleccionado , int ladoCorrecto, int numeroNoSeleccionado);



    //TODO tres numeros continuos pasa
    public int isTestPass();


    /**
     *
     * @return
     */
    public HashMap<Integer,Integer> getImageResource ();

    /**
     *
     * @return
     */
    public HashMap<Integer,String> getNumeroLetrasMap ();


    /**
     *
     * @return
     */
    public HashMap<Integer,String> getladoLetrasMap ();

    /**
     * 
     * @param numero
     */
    public void addExlutionList(int numero);

    /**
     *
     * @return
     */
    public int isNumeroElejido(Set numerosSet);


    /**
     *
     * @return
     */
    public HashMap<Integer,Integer> getNumberFromResource ();

    public int[][] getNumeros();


    public void setNumeros(int[][] numeros);

    public void setNumeroPregunta(int numeroPregunta);

    public void setAciertosTotales(int aciertosTotales);

    public void setFallosTotales(int fallosTotales);

    public int getNumeroPregunta() ;

    public int getAciertosTotales();

    public int getFallosTotales();

     int getErroresContinuos();

    public void setErroresContinuos(int erroresContinuos);

    public int getAciertosContinuos() ;

    public void setAciertosContinuos(int aciertosContinuos);

    public Set<Integer> getNumEcxluidosList();

    public void setNumEcxluidosList(Set<Integer> numEcxluidosList);




    public void clearNumerosSet();
    public Set<Integer> getNumerosSet();



    public int evalua(int numeroSeleccionado, int resultadoOperacion);

    public int getNumeroAleatorioSinCero(int numMAx);
    public int getNumeroAleatorioRango(int numMAx) ;

    public void addNumerosSet(int numero);

    public int getNumeroArreglo() ;


    public void removeNumerosSet(int numero);

    }
