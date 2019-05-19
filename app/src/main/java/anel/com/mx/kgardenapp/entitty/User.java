package anel.com.mx.kgardenapp.entitty;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by  on 20/11/2018.
 */
@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name ="nombre")
    public String nombre;
    @ColumnInfo(name ="edad")
    public String edad;
    @ColumnInfo(name ="sexo")
    public String sexo;
    @ColumnInfo(name ="fechaUltimoIngreso")
    public String fechaUltimoIngreso;
    @ColumnInfo(name ="fechaPrimerIngreso")
    public String fechaPrimerIngreso;
    @ColumnInfo(name ="status")
    public String status;

}
