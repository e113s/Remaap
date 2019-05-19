package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.Actividad;
import anel.com.mx.kgardenapp.entitty.ResultadoActividad;

/**
 * Created by  on 27/11/2018.
 */
@Dao
public interface ResultadoActividadDao {

    @Query("SELECT * FROM resultadoactividad")
    List<ResultadoActividad> getAll();

    @Query("select * from resultadoactividad where id = :id")
    ResultadoActividad getResultadoActividadbyId(String id);

    @Query("delete from resultadoactividad where id= :id")
    void deleteResultadoActividadbyId(String id);

    @Insert
    void insertAll(ResultadoActividad... actividades);
    @Delete
    void delete(ResultadoActividad actividades);

}
