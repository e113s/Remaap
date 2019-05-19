package anel.com.mx.kgardenapp.entitty;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by  on 20/11/2018.
 */
@Entity
public class ResultadoActividad {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name="clasificacion")
    public String clasificacion;
    @ColumnInfo(name="nivelComplejidad")
    public String nivelComplejidad;
    @ColumnInfo(name="num_intentos_pred")
    public String numIntentoPredomiantes;
    @ColumnInfo(name="num_preguntas")
    public String numeroPregunta;
    @ColumnInfo(name="aciertos_totales")
    public String aciertosTotales;
    @ColumnInfo(name="fallos_totales")
    public String fallosTotales;
    @ColumnInfo(name="errores_continuos")
    public String erroresContinuos;
    @ColumnInfo(name="aciertos_continuos")
    public String aciertosContinuos;
    @ColumnInfo(name="duracion_actividad")
    public String duracionActividad;

    @ColumnInfo(name="porcentaje_exito")
    public String porcentajeExito;

    @ColumnInfo(name = "id_actividad")
    public String idActividad;



    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(String nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public String getNumIntentoPredomiantes() {
        return numIntentoPredomiantes;
    }

    public void setNumIntentoPredomiantes(String numIntentoPredomiantes) {
        this.numIntentoPredomiantes = numIntentoPredomiantes;
    }

    public String getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(String numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public String getAciertosTotales() {
        return aciertosTotales;
    }

    public void setAciertosTotales(String aciertosTotales) {
        this.aciertosTotales = aciertosTotales;
    }

    public String getFallosTotales() {
        return fallosTotales;
    }

    public void setFallosTotales(String fallosTotales) {
        this.fallosTotales = fallosTotales;
    }

    public String getErroresContinuos() {
        return erroresContinuos;
    }

    public void setErroresContinuos(String erroresContinuos) {
        this.erroresContinuos = erroresContinuos;
    }

    public String getAciertosContinuos() {
        return aciertosContinuos;
    }

    public void setAciertosContinuos(String aciertosContinuos) {
        this.aciertosContinuos = aciertosContinuos;
    }

    public String getDuracionActividad() {
        return duracionActividad;
    }

    public void setDuracionActividad(String duracionActividad) {
        this.duracionActividad = duracionActividad;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getPorcentajeExito() {
        return porcentajeExito;
    }

    public void setPorcentajeExito(String porcentajeExito) {
        this.porcentajeExito = porcentajeExito;
    }
}
