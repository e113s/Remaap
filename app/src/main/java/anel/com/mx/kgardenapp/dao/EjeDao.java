package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.Eje;
import anel.com.mx.kgardenapp.entitty.User;

/**
 * Created by  on 27/11/2018.
 */

@Dao
public interface EjeDao {

    @Query("SELECT * FROM eje")
    List<Eje> getAll();

    @Query("select * from eje where id = :id")
    Eje getEjebyId(String id);

    @Query("delete from eje where id= :id")
    void deleteEjebyId(String id);

    @Insert
    void insertAll(Eje... ejes);
    @Delete
    void delete(Eje eje);

}
