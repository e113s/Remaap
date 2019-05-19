package anel.com.mx.kgardenapp.firebase;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by ddarredondo on 24/09/2018.
 * This class hadndle whole diffrents issues about
 * Eje example; "comunicar los primeros diez numeros"
 * "escritura de los primeros diez numeros"
 */

public class EjeFB {

    public Integer identificador;
    public String descripcion;
    public Boolean status;
    public TreeMap temaFBTreeMap;

    /**
     * default constructor
     */
    public  EjeFB(){

    }

    public EjeFB(Integer identificador, String descripcion, Boolean status, TreeMap<?, TemaFB> temaFBTreeMap) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
        this.temaFBTreeMap = temaFBTreeMap;
    }

    /**
     *
     * @param identificador
     * @param descripcion
     * @param status
     */
    public EjeFB(Integer identificador, String descripcion, Boolean status) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
    }


}
