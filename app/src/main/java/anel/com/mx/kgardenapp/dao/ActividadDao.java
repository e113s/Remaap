package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.Actividad;
import anel.com.mx.kgardenapp.entitty.Tema;

/**
 * Created by ddarredondo on 27/11/2018.
 */
@Dao
public interface ActividadDao {


    @Query("SELECT * FROM actividad")
    List<Actividad> getAll();

    @Query("select * from actividad where id = :id")
    Actividad getActividadbyId(String id);

    @Query("delete from actividad where id= :id")
    void deleteActividadbyId(String id);

    @Query("update actividad set status = :status where id= :id")
    void updateActividadById(String id, boolean status);

    @Insert
    void insertAll(Actividad... actividades);
    @Delete
    void delete(Actividad actividades);
}
