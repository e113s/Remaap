package anel.com.mx.kgardenapp.entitty;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by  on 20/11/2018.
 */
@Entity
public class Habilidad {

    @PrimaryKey
    @NonNull
    public int id;
    @ColumnInfo(name="descripcion")
    public String descripcion;




}
