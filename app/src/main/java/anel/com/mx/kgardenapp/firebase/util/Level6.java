package anel.com.mx.kgardenapp.firebase.util;

import anel.com.mx.kgardenapp.Num3_5Activity;
import anel.com.mx.kgardenapp.Num3_5_0Activity;
import anel.com.mx.kgardenapp.Num3_5_1Activity;
import anel.com.mx.kgardenapp.Num3_7Activity;
import anel.com.mx.kgardenapp.Num3_7a_Activity;

/**
 * Created by Anel G.G.
 */

public enum Level6 {

        //habilidad comunicar los primeros 10 numeros
        PERCEPCION_0  (Num3_5Activity.class, Num3_5_0Activity.class, Num3_5_1Activity.class),  //calls constructor with value 3
        ATENCION_0 (Num3_7Activity.class, Num3_7a_Activity.class,String.class),  //calls constructor with value 2
        MEMORIA_0   (Num3_7Activity.class, Num3_7a_Activity.class,String.class);   //calls constructor with value 1

        //Escritura primeros 10 numeros
        //PERCEPCION_1  ("1","2","3"),  //calls constructor with value 3
        //ATENCION_1 ("1","2","3"),  //calls constructor with value 2
        //MEMORIA_1   ("1","2","3"),   //calls constructor with value 1


        //Compara Coleccionese en base a sus elementos
        //PERCEPCION_2  ("1","2","3"),
        //ATENCION_2 ("1","2","3"),
        //MEMORIA_2   ("1","2","3")
        //; // semicolon needed when fields / methods follow

        private final Class levelOne;
        private final Class levelTwo;
        private final Class levelThree;

        private Level6(Class levelOne, Class levelTwo, Class levelThree) {
            this.levelOne = levelOne;
            this.levelTwo = levelTwo;
            this.levelThree = levelThree;
        }

    }
