package anel.com.mx.kgardenapp.firebase.util;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import anel.com.mx.kgardenapp.Num3_1Activity;
import anel.com.mx.kgardenapp.Num3_2Activity;
import anel.com.mx.kgardenapp.Num3_5Activity;
import anel.com.mx.kgardenapp.Num3_5_0Activity;
import anel.com.mx.kgardenapp.Num3_5_1Activity;
import anel.com.mx.kgardenapp.Num3_7Activity;
import anel.com.mx.kgardenapp.Num_3_3Activity;
import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.firebase.ActividadResultadoFB;

/**
 * Created by Anel G.G.
 */

public final class Util {

    Set<Class> edadPathList = new HashSet<>();

    Level3 level3;
    Level3 level4;
    Level3 level5;
    Level3 level6;
    Level3 level7;
    Level3 level8;


    public TreeMap<String, TreeMap> getPathGame(Integer edad) {
        TreeMap<String, TreeMap> ejeTreeMap = new TreeMap<>();

        Enum<?> levelEnum = null;
        switch (edad) {

            case 3:
                levelEnum = (Level3) level3;


            case 4:
                levelEnum = level4;
            case 5:
                levelEnum = level5;
            case 6:
                levelEnum = level6;
            case 7:
                levelEnum = level7;
            case 8:
                levelEnum = level8;
        }


        //action_comunicar_numeros
        TreeMap<String, TreeMap> habilidadesMap = new TreeMap<>();
        //action_percepcion
        TreeMap<String, String> actividadesMap = new TreeMap<>();

        TreeMap<String, ActividadResultadoFB> actividadesResultadoMap = new TreeMap<>();

        TreeMap<String, TreeMap> temasTreeMap = new TreeMap();

        for (Level3 l3 : Level3.values()) {

            if (l3.name() == Level3.PERCEPCION_0.name()) {

                actividadesMap.put("1", String.valueOf(l3.levelOne));
                actividadesMap.put("2", String.valueOf(l3.levelTwo));
                actividadesMap.put("3", String.valueOf(l3.levelThree));

                habilidadesMap.put(String.valueOf(R.string.action_percepcion), actividadesMap);
            }
            if (l3.name() == Level3.ATENCION_0.name()) {
                actividadesMap = new TreeMap<>();
                actividadesMap.put("1", String.valueOf(l3.levelOne));
                actividadesMap.put("2", String.valueOf(l3.levelTwo));
                actividadesMap.put("3", String.valueOf(l3.levelThree));
                habilidadesMap.put(String.valueOf(R.string.action_atencion), actividadesMap);
            }
            if (l3.name() == Level3.MEMORIA_0.name()) {
                actividadesMap = new TreeMap<>();
                actividadesMap.put("1", String.valueOf(l3.levelOne));
                actividadesMap.put("2", String.valueOf(l3.levelTwo));
                actividadesMap.put("3", String.valueOf(l3.levelThree));
                habilidadesMap.put(String.valueOf(R.string.action_memoria), actividadesMap);
            }

            //tema comunicar numeros

            //inserto habilidades en temas map comucnar unmeros
            //temasTreeMap.put(String.valueOf(R.string.action_comunicar_numeros),habilidadesMap);
            temasTreeMap.put(String.valueOf(R.string.action_comunicar_numeros), habilidadesMap);

        }


            //se inserta en ejes
            ejeTreeMap.put(String.valueOf(R.string.eje_numeros), temasTreeMap);

            //ejeTreeMap.put(R.string.eje_figuras_geometricas,temasTreeMap);
            //ejeTreeMap.put(R.string.eje_magintudes_medidas,temasTreeMap);

        return ejeTreeMap;
    }



}
