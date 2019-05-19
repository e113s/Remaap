package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import anel.com.mx.kgardenapp.entitty.User;

/**
 * Created by on 20/11/2018.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("select * from user where id = :id")
    User getUserbyId(String id);

    @Query("delete from user where id= :id")
    void deleteUserbyId(String id);

    @Insert
    void insertAll(User... users);
    @Delete
    void delete(User user);
}
