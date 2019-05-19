package anel.com.mx.kgardenapp;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
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

import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.dto.Numero;
import anel.com.mx.kgardenapp.dto.Player;
import anel.com.mx.kgardenapp.firebase.UsuarioFB;
import anel.com.mx.kgardenapp.impl.DrawingActivity;
import anel.com.mx.kgardenapp.impl.Game;
import anel.com.mx.kgardenapp.impl.GameNumerosMayorMenor;
import anel.com.mx.kgardenapp.impl.IGame;

/**
 *
 */
public class Num3_7a_Activity extends NumParentActivity {

    //Declaración de variables
    public  MediaPlayer mp;
    public MediaPlayer mpInstruccion;
    private String sexo;
    private String nombre;
    private String edad;
    private TextView textViewUsuario;
    private  TextView textViewEdad;
    private ImageView imageView;
    private Player player;
    private SharedPreferences preferences ;
    String mayorMenor;
    long tInicio, tFinal, tDuracion;
    //varibales control
    int imagenNumberI =0,imagenNumberD=0,imagenNumberFinal =0,ladoCorrecto=0,numeroFinal=0, numI =0, numD=0;

    IGame gameNumerosMayorMenor ;
    int contador=0;
    ImageButton botonIzq ;
    ImageButton botonDer ;
    TextView texto, textNumIzq, textNumDer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num3_7a);

        super.getInformation();
        gameNumerosMayorMenor = new GameNumerosMayorMenor(super.getActividad());

        botonIzq = findViewById(R.id.imageButtonColIzq);

        botonDer = findViewById(R.id.imageButtonColDer);
        texto =findViewById(R.id.textViewNumber);
        textNumIzq= findViewById(R.id.textViewNumColIzq);
        textNumDer= findViewById(R.id.textViewNumColDer);



        /*
        nombre =getIntent().getExtras().getString("nombre");
        sexo =getIntent().getExtras().getString("sexo");
        edad =getIntent().getExtras().getString("edad");
        player = new Player(gameNumerosMayorMenor.getUIDUsuario(),nombre,Long.valueOf(edad), sexo);
*/
  //      setPlayerInfoPreferences(player);
        //incializar reglas juego
        gameNumerosMayorMenor.inicializa();
        //obtiene numero aleatorio
        generaNumeros();
        tInicio = System.currentTimeMillis();
        botonIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ladoSeleccionado=0;


                    if(gameNumerosMayorMenor.evalua(numI,ladoSeleccionado,numD)){
                        //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                        llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);
                    }else{
                        //Toast.makeText(getApplicationContext(),"Sigue adelante!! ",Toast.LENGTH_SHORT).show();
                        llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);

                    }

                if(evaluaTest()) {
                    generaNumeros();
                }

                    //gameMonedasSimples.evalua();
                    // The toggle is enabled
//                    Drawable drawable=botonDer.getBackground();


            }
        });

        botonDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ladoSeleccionado=1;

                    if(gameNumerosMayorMenor.evalua(numD,ladoSeleccionado,numI)){
                        //Toast.makeText(getApplicationContext(),"Excelente!!",Toast.LENGTH_SHORT).show();
                        llamarPopup(getResources().getString(R.string.excelente),R.drawable.robin);

                    }else{
                        //Toast.makeText(getApplicationContext(),"Vamos inténtalo!",Toast.LENGTH_SHORT).show();
                        llamarPopup(getResources().getString(R.string.sigue_adelante),R.drawable.robin_sad);
                    }

                if(evaluaTest()) {
                    generaNumeros();
                }
            }
        });


        //TODO necesitamos integrar el audio de "selecciona el numero de la"
        // mp= MediaPlayer.create(Num3_5Activity.this, R.raw.dos);
        //mp.start();
    }
    public void generaNumeros() {

        numI =gameNumerosMayorMenor.getNumberaleatorio();
        if(numI == 666){
            llamaResultados();
            //llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta());
        }else {
            imagenNumberI = gameNumerosMayorMenor.getImageResource().get(Integer.valueOf(numI));
            botonIzq.setBackgroundResource(imagenNumberI);
            textNumIzq.setText(String.valueOf(numI));

            numD = gameNumerosMayorMenor.getNumberaleatorio();
        }

        while(numI == numD){
            numD =gameNumerosMayorMenor.getNumberaleatorio();
            if(contador >15){
                numD=5;
                contador=0;
                break;
            }
            contador++;
        }
        if(numD == 666 || numI == 666){
            llamaResultados();
            //llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta());
        }else {
            imagenNumberD = gameNumerosMayorMenor.getImageResource().get(Integer.valueOf(numD));
            botonDer.setBackgroundResource(imagenNumberD);
            textNumDer.setText(String.valueOf(numD));

            ladoCorrecto = gameNumerosMayorMenor.getSide();
            //Toast.makeText(getApplicationContext(),"lado que a seleccionar:::"+ladoCorrecto,Toast.LENGTH_LONG).show();
            numeroFinal = gameNumerosMayorMenor.getNumeroCorrecto(numI, numD, ladoCorrecto);


            if (numI == numeroFinal) {
                if (numeroFinal > numD) {
                    //mas elementos
                    mayorMenor = "más ";
                } else {
                    //menos elementos
                    mayorMenor = "menos ";
                }
            } else if (numD == numeroFinal) {

                if (numeroFinal > numI) {
                    //mas elementos
                    mayorMenor = "más ";
                } else {
                    //menos elementos
                    mayorMenor = "menos ";
                }
            }
            //Toast.makeText(getApplicationContext(),"numero final:::"+numeroFinal,Toast.LENGTH_LONG).show();
            String cadena = "Toca donde hay ";
            cadena = cadena.concat(mayorMenor);

            texto.setText(cadena);
        }
        // agrega auido
        Thread thread = new Thread(){

            public void run(){
                try {
                    mpInstruccion = MediaPlayer.create(Num3_7a_Activity.this, R.raw.tocadndhsy);
                    mpInstruccion.start();
                    sleep(1600);
                    mpInstruccion.release();

                    if (mayorMenor.equalsIgnoreCase("más "))
                    {
                        mpInstruccion = MediaPlayer.create(Num3_7a_Activity.this, R.raw.mas);
                        mpInstruccion.start();
                        sleep(1500);
                        mpInstruccion.release();
                    } else
                    {
                        mpInstruccion = MediaPlayer.create(Num3_7a_Activity.this, R.raw.menos);
                        mpInstruccion.start();
                        sleep(1500);
                        mpInstruccion.release();
                    }

                    // finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
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


        preferences = getApplication().getSharedPreferences("playerPrefs",0);

        if(preferences == null){
            throw new NullPointerException("Error on preferences");
        }

        preferences.edit().putString("userUID", player.getId()).commit();
        preferences.edit().putString("name", player.getName()).commit();
        preferences.edit().putString("age", String.valueOf(player.getAge())).commit();
        preferences.edit().putString("gender", player.getGender()).commit();



        return true;
    }

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
                            Toast.makeText(Num3_7a_Activity.this,
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
        //Toast.makeText(getApplicationContext(),"llegaste a otro nivel",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(Num3_7a_Activity.this, Num3_5Activity.class);
        //intent.putExtra("nombre","Otro nivel");
        //startActivity(intent);
    }

    /**
     *
     */
    public boolean evaluaTest(){
        boolean retorno= true;
        //boolean retorno= true;
        int result =gameNumerosMayorMenor.isTestPass();
        if(result ==0){
/*
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta(),gameNumerosMayorMenor.getAciertosContinuos(),gameNumerosMayorMenor.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            Intent intent = new Intent(getApplicationContext(), VideoNumeros.class);

            startActivity(intent);
*/
//            super.llamaOtroNivel(super.getActividad(),this.getClass(),Num3_2Activity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta(), new UsuarioFB());
            //llamaOtroNivel();
            retorno= true;
        }else if(result==1){
            super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta(),gameNumerosMayorMenor.getAciertosContinuos(),gameNumerosMayorMenor.getErroresContinuos(),getClass());
            super.actualizaResultadoActividad(super.getActividad(),this.getClass());

            super.llamaOtroNivel(super.getActividad(),this.getClass(),VideoNumeros.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta(), new UsuarioFB());
            //llamaVideo();
            retorno= false;
        }
        return retorno;
    }



    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }

    public void llamaResultados(){

        super.guardaResultadoActividad(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta(),gameNumerosMayorMenor.getAciertosContinuos(),gameNumerosMayorMenor.getErroresContinuos(),getClass());
        super.actualizaResultadoActividad(super.getActividad(),this.getClass());
        llamarResultadoPopup(super.getActividad(),  tFinal,  tInicio, DrawingActivity.class,gameNumerosMayorMenor.getNumeros(),gameNumerosMayorMenor.getAciertosTotales(),gameNumerosMayorMenor.getFallosTotales(),gameNumerosMayorMenor.getNumEcxluidosList(),gameNumerosMayorMenor.getNumeroPregunta());
    }

}
