package anel.com.mx.kgardenapp.dto;

import anel.com.mx.kgardenapp.R;

/**
 * Created by ddarredondo on 24/04/2018.
 */

public class Numero {


    public Numero(){

    }

    public Numero(int valor){
    this.valor=valor;
    }

    public int[] imagenesNumeros ={R.drawable.cero ,R.drawable.uno,R.drawable.dos,R.drawable.tres,R.drawable.cuatro,R.drawable.cinco,R.drawable.seis,R.drawable.siete,R.drawable.ocho,R.drawable.nueve,R.drawable.diez};

    public int[] imagenesLetras ={};

    public int [] distractores ={};

    public  int valor;
    public int intentosFallidos =0;
    public int intentoExitoso = 0;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public int getIntentoExitoso() {
        return intentoExitoso;
    }

    public void setIntentoExitoso(int intentoExitoso) {
        this.intentoExitoso = intentoExitoso;
    }
}
