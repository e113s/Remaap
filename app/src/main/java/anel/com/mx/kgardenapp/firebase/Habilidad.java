package anel.com.mx.kgardenapp.firebase;

import java.util.List;
import java.util.TreeMap;

import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by Anel G.G.
 */

public class Habilidad {

    /**
     * fileds
     */
    public String identificador;
    public String descripcion;
    public Boolean status;
    public TreeMap actividadFBTreeMap;

    public  Habilidad(){

    }

    /**
     *
     * @param identificador
     * @param descripcion
     * @param status
     */
    public Habilidad(String identificador, String descripcion, Boolean status) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
    }

    /**
     *
     * @param identificador
     * @param descripcion
     * @param status
     * @param actividadFBTreeMap
     */
    public Habilidad(String identificador, String descripcion, Boolean status, TreeMap actividadFBTreeMap) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
        this.actividadFBTreeMap = actividadFBTreeMap;
    }
}
