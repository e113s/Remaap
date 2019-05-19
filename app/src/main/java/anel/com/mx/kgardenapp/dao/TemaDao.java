package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.Eje;
import anel.com.mx.kgardenapp.entitty.Tema;

/**
 * Created by ddarredondo on 27/11/2018.
 */

@Dao
public interface TemaDao {


    @Query("SELECT * FROM tema")
    List<Tema> getAll();

    @Query("select * from tema where id = :id")
    Tema getTemabyId(String id);

    @Query("delete from tema where id= :id")
    void deleteTemabyId(String id);

    @Insert
    void insertAll(Tema... temas);
    @Delete
    void delete(Tema temas);

}
