package anel.com.mx.kgardenapp.impl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.dao.ConexionDAO;
import anel.com.mx.kgardenapp.dao.IConexion;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.dto.Numero;
import anel.com.mx.kgardenapp.dto.Player;

/**
 * Esta clase debe de considerarse para crear el juego
 */
@SuppressWarnings("serial")
public class Game extends Activity implements IGame {


    int[][] numeros = new int[33][11];
    int numeroPregunta = 0;
    int aciertosTotales = 0, fallosTotales = 0, erroresContinuos = 0, aciertosContinuos = 0;
    Set<Integer> numEcxluidosList = new ArraySet<>();
    Actividad actividad;


    // variables compartidas por todos los juegos
   /*
    int numerosTotales ;
    int[] imagenesNumerosImagenes ;
    int [][] numeros;
    //0 es izquierda
    //1 derecha
    int[] side ;
    int numeroIzq =0;
    int numeroDer=0;
    int numeroFinal =0;
    int lado=0;
    int numeroPregunta=0;
    int aciertosTotales=0, fallosTotales=0;
*/


   public Game ( Actividad actividad){


       numEcxluidosList=actividad.getNumEcxluidosList();
       numeros=actividad.getNumeros();
       aciertosTotales= actividad.getAciertosTotales();
       fallosTotales= actividad.getFallosTotales();
       numeroPregunta=actividad.getNumeroPregunta();
erroresContinuos=actividad.getErroresContinuos();
aciertosContinuos=actividad.getAciertosContinuos();

   }


   

    //propiedad para guardar las preferencias del usuario en el dispositivo
    private SharedPreferences preferences ;

    private  String userUID;
    private IConexion conexionDAO;
    private Player player;





    public Game(Player player){
     this.player =player;

    }


    public String getUIDUsuario(){


        /*
        if(userUID == null){
            throw new NullPointerException("Error on UserUID");
        }*/

        if(userUID == null) {
            Date date = new Date();
            String stringDate = String.valueOf(date.getTime());
            Random random = new Random();
            stringDate = stringDate + String.valueOf(random.nextInt(10000));
            userUID = stringDate;

            //Toast.makeText(this, "la llave userUID es  ::::::::::"+userUID, Toast.LENGTH_LONG).show();
        }
        return userUID;

    }


    public Player getPlayerInfoPreferences(){
        Player player = new Player();
        preferences = getApplication().getSharedPreferences("palyerPrefs",0);
        String userUID = preferences.getString("userUID",null);
        String name = preferences.getString("name",null);
        String age = preferences.getString("age",null);
        String gender = preferences.getString("gender",null);


        if(preferences == null){
            throw new NullPointerException("Error on preferences");
        }
        if(userUID == null){
            throw new NullPointerException("Error on preferences");
        }
        if(name == null){
            throw new NullPointerException("Error on preferences");
        }
        if(age == null){
            throw new NullPointerException("Error on preferences");
        }
        if(gender == null){
            throw new NullPointerException("Error on preferences");
        }
        player.setId(userUID);
        player.setName(name);
        player.setAge(Long.valueOf(age));
        player.setGender(gender);


        return  player;
    }

    @Override
    public Player getPlayerInfo() {
        return null;
    }


    public boolean setPlayerInfoPreferences(Player player){


        if(player.getId() == null){
            throw new NullPointerException("Error on Player");
        }

        if(player.getAge() == null){
            throw new NullPointerException("Error on Player");
        }

        if(player.getName() == null){
            throw new NullPointerException("Error on Player");
        }

        preferences = getApplication().getSharedPreferences("palyerPrefs",0);

        if(preferences == null){
            throw new NullPointerException("Error on preferences");
        }

        preferences.edit().putString("userUID", player.getId()).commit();
        preferences.edit().putString("name", player.getName()).commit();
        preferences.edit().putString("age", String.valueOf(player.getAge())).commit();
        preferences.edit().putString("gender", player.getGender()).commit();
        return true;
    }




    @Override
    public boolean isPlayerExist() {


        conexionDAO.getConnection();
        if(conexionDAO.isPlayerExist(player)){

        }else{

        }

        return false;
    }

    @Override
    public String getUserUID() {
        return null;
    }

    @Override
    public Player setPlayerInfo() {


        conexionDAO.getConnection();
        if(conexionDAO.isPlayerExist(player)){

        }else{

        }

        return null;
    }

    @Override
    public void inicializa() {

    }

    @Override
    public int getImageNumber(int numero) {
        return 0;
    }

    @Override
    public int getNumberaleatorio() {
        return 0;
    }

    @Override
    public int getSide() {
        return 0;
    }

    @Override
    public int getNumeroCorrecto(int numeroIzq, int numeroDer, int ladoCorrecto) {
        return 0;
    }

    @Override
    public boolean evalua(int numeroSeleccionado) {


        return false;
    }

    @Override
    public boolean evalua(int numeroSeleccionado, int ladoCorrecto, int numeroNoSeleccionado) {
        return false;
    }

    @Override
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

    public boolean esAgregadoLista(int numeroRepeticiones, int numero){

        if(numeroRepeticiones ==3){
            numEcxluidosList.add(numero);
            return true;
        }
        return false;
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


    public Set<Integer> getNumEcxluidosList() {
        return numEcxluidosList;
    }

    public void setNumEcxluidosList(Set<Integer> numEcxluidosList) {
        this.numEcxluidosList = numEcxluidosList;
    }


    @Override
    public HashMap<Integer, Integer> getImageResource() {
        return null;
    }

    @Override
    public HashMap<Integer, String> getNumeroLetrasMap() {

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

    @Override
    public HashMap<Integer, String> getladoLetrasMap() {
        return null;
    }

    @Override
    public void addExlutionList(int numero) {

    }

    @Override
    public int isNumeroElejido(Set numerosSet) {
        return 0;
    }

    @Override
    public HashMap<Integer, Integer> getNumberFromResource() {
        return null;
    }



    public void getNivel(){

    }

    public void getNumero(){

    }


    public void getOrientacion(){

    }

    public void pintarNumeros(){

    }

    public void setNumeros(int[][] numeros) {
        this.numeros = numeros;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public void setAciertosTotales(int aciertosTotales) {
        this.aciertosTotales = aciertosTotales;
    }

    public void setFallosTotales(int fallosTotales) {
        this.fallosTotales = fallosTotales;
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


    @Override
    public void clearNumerosSet() {

    }

    @Override
    public Set<Integer> getNumerosSet() {
        return null;
    }

    public int evalua(int numeroSeleccionado, int resultadoOperacion) {
        return 0;
    }


    public int getNumeroAleatorioSinCero(int numMAx) {
        return 0;
    }

    @Override
    public int getNumeroAleatorioRango(int numMAx) {
        return 0;
    }

    @Override
    public void addNumerosSet(int numero) {

    }

    @Override
    public int getNumeroArreglo() {
        return 0;
    }



    @Override
    public void removeNumerosSet(int numero) {

    }


}
