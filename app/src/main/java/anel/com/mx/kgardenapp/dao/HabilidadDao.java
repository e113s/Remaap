package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.Eje;
import anel.com.mx.kgardenapp.entitty.Habilidad;
import anel.com.mx.kgardenapp.entitty.Tema;

/**
 * Created by  on 27/11/2018.
 */
@Dao
public interface HabilidadDao {


    @Query("SELECT * FROM habilidad")
    List<Habilidad> getAll();

    @Query("select * from habilidad where id = :id")
    Habilidad getHabilidadbyId(String id);

    @Query("delete from habilidad where id= :id")
    void deleteHabilidadbyId(String id);

    @Insert
    void insertAll(Habilidad... users);
    @Delete
    void delete(Habilidad user);

}
