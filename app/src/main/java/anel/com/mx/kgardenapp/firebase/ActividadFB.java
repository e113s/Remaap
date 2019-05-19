package anel.com.mx.kgardenapp.firebase;

import java.util.List;
import java.util.TreeMap;

import anel.com.mx.kgardenapp.dto.Actividad;

/**
 * Created by Anel y Aberto
 */

public class ActividadFB {

    public Integer id;
    public String nombre;
    public Boolean status;
    public List actividadResultadoFBTreeMap;
    public String nextActivity;
    public String beforeActivity;

    public ActividadFB() {

    }

    public ActividadFB(Integer id, String nombre, Boolean status, List actividadResultadoFBTreeMap, String nextActivity, String beforeActivity) {
        this.id = id;
        this.nombre = nombre;
        this.status = status;
        this.actividadResultadoFBTreeMap = actividadResultadoFBTreeMap;
        this.nextActivity = nextActivity;
        this.beforeActivity = beforeActivity;
    }

    public ActividadFB(List actividadResultadoFBTreeMap) {
        this.actividadResultadoFBTreeMap = actividadResultadoFBTreeMap;
    }
}

