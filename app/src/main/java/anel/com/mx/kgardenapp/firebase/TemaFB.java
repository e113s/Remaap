package anel.com.mx.kgardenapp.firebase;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Anel Gutierrez
 * this class is used to manage data releated about themes around the game
 * theme Sample. Percepcion , atencion  y memoria
 */

public class TemaFB {

    public Integer identificador;
    public String descripcion;
    public Boolean status;
    public TreeMap habilidadTreeMap;

    /**
     * constructor default
     */
    public TemaFB(){

    }

    /**
     *
     * @param identificador
     * @param descripcion
     * @param status
     */
    public TemaFB(Integer identificador, String descripcion, Boolean status) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
    }

    /**
     *
     * @param identificador
     * @param descripcion
     * @param status
     * @param habilidadTreeMap
     */
    public TemaFB(Integer identificador, String descripcion, Boolean status, TreeMap habilidadTreeMap) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.status = status;
        this.habilidadTreeMap = habilidadTreeMap;
    }
}
