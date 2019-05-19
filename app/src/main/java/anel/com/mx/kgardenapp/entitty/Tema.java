package anel.com.mx.kgardenapp.entitty;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by  on 20/11/2018.
 */
@Entity
public class Tema {

    @PrimaryKey
    @NonNull
    public int id;
    @ColumnInfo(name ="descripcion")
    public String descripcion;
    @ColumnInfo(name ="id_eje")
    public int idEje;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEje() {
        return idEje;
    }

    public void setIdEje(int idEje) {
        this.idEje = idEje;
    }
}
