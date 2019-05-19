package anel.com.mx.kgardenapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import anel.com.mx.kgardenapp.dto.Numero;
import anel.com.mx.kgardenapp.dto.Player;
import anel.com.mx.kgardenapp.impl.Game;
import anel.com.mx.kgardenapp.impl.GameMonedasSimples;
import anel.com.mx.kgardenapp.impl.IGame;

public class IM_P1 extends NumParentActivity {

    public MediaPlayer mp;

    public MediaPlayer mpInstruccion;
    private String sexo;
    private String nombre;
    private String edad;
    private TextView textViewUsuario;
    private  TextView textViewEdad;
    private ImageView imageView;
    private Player player;
    private long tInicio, tFinal, tDuracion;
    private SharedPreferences preferences ;


    //DataBase
    private FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();

    //varibales control
    int imagenNumberI =0,imagenNumberD=0,imagenNumberFinal =0,ladoCorrecto=0, numeroFinal=0, numI =0, numD=0;

    private IGame gameMonedasSimples;

    ToggleButton botonIzq ;
    ToggleButton botonDer ;
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im__p1);

        botonIzq = findViewById(R.id.imageButtonIzquierda);
        botonDer = findViewById(R.id.imageButtonDerecha);
        texto =findViewById(R.id.textViewNumber);

        super.getInformation();
        gameMonedasSimples = new GameMonedasSimples(super.getActividad());

        /* obtenemos los datos del jugador en las preferencias si es nulo no*/
        if(gameMonedasSimples.getUIDUsuario() == null){
            //TODO pint a message to this error
        }


        nombre =getIntent().getExtras().getString("nombre");
        sexo =getIntent().getExtras().getString("sexo");
        edad =getIntent().getExtras().getString("edad");
        player = new Player(gameMonedasSimples.getUIDUsuario(),nombre,Long.valueOf(edad), sexo);

        setPlayerInfoPreferences(player);
        //incializar reglas juego
        gameMonedasSimples.inicializa();
        //obtiene numero aleatorio
        generaNumeros();
        //incializa tiempo
        tInicio = System.currentTimeMillis();
        botonIzq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int ladoSeleccionado=0;
                if (isChecked) {

                    // buttonView.getBackground().getCurrent();
                    botonDer.setChecked(false);
                    if(gameMonedasSimples.evalua(numI,ladoSeleccionado,numD)){
                        Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                    }

                    if(evaluaTest()) {
                        generaNumeros();
                    }
                } else {
                    // The toggle is disabled

                }
            }
        });

        botonDer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int ladoSeleccionado=1;
                if (isChecked) {
                    // The toggle is enabled
                    //buttonView.getBackground().getCurrent();
                    botonIzq.setChecked(false);
                    if(gameMonedasSimples.evalua(numD,ladoSeleccionado,numI)){
                        Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Vamos inténtalo!",Toast.LENGTH_SHORT).show();
                    }
                    if(evaluaTest()) {
                        generaNumeros();
                    }

                    //gameMonedasSimples.isTestPass();
                }
            }
        });


        //TODO necesitamos integrar el audio de "selecciona el numero de la"
        // mp= MediaPlayer.create(Num3_5Activity.this, R.raw.dos);
        //mp.start();
    }


    public void generaNumeros() {

        numI = gameMonedasSimples.getNumberaleatorio();

        if(numI == 666){
            llamaResultado();
        }else {
            imagenNumberI = gameMonedasSimples.getImageResource().get(Integer.valueOf(numI));
            botonIzq.setBackgroundResource(imagenNumberI);

            numD = gameMonedasSimples.getNumberaleatorio();

            while(numI == numD){
                numD =gameMonedasSimples.getNumberaleatorio();
            }
            // numD = gameMonedasSimples.getNumberaleatorio();
            if(numD == 666){
                llamaResultado();
            }else {
                imagenNumberD = gameMonedasSimples.getImageResource().get(Integer.valueOf(numD));
                botonDer.setBackgroundResource(imagenNumberD);

                ladoCorrecto = gameMonedasSimples.getSide();
                //Toast.makeText(getApplicationContext(),"lado que a seleccionar:::"+ladoCorrecto,Toast.LENGTH_LONG).show();
                numeroFinal = gameMonedasSimples.getNumeroCorrecto(numI, numD, ladoCorrecto);
                //Toast.makeText(getApplicationContext(),"numero final:::"+numeroFinal,Toast.LENGTH_LONG).show();
                String cadena = getResources().getString(R.string.selecciona_moneda);
                cadena = cadena.concat(" ").concat(gameMonedasSimples.getNumeroLetrasMap().get(numeroFinal).toString()).concat(" de la ").
                        concat(gameMonedasSimples.getladoLetrasMap().get(Integer.valueOf(ladoCorrecto)).toString());

                texto.setText(cadena);
                // agrega auido
                Thread thread = new Thread() {

                    public void run() {
                        try {
                            mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.moneda);
                            mpInstruccion.start();
                            sleep(1500);
                            mpInstruccion.release();

                            if (numeroFinal == 0) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.cero);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 1) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.un);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 2) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.dos);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 3) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.tres);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 4) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.cuatro);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 5) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.cinco);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 6) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.seis);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 7) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.siete);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 8) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.ocho);
                                mpInstruccion.start();
                                sleep(1000);
                                mpInstruccion.release();
                            } else if (numeroFinal == 9) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.nueve);
                                mpInstruccion.start();
                                sleep(1200);
                                mpInstruccion.release();
                            } else if (numeroFinal == 10) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.diez);
                                mpInstruccion.start();
                                sleep(1200);
                                mpInstruccion.release();
                            }

                            mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.pesos);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.dela);
                            mpInstruccion.start();
                            sleep(1000);
                            mpInstruccion.release();

                            if (ladoCorrecto == 0) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.izquierda);
                                mpInstruccion.start();
                                sleep(1200);
                                mpInstruccion.release();

                            } else if (ladoCorrecto == 1) {
                                mpInstruccion = MediaPlayer.create(IM_P1.this, R.raw.derecha);
                                mpInstruccion.start();
                                sleep(1200);
                                mpInstruccion.release();

                            }


                            // finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();


            }//fin segundo del else

        }

    }



    public int getImageNumber(Numero numero){
        Random random = new Random();
        int posicion =random.nextInt(10);
        int valor = numero.imagenesNumeros[posicion];

        return valor;
    }



    public boolean setPlayerInfoPreferences(Player player){


        if(player.getId() == null){
            throw new NullPointerException("Error on Player");
        }

        if(player.getAge() == null){
            throw new NullPointerException("Error on Player");
        }

        if(player.getName() == null){
            throw new NullPointerException("Error on Player");
        }

        preferences = getApplication().getSharedPreferences("palyerPrefs",0);

        if(preferences == null){
            throw new NullPointerException("Error on preferences");
        }

        preferences.edit().putString("userUID", player.getId()).commit();
        preferences.edit().putString("name", player.getName()).commit();
        preferences.edit().putString("age", String.valueOf(player.getAge())).commit();
        preferences.edit().putString("gender", player.getGender()).commit();
        return true;
    }






/*
    private String getUIDUsuario(){

        preferences = getApplication().getSharedPreferences("palyerPrefs",0);
        String userUID = preferences.getString("userUID",null);
        if(userUID == null) {
            Date date = new Date();
            String stringDate = String.valueOf(date.getTime());
            Random random = new Random();
            stringDate = stringDate + String.valueOf(random.nextInt(10000));
            userUID = stringDate;
            preferences.edit().putString("userUID", userUID).commit();
            //Toast.makeText(this, "la llave userUID es  ::::::::::"+userUID, Toast.LENGTH_LONG).show();
        }
            return userUID;

    }
*/


    public void getConnection(){

        FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();

        //inicio conexion base de datos
        DatabaseReference reference = fdatabase.getReference();
        //reference.child("palyer").child("id").setValue("50");

        //inicio conexion base de datos

    }


    public boolean isPlayerExist(String userId) {

        FirebaseDatabase fdatabase = FirebaseDatabase.getInstance();
        //inicio conexion base de datos
        DatabaseReference reference = fdatabase.getReference();

        reference.child("players");

        // [START single_value_read]

        reference.child("players").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Player user = dataSnapshot.getValue(Player.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Toast.makeText(IM_P1.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            // writeNewPost(userId, user.username, title, body);
                        }

                        // Finish this Activity, back to the stream
                        //setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        //setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]

        return true;
    }


    public  void llamaOtroNivel(){
        //llamaResultado();
        Intent intent = new Intent(IM_P1.this, Num3_5Activity.class);
        intent.putExtra("nombre","Otro nivel");
        startActivity(intent);

    }


    public  void llamaVideo(){

        Intent intent = new Intent(IM_P1.this, VideoNumeros.class);
        intent.putExtra("nombre","Otro nivel");
        startActivity(intent);
        finish();
    }

    public void llamaResultado() {
        tFinal = System.currentTimeMillis();
        tDuracion = (tFinal - tInicio) / 1000;

        final AlertDialog.Builder myResult = new AlertDialog.Builder(this);
        myResult.setMessage("Hola:   " + "* * *       " + nombre + "       * * *" + "\n" +
                "Aciertos = " + gameMonedasSimples.getAciertosTotales() + "\n" +
                "Fallas =" + gameMonedasSimples.getFallosTotales() + "\n" +
                "Número de preguntas =" + gameMonedasSimples.getNumeroPregunta() + "\n" +
                "Tiempo= " + tDuracion + "segundos" + "\n" +
                "Acertivicidad =" + "Bien");


        myResult.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(IM_P1.this, Num3_7a_Activity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("sexo", sexo);
                intent.putExtra("edad", String.valueOf(edad));
                startActivity(intent);
                finish();
            }
        });

    }

    public boolean evaluaTest(){
        boolean retorno= true;
        int result = gameMonedasSimples.isTestPass();
        if(result ==0){
            llamaOtroNivel();
            retorno= false;

        }else if(result==1){
            llamaVideo();
            retorno= false;
        }

        return retorno;
    }

    @Override
    public void onBackPressed() {
        backPrincipalMenu(0);
    }


}
