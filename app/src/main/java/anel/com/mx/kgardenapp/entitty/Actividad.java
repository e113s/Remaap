package anel.com.mx.kgardenapp.entitty;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 *
 */
@Entity
public class Actividad {

    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name ="nombre")
    public String nombre;
    @ColumnInfo(name ="status")
    public Boolean status;
    @ColumnInfo(name ="eje")
    public String eje;
    @ColumnInfo (name="tema")
    public String tema;
    @ColumnInfo (name="habilidad")
    public String habilidad;
    @ColumnInfo (name="complejidad")
    public String complejidad;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEje() {
        return eje;
    }

    public void setEje(String eje) {
        this.eje = eje;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }
}
