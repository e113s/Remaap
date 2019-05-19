package anel.com.mx.kgardenapp.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import anel.com.mx.kgardenapp.ResultadoActivity;
import anel.com.mx.kgardenapp.entitty.Actividad;
import anel.com.mx.kgardenapp.entitty.Eje;
import anel.com.mx.kgardenapp.entitty.Habilidad;
import anel.com.mx.kgardenapp.entitty.ResultadoActividad;
import anel.com.mx.kgardenapp.entitty.Tema;
import anel.com.mx.kgardenapp.entitty.User;
import static anel.com.mx.kgardenapp.dao.AppDatabase.DATABASE_VERSION;
/**
 * Created by  on 20/11/2018.
 */

@Database(entities = {User.class, Tema.class, ResultadoActividad.class, Habilidad.class, Eje.class, Actividad.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{


    public static final int DATABASE_VERSION=2;
    private static volatile AppDatabase INSTANCE;

    /**
     *
     * @return
     */
    public abstract UserDao userDao();

    /**
     * usado para guaradar los ejes con los que se esta trabajando como eje_numeros
     * @return
     */
    public abstract EjeDao ejeDao();

    /**
     * Tema u Objetivo con el que se trabajara como "action_comunicar_numeros"
     *
     * @return
     */
    public abstract TemaDao temaDao();

    /**
     * habilidades que se evaluan action_percepcion, action_atencion, action_memoria
     * @return
     */
    public abstract HabilidadDao habilidadDao();

    /**
     * diferentes actividades en el juego la referencia se encuentra en el firebase
     * @return
     */
    public abstract ActividadDao actividadDao();

    /**
     *
     * @return
     */
    public abstract ResultadoActividadDao resultadoActividadDao();

    /**
     *
     * @param context
     * @return
     */
    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"remap_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
