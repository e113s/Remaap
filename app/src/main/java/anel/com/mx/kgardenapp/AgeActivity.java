package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.media.MediaPlayer;

import java.util.HashMap;

/**
 * Created by ddarredondo on 05/04/2018.
 * Modified by ragutierrez 24/04/18.
 * Esta clase selecciona de la edad del infante mediante imageView
 *
 */

public class AgeActivity extends NumParentActivity {
    public  MediaPlayer mp;
    private SeekBar seekBar;
    private TextView textViewAge;
    private ImageView ImageView3, ImageView4, ImageView5, ImageView6, ImageView7, ImageView8;
    //private ToggleButton toggleButton;

    /*
    campos para obtener los datos del niño
     */
    private String sexo;
    private String nombre;
    private int edad=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_age_slide);

        //obetenemos los valores de la actividad anterior
        nombre =getIntent().getExtras().getString("nombre");
        sexo =getIntent().getExtras().getString("sexo");
        mp= MediaPlayer.create(AgeActivity.this, R.raw.anios);
        mp.start();
        ImageView3 = (ImageView)findViewById(R.id.imageViewEdad3);
        ImageView4 = (ImageView)findViewById(R.id.imageViewEdad4);
        ImageView5 = (ImageView)findViewById(R.id.imageViewEdad5);
        ImageView6 = (ImageView)findViewById(R.id.imageViewEdad6);
        ImageView7 = (ImageView)findViewById(R.id.imageViewEdad7);
        ImageView8 = (ImageView)findViewById(R.id.imageViewEdad8);


        ImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView3.setImageResource(R.drawable.edad3);
                edad = 3;
                ImageView4.setImageResource(R.drawable.edad4_sincolor);
                ImageView5.setImageResource(R.drawable.edad5_sincolor);
                ImageView6.setImageResource(R.drawable.edad6_sincolor);
                ImageView7.setImageResource(R.drawable.edad7_sincolor);
                ImageView8.setImageResource(R.drawable.edad8_sincolor);
            }
        });

        ImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView4.setImageResource(R.drawable.edad4);
                edad = 4;
                ImageView3.setImageResource(R.drawable.edad3_sincolor);
                ImageView5.setImageResource(R.drawable.edad5_sincolor);
                ImageView6.setImageResource(R.drawable.edad6_sincolor);
                ImageView7.setImageResource(R.drawable.edad7_sincolor);
                ImageView8.setImageResource(R.drawable.edad8_sincolor);
            }
        });

        ImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView5.setImageResource(R.drawable.edad5);
                edad = 5;
                ImageView3.setImageResource(R.drawable.edad3_sincolor);
                ImageView4.setImageResource(R.drawable.edad4_sincolor);
                ImageView6.setImageResource(R.drawable.edad6_sincolor);
                ImageView7.setImageResource(R.drawable.edad7_sincolor);
                ImageView8.setImageResource(R.drawable.edad8_sincolor);
            }
        });

        ImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView6.setImageResource(R.drawable.edad6);
                edad = 6;
                ImageView3.setImageResource(R.drawable.edad3_sincolor);
                ImageView4.setImageResource(R.drawable.edad4_sincolor);
                ImageView5.setImageResource(R.drawable.edad5_sincolor);
                ImageView7.setImageResource(R.drawable.edad7_sincolor);
                ImageView8.setImageResource(R.drawable.edad8_sincolor);
            }
        });

        ImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView7.setImageResource(R.drawable.edad7);
                edad = 7;
                ImageView3.setImageResource(R.drawable.edad3_sincolor);
                ImageView4.setImageResource(R.drawable.edad4_sincolor);
                ImageView5.setImageResource(R.drawable.edad5_sincolor);
                ImageView6.setImageResource(R.drawable.edad6_sincolor);
                ImageView8.setImageResource(R.drawable.edad8_sincolor);
            }
        });

        ImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView8.setImageResource(R.drawable.edad8);
                edad = 8;
                ImageView3.setImageResource(R.drawable.edad3_sincolor);
                ImageView4.setImageResource(R.drawable.edad4_sincolor);
                ImageView5.setImageResource(R.drawable.edad5_sincolor);
                ImageView6.setImageResource(R.drawable.edad6_sincolor);
                ImageView7.setImageResource(R.drawable.edad7_sincolor);
            }
        });


       /* seekBar = findViewById(R.id.seekBarAge);
        textViewAge= findViewById(R.id.textViewNumber);
        toggleButton =findViewById(R.id.toggleButtonAge);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                if(getImageResource().get(Integer.valueOf(progress)) == null){
                    toggleButton.setBackgroundResource(R.drawable.fill_blank);
                }else {
                    toggleButton.setBackgroundResource(getImageResource().get(Integer.valueOf(progress)));
                    textViewAge.setText(String.valueOf(progress));
                    edad=progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

*/
        //lama a otra actividad
        Button forward = findViewById(R.id.buttonForward3);



        forward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AgeActivity.this, WelcomeActivity.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("sexo",sexo);
                intent.putExtra("edad",String.valueOf(edad));
                if (edad!=0) {
                    startActivity(intent);
                }else if(edad==0)
                {
                    mp.start();
                }
                //velocidad de aparcion de otra actividad
               // overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_left);
            }
        });


        //regresa a la actividad anterior

        Button back = findViewById(R.id.buttonBack);


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AgeActivity.super.onBackPressed();
            }
        });

    }

    /**
     * TODO este metodo debe cambiar para conectarse a la base de datos y traerse una lista de numeros
     * @return un map de interros el valor es el numero que le corresponde a un a imagen que se mostrara en un toggle button
     */
  /*  public HashMap<Integer,Integer> getImageResource (){
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

        map.put(Integer.valueOf(1),R.drawable.uno);
        map.put(Integer.valueOf(2),R.drawable.dos);
        map.put(Integer.valueOf(3),R.drawable.tres);
        map.put(Integer.valueOf(4),R.drawable.cuatro);
        map.put(Integer.valueOf(5),R.drawable.cinco);
        map.put(Integer.valueOf(6),R.drawable.seis);
        map.put(Integer.valueOf(7),R.drawable.siete);
        map.put(Integer.valueOf(8),R.drawable.ocho);
        map.put(Integer.valueOf(9),R.drawable.nueve);
        map.put(Integer.valueOf(10),R.drawable.diez);

        return map;

    }*/

    @Override
    public void onBackPressed() {
        backPrincipalMenu(0);
    }

}
