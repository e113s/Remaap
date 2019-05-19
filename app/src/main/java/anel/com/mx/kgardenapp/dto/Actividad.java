package anel.com.mx.kgardenapp.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArraySet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * clase para guardar el comportamiento del jugador y datos adicionales ,
 * clase usada para ser pasada entre actividades.
 */

public class Actividad implements Parcelable{

    public int[][] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[][] numeros) {
        this.numeros = numeros;
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

    public Set<Integer> getNumEcxluidosList() {
        return numEcxluidosList;
    }

    public void setNumEcxluidosList(Set<Integer> numEcxluidosList) {
        this.numEcxluidosList = numEcxluidosList;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDuracionActividad() {
        return duracionActividad;
    }

    public void setDuracionActividad(long duracionActividad) {
        this.duracionActividad = duracionActividad;
    }

    public String getEdad() {
        return edad;

    }

    public void setEdad(String edad) {
        this.edad = edad;
    }


    /**
     * deafult constructor
     */
    public Actividad(){

    }

    public Actividad(int[][] numeros, int numeroPregunta, int aciertosTotales, int fallosTotales, int erroresContinuos, int aciertosContinuos, String sexo, String nombre, String edad, int duracionActividad, Set<Integer> numEcxluidosList) {
        this.numeros = numeros;
        this.numeroPregunta = numeroPregunta;
        this.aciertosTotales = aciertosTotales;
        this.fallosTotales = fallosTotales;
        this.erroresContinuos = erroresContinuos;
        this.aciertosContinuos = aciertosContinuos;
        this.sexo = sexo;
        this.nombre = nombre;
        this.edad = edad;
        this.duracionActividad = duracionActividad;
        this.numEcxluidosList = numEcxluidosList;
    }

    /**
     * Fields
     */
    private int [][] numeros= new int[33][11];
    private int numeroPregunta;
    private int aciertosTotales;
    private int fallosTotales;
    private int erroresContinuos;
    private int aciertosContinuos;
    private String sexo;
    private String nombre;
    private String edad;
    private long duracionActividad;
    private Set<Integer> numEcxluidosList = new ArraySet<>();

    public static final Parcelable.Creator<Actividad> CREATOR = new Parcelable.Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel source) {
            return new Actividad(source);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(numeroPregunta);
        parcel.writeInt(aciertosTotales);
        parcel.writeInt(fallosTotales);
        parcel.writeInt(erroresContinuos);
        parcel.writeInt(aciertosContinuos);
        parcel.writeString(sexo);
        parcel.writeString(nombre);
        parcel.writeString(edad);
        parcel.writeLong(duracionActividad);

    }

    /**
     *
     * @param in
     */
    public Actividad(Parcel in){

        this.numeroPregunta=in.readInt();
        this.aciertosTotales=in.readInt();
        this.fallosTotales =in.readInt();
        this.erroresContinuos=in.readInt();
        this.aciertosContinuos=in.readInt();
        this.sexo=in.readString();
        this.nombre=in.readString();
        this.edad=in.readString();
        this.duracionActividad=in.readLong();

    }


}
