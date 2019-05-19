package anel.com.mx.kgardenapp;

import android.app.Dialog;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import anel.com.mx.kgardenapp.dao.AppDatabase;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.entitty.ResultadoActividad;
import anel.com.mx.kgardenapp.entitty.User;
import anel.com.mx.kgardenapp.firebase.ActividadFB;
import anel.com.mx.kgardenapp.firebase.ActividadResultadoFB;
import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.firebase.util.Eje;
import anel.com.mx.kgardenapp.firebase.util.Habilidad;
import anel.com.mx.kgardenapp.firebase.util.Tema;
import anel.com.mx.kgardenapp.impl.DrawingActivity;
import anel.com.mx.kgardenapp.impl.Game;
import anel.com.mx.kgardenapp.impl.IGame;

/**
 * Created by ddarredondo on 08/08/2018.
 * clase creada para poder
 */

public class NumParentActivity extends AppCompatActivity {


    private Actividad actividad;
    public MediaPlayer mediaPlayer;
    private Dialog mensaje;
    private int [] numbersResource={R.raw.cero,R.raw.uno,R.raw.dos,R.raw.tres,R.raw.cuatro,R.raw.cinco,R.raw.seis,R.raw.siete,R.raw.ocho,R.raw.nueve,R.raw.diez};
    private SharedPreferences preferences ;
    private UsuarioFB user;

    static  FirebaseDatabase database;
    private DatabaseReference myRef;

    public AppDatabase mDb;

    public Boolean estatus= false;

    public HashMap<Integer, String> ejesMap = new HashMap<>();
    public HashMap<Integer, String> temaMapa = new HashMap<>();
    public HashMap<Integer, String> objetivoMap = new HashMap<>();
    public HashMap<String,String> actividadesMap = new HashMap<>();

    public int MENU_PRINCIPAL =1;
    public int SALIR_APP =0;
    /**
     *mapas para armar el camino
     */
    TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, List>>>> ejes = new TreeMap<>();
    TreeMap<String, TreeMap<String, TreeMap<String, List>>> temaMap = new TreeMap<>();
    TreeMap<String, TreeMap<String, List>> habilidad = new TreeMap<>();
    TreeMap<String,List> mapaActividad = new TreeMap<>();

    /**
     * obtiene la conexion
     */
    public void getConnection(){


        if(database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);

        }


        

    }

    public Actividad getInformation() {

        user= (UsuarioFB) getIntent().getSerializableExtra("usuarioFB");


        Bundle data = getIntent().getExtras();

        actividad = (Actividad) data.getParcelable("actividad");
        //Toast.makeText(getApplicationContext(), "la pregunta es la numero " + actividad.getNumeroPregunta(), Toast.LENGTH_SHORT).show();

        int[][] arrayReceived = null;
        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("numeros");
        if (objectArray != null) {
            arrayReceived = new int[objectArray.length][];
            for (int i = 0; i < objectArray.length; i++) {
                arrayReceived[i] = (int[]) objectArray[i];
            }
        }
        actividad.setNumeros(arrayReceived);

        ArrayList<Integer> numerosExluidos = data.getIntegerArrayList("numExcluidosList");
        Set<Integer> numEcxluidosList = new android.support.v4.util.ArraySet<>();
        for (Integer num:numerosExluidos
             ) {
            numEcxluidosList.add(num);
        }
        actividad.setNumEcxluidosList(numEcxluidosList);



        return actividad;
    }


    public void llamaVideo() {

        Intent intent = new Intent(this, VideoNumeros.class);

        intent.putExtra("nombre", "Otro nivel");
        startActivity(intent);
    }


    /**
     * Llama a otro nivel del juego
     * @param actividad tiene la informacion y progreso del jugador
     * @param claseDestino el destino del juego al que ira
     * @param progresoJuego tiene to-do el road map del juego
     */
    //super.getActividad(),Num3_5_0Activity.class,gameNumerosSimples.getNumeros(),gameNumerosSimples.getAciertosTotales(),gameNumerosSimples.getFallosTotales(),gameNumerosSimples.getNumEcxluidosList()

    /**
     * @param actividad        tiene la informacion y progreso del jugador
     * @param claseDestino     el destino del juego al que ira
     * @param progresoJuego    tiene to-do el road map del juego, it has the whole game path
     * @param aciertosTotales  aciertos totales del juego
     * @param fallosTotales    fallos totales del juego
     * @param numExcluidosList numeros que fueron seleccionados correctamente
     * @param usuarioFB  objeto que contiene toda la ruta del jugador
     */
    public void llamaOtroNivel(Actividad actividad, Class claseOrigen, Class claseDestino, int[][] progresoJuego, int aciertosTotales, int fallosTotales, Set<Integer> numExcluidosList, int numeroPregunta, UsuarioFB usuarioFB ) {
        // llamaResultado();
        // Toast.makeText(getApplicationContext(),"llegaste a otro nivel",Toast.LENGTH_LONG).show();


        actividad.setNumeros(progresoJuego);
        actividad.setAciertosTotales(aciertosTotales);
         //Toast.makeText(getApplicationContext(),"aciertosTotales"+aciertosTotales,Toast.LENGTH_LONG).show();
        actividad.setFallosTotales(fallosTotales);
        actividad.setNumeroPregunta(numeroPregunta);
        //Toast.makeText(getApplicationContext(),"numeroPregunta"+numeroPregunta,Toast.LENGTH_LONG).show();
        actividad.setNumEcxluidosList(numExcluidosList);

        Intent intent = new Intent(getApplicationContext(), claseDestino);
        intent.putExtra("nombre", "Otro nivel");
        intent.putExtra("actividad", actividad);

        intent.putExtra("usuarioFB", usuarioFB);
        Bundle bundle = new Bundle();
        bundle.putSerializable("numeros", progresoJuego);
        bundle.putIntegerArrayList("numExcluidosList",new ArrayList<Integer>(numExcluidosList));

        //bundle.putSerializable("numExcluidosList,numExcluidosList);
        //guarda los datos de la actividad

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    /**
     * llama un mensaje emergente
     *
     * @param textoMensaje es el texto que se mostrara en la ventana emergente
     * @param imagen       la imagen a mostrar , se recomienda el tamaño 516X587 maximo
     */
    public void llamarPopup(String textoMensaje, int imagen) {

        mensaje = new Dialog(this);
        mensaje.setContentView(R.layout.custompopup);
        TextView textView = mensaje.findViewById(R.id.textoMensaje);
        ImageView imageView = mensaje.findViewById(R.id.imagenMensaje);
        textView.setText(textoMensaje);
        imageView.setBackgroundResource(imagen);
        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mensaje.show();

    }

    public void closeWindow(View view){


        mensaje.hide();

    }


    /**
     *
     * @param actividad
     * @param tFinal
     * @param tInicio
     * @param claseDestino clase a la que debe ir despues de pintar los resaultados
     * @param progresoJuego
     * @param aciertosTotales
     * @param fallosTotales
     * @param numExcluidosList
     * @param numeroPregunta
     */
    public void llamarResultadoPopup(final Actividad actividad, long tFinal, long tInicio, final Class claseDestino, final int[][] progresoJuego, final int aciertosTotales, final int fallosTotales, final Set<Integer> numExcluidosList, final int numeroPregunta) {


        long tiempoFinal = System.currentTimeMillis();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView= inflater.inflate(R.layout.custom_results_popup, null);
        builder.setView(dialogView);


// Add the buttons


        TextView aciertosTextView =dialogView.findViewById(R.id.textNumAciertosId);
        aciertosTextView.setText(String.valueOf(aciertosTotales));

        ProgressBar progressBarAciertos =dialogView.findViewById(R.id.progressBarAciertos);

        //progressBarAciertos.setProgress(aciertosTotales,true);
        progressBarAciertos.setProgress(aciertosTotales);


        //fallas

        TextView fallasTextView =dialogView.findViewById(R.id.textNumFallasId);
        fallasTextView.setText(String.valueOf(fallosTotales));


        ProgressBar progressBarFallos =dialogView.findViewById(R.id.progressBarFallas);

        progressBarFallos.setProgress(fallosTotales);


        //preguntas

        TextView preguntasTextView =dialogView.findViewById(R.id.textNumPreguntasId);
        preguntasTextView.setText(String.valueOf(numeroPregunta));


        ProgressBar progressBarPreguntas =dialogView.findViewById(R.id.progressBarPreguntas);

        //progressBarAciertos.setProgress(aciertosTotales,true);
        progressBarPreguntas.setProgress(numeroPregunta);

        Chronometer simpleChronometer = (Chronometer)  dialogView.findViewById(R.id.crono);

        String tiempo = String.valueOf((tiempoFinal-tInicio)/1000).concat(" segundos");
        simpleChronometer.setText(tiempo);
        //simpleChronometer.setBase((tiempoFinal-tInicio)/1000);
        //simpleChronometer.start();
        //simpleChronometer.stop();

        //barra progreso

        float  exito =((Float.valueOf(aciertosTotales) - Float.valueOf(fallosTotales))/Float.valueOf(aciertosTotales))*10;
        DecimalFormat df = new DecimalFormat("#.00");

        TextView acertividadTextView =dialogView.findViewById(R.id.textAcertividadId);
        acertividadTextView.setText(String.valueOf(df.format(exito)));

        RatingBar ratingBar= dialogView.findViewById(R.id.ratingBarId);

        ratingBar.setNumStars((int) exito);

// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();




/*
        long tiempoFinal = System.currentTimeMillis();

        mensaje = new Dialog(this);
        mensaje.setContentView(R.layout.custom_results_popup);


//aciertos
        TextView aciertosTextView =mensaje.findViewById(R.id.textNumAciertosId);
        aciertosTextView.setText(String.valueOf(aciertosTotales));

        ProgressBar progressBarAciertos =mensaje.findViewById(R.id.progressBarAciertos);

        //progressBarAciertos.setProgress(aciertosTotales,true);
        progressBarAciertos.setProgress(aciertosTotales);


        //fallas

        TextView fallasTextView =mensaje.findViewById(R.id.textNumFallasId);
        fallasTextView.setText(String.valueOf(fallosTotales));


        ProgressBar progressBarFallos =mensaje.findViewById(R.id.progressBarFallas);

        progressBarFallos.setProgress(fallosTotales);


        //preguntas

        TextView preguntasTextView =mensaje.findViewById(R.id.textNumPreguntasId);
        preguntasTextView.setText(String.valueOf(numeroPregunta));


        ProgressBar progressBarPreguntas =mensaje.findViewById(R.id.progressBarPreguntas);

        //progressBarAciertos.setProgress(aciertosTotales,true);
        progressBarPreguntas.setProgress(numeroPregunta);

        Chronometer simpleChronometer = (Chronometer)  mensaje.findViewById(R.id.crono);

        String tiempo = String.valueOf((tiempoFinal-tInicio)/1000).concat(" segundos");
        simpleChronometer.setText(tiempo);
        //simpleChronometer.setBase((tiempoFinal-tInicio)/1000);
        //simpleChronometer.start();
        //simpleChronometer.stop();

        //barra progreso

        float  exito =((Float.valueOf(aciertosTotales) - Float.valueOf(fallosTotales))/Float.valueOf(aciertosTotales))*10;
        DecimalFormat df = new DecimalFormat("#.00");

        TextView acertividadTextView =mensaje.findViewById(R.id.textAcertividadId);
        acertividadTextView.setText(String.valueOf(df.format(exito)));

        RatingBar ratingBar= mensaje.findViewById(R.id.ratingBarId);

        ratingBar.setNumStars((int) exito);

        mensaje.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mensaje.getWindow().makeActive();
        mensaje.show();
*/



    }

    public void llamaResultado(final Actividad actividad, long tFinal, long tInicio, final Class claseDestino, final int[][] progresoJuego, final int aciertosTotales, final int fallosTotales, final Set<Integer> numExcluidosList, final int numeroPregunta) {


        //Num3_7a_Activity.class
        tFinal = System.currentTimeMillis();
        long tDuracion = (tFinal - tInicio) / 1000;

        final AlertDialog.Builder myResult = new AlertDialog.Builder(this);
        myResult.setMessage("Hola:   " + "* * *       " + actividad.getNombre() + "       * * *" + "\n" +
                "Aciertos = " + aciertosTotales + "\n" +
                "Fallas =" + fallosTotales + "\n" +
                "Número de preguntas =" + numeroPregunta + "\n" +
                "Tiempo= " + tDuracion + "segundos" + "\n" +
                "Acertivicidad =" + "Bien");


        myResult.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                actividad.setNumeros(progresoJuego);
                actividad.setAciertosTotales(aciertosTotales);
                actividad.setFallosTotales(fallosTotales);
                actividad.setNumEcxluidosList(numExcluidosList);

                Intent intent = new Intent(getApplicationContext(), claseDestino);

                intent.putExtra("actividad", actividad);

                Bundle bundle = new Bundle();
                bundle.putSerializable("numeros", progresoJuego);
                bundle.putIntegerArrayList("numExcluidosList",new ArrayList<Integer>(numExcluidosList));
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = myResult.create();
        dialog.show();
    }


    public Actividad getActividad() {
        return actividad;
    }


    public void setData() {

    }


    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void getSiguienteNivel(String edad, String sexo, String nombre) {

        switch (Integer.valueOf(edad)) {
            case 3:

        }


    }


    public boolean playAudio(final int scriptToPlay, final int sleepTime) {

        boolean estado = true;
        // agrega auido
        Thread thread = new Thread() {

            public void run() {
                try {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), scriptToPlay);
                    mediaPlayer.start();
                    sleep(sleepTime);
                    mediaPlayer.release();
                    // finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();





        return estado;
    }



    public boolean playAudioNumbers(final int scriptToPlay) {

        boolean estado = true;


        // agrega auido
        Thread thread = new Thread() {

            public void run() {
                try {
                     int sleepTime =1000;
                    int script=0;
                    if (scriptToPlay == 0) {
                        script= R.raw.cero;
                    }else if (scriptToPlay == 1) {
                        script= R.raw.uno;
                    }else if (scriptToPlay == 2) {
                        script= R.raw.dos;
                    }else if (scriptToPlay == 3) {
                        script= R.raw.tres;
                    }else if (scriptToPlay == 4) {
                        script= R.raw.cuatro;
                    }else if (scriptToPlay == 5) {
                        script= R.raw.cinco;
                    }else if (scriptToPlay == 6) {
                        script= R.raw.seis;
                    }else if (scriptToPlay == 7) {
                        script= R.raw.siete;
                    }else if (scriptToPlay == 8) {
                        script= R.raw.ocho;
                    }else if (scriptToPlay == 9) {
                        script= R.raw.nueve;
                        sleepTime =1200;
                    }else if (scriptToPlay == 10) {
                        script= R.raw.diez;
                        sleepTime =1200;
                    }
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), script);
                    mediaPlayer.start();
                    sleep(sleepTime);
                    mediaPlayer.release();
                    // finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        return estado;
    }


    /**
     *
     * @return un nuevo usuario para el dispositivo.
     *         A new user for this device
     */
    public String getUIDUsuario(){

        //obtengo las preferencias del usuario
        preferences = getApplication().getSharedPreferences("palyerPrefs",0);
        //obtengo el user UID
        String userUID = preferences.getString("userUID",null);

        //si el userUID no existe creo uno nuevo de lo contrario regreso el existente
        //si el usuario borra el cache del juego volvera a generar uno nuevo y por tanto su registro nuevamente dentro de la app
        if(userUID == null) {
            //creo un nueva fecha
            Date date = new Date();
            //obtengo la fecha en milisegundos con gettime y la transformo a String
            String stringDate = String.valueOf(date.getTime());
            //genero un random
            Random random = new Random();
            //el random generado 10000 es multiplicado por tres
            stringDate = stringDate + String.valueOf(random.nextInt(10000)*3);
            userUID = stringDate;
            preferences.edit().putString("userUID", userUID).commit();
            //Toast.makeText(this, "la llave userUID es  ::::::::::"+userUID, Toast.LENGTH_LONG).show();
        }
        return userUID;

    }


    public boolean isUserExist(){

        boolean existe= false;
        preferences = getApplication().getSharedPreferences("palyerPrefs",0);
        String userUID = preferences.getString("userUID",null);
        if(userUID != null) {
             existe = true;
            //Toast.makeText(this, "la llave userUID es  ::::::::::"+userUID, Toast.LENGTH_LONG).show();
        }
        return existe;

    }

    /**
     *
     *
     * @return un objeto UsuarioFb con to-do el progreso del juego
     */
    public Class getActivity(){

        //nos posiciona en usuarios

         myRef = database.getReference("users").child(getUIDUsuario());
        myRef.keepSynced(true);

        Query query =myRef.orderByChild(getUIDUsuario());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds: dataSnapshot.child("ejeFBTreeMap").child("NUMEROS").child("action_comunicar_numeros").child("ATENCION").getChildren()){

                    for(DataSnapshot ds2: ds.getChildren()){
                        ds2.getKey();

                    }

                }



                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return null;
    }

    /**
     *
     * @param edad
     * @param nombre
     * @param sexo
     * @return un objeto de tipo usuario fire base que contiene la información y progreso del juego
     */
    public UsuarioFB creaPerfil(final Integer edad,final String nombre , final String sexo){

         myRef = database.getReference("users");


        String fechaInicial =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        user = new UsuarioFB(getUIDUsuario(),nombre,edad,sexo,fechaInicial,fechaInicial,"activo");
        myRef.child(user.getId()).setValue(user);

        preferences = getApplication().getSharedPreferences("palyerPrefs",0);
        preferences.edit().putString("edad", String.valueOf(edad)).commit();
        preferences.edit().putString("sexo", sexo).commit();
        preferences.edit().putString("fechaUltimoAcceso", fechaInicial).commit();


        //acceso a firebase

        myRef = database.getReference("ejes");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                List<ActividadResultadoFB> lista = new ArrayList();


                //ds.child("numeros").child("action_comunicar_numeros").child("action_atencion").child("3").getChildrenCount()
                // se pone la constante 3 por que el juego solo esta manejando tres juegos
                for (DataSnapshot ds: dataSnapshot.child("numeros").child("action_comunicar_numeros").child("action_atencion").child(String.valueOf(3)).getChildren()){

                    lista = new ArrayList();
                    for(DataSnapshot ds2: ds.child("resultado").getChildren()){
                        lista.add(ds2.getValue(ActividadResultadoFB.class));
                        //lista.add(new ActividadResultadoFB());
                    }

                    mapaActividad.put(ds.getKey(),lista );

                }
                habilidad.put(Habilidad.ATENCION.name(),mapaActividad);


                mapaActividad = new TreeMap<>();
                for (DataSnapshot ds: dataSnapshot.child("numeros").child("action_comunicar_numeros").child("action_memoria").child(String.valueOf(3)).getChildren()){

                    lista = new ArrayList();
                    for(DataSnapshot ds2: ds.child("resultado").getChildren()){
                        lista.add(ds2.getValue(ActividadResultadoFB.class));
                        //lista.add(new ActividadResultadoFB());
                    }

                    mapaActividad.put(ds.getKey(),lista );

                }
                habilidad.put(Habilidad.MEMORIA.name(),mapaActividad);



                mapaActividad = new TreeMap<>();
                for (DataSnapshot ds: dataSnapshot.child("numeros").child("action_comunicar_numeros").child("action_percepcion").child(String.valueOf(3)).getChildren()){

                    lista = new ArrayList();
                    for(DataSnapshot ds2: ds.child("resultado").getChildren()){
                        lista.add(ds2.getValue(ActividadResultadoFB.class));
                        //lista.add(new ActividadResultadoFB());
                    }

                    mapaActividad.put(ds.getKey(),lista );

                }
                habilidad.put(Habilidad.PERCEPCION.name(),mapaActividad);




                temaMap.put(Tema.action_comunicar_numeros.name(),habilidad);
                ejes.put(Eje.NUMEROS.name(),temaMap);


                 myRef = database.getReference("users");
                String fechaInicial =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                user = new UsuarioFB(getUIDUsuario(),nombre,edad,sexo,fechaInicial,fechaInicial,"activo",ejes);
                user.ejeFBTreeMap=ejes;
                myRef.child(user.getId()).setValue(user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //cuando cambie el valor deberia de llamar el metodo onDataChange

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return user;
    }


    public UsuarioFB getUser() {
        return user;
    }

    public void setUser(UsuarioFB user) {
        this.user = user;
    }

    public Class getNextActividad(){

        return null;
    }


    public void guardaResultadoActividadbyUser(){


    }

    /**
     * metodo usado para llenar la base de datos con to-dos los temas relacionados con el juego, las combinaciones son
     *
     * EJEs     -->TEMAS                   -->Habilidad      -->edad-->Actividad--> ResultadoActividad
     * numeros --->action_comunicar_numeros-->action_atencion-->3   -->A001     -->Resultado
     */
    public  void populateDataBase(final UsuarioFB usuarioFB){


        llenaMapaDatos();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Insert Data
                mDb = AppDatabase.getDatabase(getApplicationContext());

                anel.com.mx.kgardenapp.entitty.Eje eje1 = new anel.com.mx.kgardenapp.entitty.Eje();
                eje1.setId(R.string.eje_numeros);
                eje1.setDescripcion(ejesMap.get(R.string.eje_numeros));
                mDb.ejeDao().insertAll(eje1);

                eje1 = new anel.com.mx.kgardenapp.entitty.Eje();
                eje1.setId(R.string.eje_figuras_geometricas);
                eje1.setDescripcion(ejesMap.get(R.string.eje_figuras_geometricas));
                mDb.ejeDao().insertAll(eje1);


                 eje1 = new anel.com.mx.kgardenapp.entitty.Eje();
                eje1.setId(R.string.eje_magintudes_medidas);
                eje1.setDescripcion(ejesMap.get(R.string.eje_magintudes_medidas));
                mDb.ejeDao().insertAll(eje1);

                //inserta tema
                anel.com.mx.kgardenapp.entitty.Tema tema = new anel.com.mx.kgardenapp.entitty.Tema();
                tema.setId(R.string.action_comunicar_numeros);
                tema.setDescripcion(temaMapa.get(R.string.action_comunicar_numeros));
                tema.setIdEje(R.string.eje_numeros);
                mDb.temaDao().insertAll(tema);




                anel.com.mx.kgardenapp.entitty.Actividad actividad = new anel.com.mx.kgardenapp.entitty.Actividad();
                actividad.setId("A001");
                actividad.setNombre("Num3_5Activity.class");
                actividad.setStatus(false);
                actividad.setComplejidad("0");
                actividad.setEje("NUMEROS");
                actividad.setTema("action_comunicar_numeros");
                actividad.setHabilidad("PERCEPCION");
                mDb.actividadDao().insertAll(actividad);

                 actividad = new anel.com.mx.kgardenapp.entitty.Actividad();
                actividad.setId("A002");
                actividad.setNombre("Num3_5_0Activity.class");
                actividad.setStatus(false);
                actividad.setComplejidad("1");
                actividad.setEje("NUMEROS");
                actividad.setTema("action_comunicar_numeros");
                actividad.setHabilidad("PERCEPCION");
                mDb.actividadDao().insertAll(actividad);

                actividad = new anel.com.mx.kgardenapp.entitty.Actividad();
                actividad.setId("A003");
                actividad.setNombre("Num3_5_1Activity.class");
                actividad.setStatus(false);
                actividad.setComplejidad("2");
                actividad.setEje("NUMEROS");
                actividad.setTema("action_comunicar_numeros");
                actividad.setHabilidad("PERCEPCION");
                mDb.actividadDao().insertAll(actividad);

                actividad = new anel.com.mx.kgardenapp.entitty.Actividad();
                actividad.setId("A004");
                actividad.setNombre("Num3_1Activity.class");
                actividad.setStatus(false);
                actividad.setComplejidad("0");
                actividad.setEje("NUMEROS");
                actividad.setTema("action_comunicar_numeros");
                actividad.setHabilidad("ATENCION");
                mDb.actividadDao().insertAll(actividad);


                actividad = new anel.com.mx.kgardenapp.entitty.Actividad();
                actividad.setId("A005");
                actividad.setNombre("Num3_7a_Activity.class");
                actividad.setStatus(false);
                actividad.setComplejidad("0");
                actividad.setEje("NUMEROS");
                actividad.setTema("action_comunicar_numeros");
                actividad.setHabilidad("MEMORIA");
                mDb.actividadDao().insertAll(actividad);

            }
        });




    }


    public void llenaMapaDatos(){



        ejesMap.put(R.string.eje_numeros,getResources().getString(R.string.eje_numeros));


        temaMapa.put(R.string.action_comunicar_numeros,getResources().getString(R.string.action_comunicar_numeros));


        objetivoMap.put(R.string.action_atencion,getResources().getString(R.string.action_atencion));
        objetivoMap.put(R.string.action_percepcion,getResources().getString(R.string.action_percepcion));
        objetivoMap.put(R.string.action_memoria,getResources().getString(R.string.action_memoria));


        actividadesMap.put("Num3_5Activity","A001");
        actividadesMap.put("Num3_5_0Activity","A002");
        actividadesMap.put("Num3_5_1Activity","A003");
        actividadesMap.put("Num3_1Activity","A004");
        actividadesMap.put("Num3_7a_Activity","A005");



    }

    public void guardaResultadoActividad(final Actividad actividad, long tFinal, final long tInicio, final Class claseDestino, final int[][] progresoJuego, final int aciertosTotales, final int fallosTotales, final Set<Integer> numExcluidosList, final int numeroPregunta, final int aciertosContinuos,final int fallosContinuos,final Class actividadClass){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Insert Data
                mDb = AppDatabase.getDatabase(getApplicationContext());
                long tiempoFinal = System.currentTimeMillis();

                llenaMapaDatos();

                anel.com.mx.kgardenapp.entitty.Actividad actividad1 =mDb.actividadDao().getActividadbyId(actividadesMap.get(actividadClass.getSimpleName()));

                ResultadoActividad resultadoActividad = new ResultadoActividad();
                resultadoActividad.setIdActividad(actividad1.getId());
                resultadoActividad.setAciertosTotales(String.valueOf(aciertosTotales));
                resultadoActividad.setAciertosContinuos(String.valueOf(aciertosContinuos));
                resultadoActividad.setErroresContinuos(String.valueOf(actividad.getErroresContinuos()));
                resultadoActividad.setFallosTotales(String.valueOf(fallosTotales));

                String tiempo = String.valueOf((tiempoFinal-tInicio)/1000).concat(" segundos");
                resultadoActividad.setDuracionActividad(String.valueOf(tiempo));
                resultadoActividad.setNumeroPregunta(String.valueOf(numeroPregunta));

                float  exito =((Float.valueOf(aciertosTotales) - Float.valueOf(fallosTotales))/Float.valueOf(aciertosTotales))*10;
                DecimalFormat df = new DecimalFormat("#.00");

                resultadoActividad.setPorcentajeExito(String.valueOf(df.format(exito)));
                mDb.resultadoActividadDao().insertAll(resultadoActividad);


                //List<ResultadoActividad> resultadoActividadList = new ArrayList<ResultadoActividad>();
                //resultadoActividadList.add(resultadoActividad);


                myRef = database.getReference("users");
                myRef.child(getUIDUsuario()).child("ejeFBTreeMap").child(actividad1.getEje()).
                        child(actividad1.getTema()).child(actividad1.getHabilidad()).child(actividad1.getId()).setValue(mDb.resultadoActividadDao().getAll());


            }
        });
    }

    public void actualizaResultadoActividad(final Actividad actividad,final Class actividadClass){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Insert Data
                mDb = AppDatabase.getDatabase(getApplicationContext());

                llenaMapaDatos();

                mDb.actividadDao().updateActividadById(actividadesMap.get(actividadClass.getSimpleName()),true);

            }
        });
    }


    /**
     *
     * @param actividadClass
     * @return
     */
    public boolean getEstatusActividad(final Class actividadClass){


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Insert Data
                mDb = AppDatabase.getDatabase(getApplicationContext());

                llenaMapaDatos();

                anel.com.mx.kgardenapp.entitty.Actividad actividad1=  mDb.actividadDao().getActividadbyId(actividadesMap.get(actividadClass.getSimpleName()));
                if( actividad1 !=null){
                    estatus= actividad1.getStatus();
                }

            }
        });

        return estatus;
    }



    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    /**
     *
     * @param nivel si es nivel 1 regresa al menu principal
     *              si nivel 0  sale de la aplicacion sin guardar los datos
     */
    public void backPrincipalMenu(final int nivel){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.title_exit))
                .setMessage(getResources().getString(R.string.message_exit))
                //.setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        if(nivel == MENU_PRINCIPAL) {
                            Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);
                            startActivity(intent);
                            finish();
                        }else if (nivel ==SALIR_APP){
                            finish();

                            NumParentActivity.super.onBackPressed();

                        }

                    }
                }).create().show();
    }



    public void backMenu(View view){

        //backPrincipalMenu(MENU_PRINCIPAL);
        Intent intent = new Intent(getApplicationContext(), VideoNumeros.class);
        startActivity(intent);
        finish();

    }


}
