package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.util.ArraySet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import anel.com.mx.kgardenapp.dao.AppDatabase;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.entitty.User;
import anel.com.mx.kgardenapp.firebase.ActividadFB;
import anel.com.mx.kgardenapp.firebase.ActividadResultadoFB;
import anel.com.mx.kgardenapp.firebase.EjeFB;
import anel.com.mx.kgardenapp.firebase.Habilidad;
import anel.com.mx.kgardenapp.firebase.TemaFB;
import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.firebase.util.Eje;
import anel.com.mx.kgardenapp.firebase.util.Tema;
import anel.com.mx.kgardenapp.firebase.util.Util;
import anel.com.mx.kgardenapp.impl.DrawingActivity;

/**
 * clase que presenta la ultima pantalla que colecta los datos del jugador antes de iniciar el juego.
 * el juego iniciara dependiendo de la edad
 *
 * this class gathers all information before start the game
 * the game will start depend on the age
 */
public class WelcomeActivity extends NumParentActivity implements View.OnClickListener{

    private String sexo;
    private String nombre;
    private String edad;
    private TextView textViewUsuario;
    private  TextView textViewEdad;
    private ImageView imageView;
    Actividad actividad = new Actividad();

    UsuarioFB user;
    private SharedPreferences preferences ;
    //FirebaseDatabase database;
    //DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //acceso a firebase
        // database = FirebaseDatabase.getInstance();
         //myRef = database.getReference("users");

        //myRef = database.getReference("ejes");
        //myRef.child("numeros");

        //obetenemos los valores de la actividad anterior
        nombre =getIntent().getExtras().getString("nombre");
        sexo =getIntent().getExtras().getString("sexo");
        edad =getIntent().getExtras().getString("edad");


        textViewUsuario = findViewById(R.id.textViewUsuario);
        textViewUsuario.setText(nombre);
        textViewEdad= findViewById(R.id.textViewEdad);
        textViewEdad.setText( edad + " años");
        imageView = (ImageView) findViewById(R.id.imageViewUsuario);

        if (sexo.equalsIgnoreCase("Hombre")){

            imageView.setBackgroundResource(R.drawable.boy);
            imageView.setVisibility(View.VISIBLE);


        } else if (sexo.equalsIgnoreCase("Mujer")) {
            imageView.setBackgroundResource(R.drawable.girl);
        }

        //llama a otra actividad
        Button forward = findViewById(R.id.buttonForward1);
        forward.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Class claseDestino = null;
        actividad.setNombre(nombre);
        actividad.setEdad(edad);
        actividad.setSexo(sexo);

        //make of profile
        //getUIDUsuario();

        getConnection();


        UsuarioFB usuarioFB= creaPerfil(Integer.valueOf(edad),nombre, sexo);
        usuarioFB.nombre=nombre;
        usuarioFB.sexo=sexo;
        //asigna la clase destino

       // if(!super.isUserExist()) {

            populateDataBase(usuarioFB);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                    //Insert Data
                    mDb = AppDatabase.getDatabase(getApplicationContext());
                    mDb.userDao();
                    User usuario = new User();
                    usuario.id = getUIDUsuario();
                    usuario.nombre = nombre;
                    usuario.edad = edad;
                    usuario.sexo = sexo;

                    mDb.userDao().insertAll(usuario);

                }
            });
        //}



        // llama a otro nivel
        Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);
        startActivity(intent);
        finish();
        //llamaOtroNivel(actividad,this.getClass(), claseDestino, new int[33][11], 0, 0, new ArraySet<Integer>(),0,usuarioFB);

       /*
        switch (Integer.valueOf(edad)){

            case 3:

                claseDestino=Num3_7Activity.class;
                break;
            case 4:
                claseDestino=Num3_1Activity.class;
                break;
            case 5:
                claseDestino=Num3_5Activity.class;
                break;
            case 6:
                claseDestino=Num3_7Activity.class;
                break;
            case 7:
                claseDestino=Num3_2Activity.class;
                break;
            case 8:
                claseDestino=Num_3_3Activity.class;
                break;

        }*/
        //sincroniza con la base de datos y crea el usuario
        //creaDatos(Integer.valueOf(edad));



    }





    /**
     * para usuarios nuevos
     */
    public void creaDatos(Integer edad){

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        // creacion de usuario
      //  UsuarioFB user = new UsuarioFB(getUIDUsuario(),nombre,edad,sexo,date,date,"activo");





        //UsuarioFB user = new UsuarioFB(getUIDUsuario(),nombre,edad,sexo,date,date,"activo",ejeTree,ejeList);
        //myRef.child(user.getId()).setValue(user);

        //usuariosList.add(user);
        //myRef.setValue("Hello, World!");
         //myRef.setValue(usuariosList);
       // myRef.child("users").setValue(user.getNombre());

        /*
        myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsuarioFB user = new UsuarioFB(getUIDUsuario(),nombre,edad,sexo);
               // myRef.setValue(user);
                myRef.setValue(user.getNombre());
                //String value = dataSnapshot.getValue(String.class);
                //viewText.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Ch3", "Failed to read value.", error.toException());
            }
        });
*/

    }




}
