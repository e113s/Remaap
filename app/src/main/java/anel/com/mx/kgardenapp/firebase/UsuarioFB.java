package anel.com.mx.kgardenapp.firebase;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by
 */

public class UsuarioFB implements Serializable{

    public String id;
    public String nombre;
    public Integer edad;
    public String sexo;
    public String fechaUltimoIngreso;
    public String fechaprimerIngreso;
    public String status;
    public TreeMap ejeFBTreeMap;
    public List<EjeFB> ejesList;


    /**
     *
     */
    public UsuarioFB() {

    }

    /**
     *
     * @param id
     * @param nombre
     * @param edad
     * @param sexo
     * @param fechaUltimoIngreso
     * @param fechaprimerIngreso
     * @param status
     */
    public UsuarioFB(String id, String nombre, Integer edad, String sexo, String fechaUltimoIngreso, String fechaprimerIngreso, String status) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaUltimoIngreso = fechaUltimoIngreso;
        this.fechaprimerIngreso = fechaprimerIngreso;
        this.status = status;
    }


    /**
     *
     * @param id
     * @param nombre
     * @param edad
     * @param sexo
     * @param fechaUltimoIngreso
     * @param fechaprimerIngreso
     * @param status
     * @param ejeFBTreeMap
     */
   public UsuarioFB(String id, String nombre, Integer edad, String sexo, String fechaUltimoIngreso, String fechaprimerIngreso, String status, TreeMap ejeFBTreeMap) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaUltimoIngreso = fechaUltimoIngreso;
        this.fechaprimerIngreso = fechaprimerIngreso;
        this.status = status;
        this.ejeFBTreeMap = ejeFBTreeMap;
    }


    public UsuarioFB(String id, String nombre, Integer edad, String sexo, String fechaUltimoIngreso, String fechaprimerIngreso, String status, TreeMap ejeFBTreeMap, List<EjeFB> ejesList) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaUltimoIngreso = fechaUltimoIngreso;
        this.fechaprimerIngreso = fechaprimerIngreso;
        this.status = status;
        this.ejeFBTreeMap = ejeFBTreeMap;
        this.ejesList = ejesList;
    }

    public String getId() {
        return id;
    }

}
