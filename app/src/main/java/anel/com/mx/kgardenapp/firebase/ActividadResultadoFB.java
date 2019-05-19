package anel.com.mx.kgardenapp.firebase;

/**
 * Created by
 */

public class ActividadResultadoFB {


    public String clasificacion;
    public String nivelComplejidad;
    public String numIntentoPredomiantes;
    public String numeroPregunta;
    public String aciertosTotales;
    public String fallosTotales;
    public String erroresContinuos;
    public String aciertosContinuos;
    public String duracionActividad;

    public  ActividadResultadoFB(){

    }

    public ActividadResultadoFB(String clasificacion, String nivelComplejidad, String numIntentoPredomiantes, String numeroPregunta, String aciertosTotales, String fallosTotales, String erroresContinuos, String aciertosContinuos, String duracionActividad) {
        this.clasificacion = clasificacion;
        this.nivelComplejidad = nivelComplejidad;
        this.numIntentoPredomiantes = numIntentoPredomiantes;
        this.numeroPregunta = numeroPregunta;
        this.aciertosTotales = aciertosTotales;
        this.fallosTotales = fallosTotales;
        this.erroresContinuos = erroresContinuos;
        this.aciertosContinuos = aciertosContinuos;
        this.duracionActividad = duracionActividad;
    }
}
