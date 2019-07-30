package anel.com.mx.kgardenapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.media.MediaPlayer;

import anel.com.mx.kgardenapp.impl.DrawingActivity;

public class DataCollectorActivity extends NumParentActivity {
    private MediaPlayer mp;
    private ViewPager vpager;
    private PagerAdapter mPagerAdapter;
    private Button forward;
    private EditText nombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector);
         forward =  findViewById(R.id.buttonForward1);

        nombre = findViewById(R.id.editTextNombre);
        mp= MediaPlayer.create(DataCollectorActivity.this, R.raw.nombre);
        mp.start();
        mp.release();
        //Toast.makeText(getApplicationContext(),"llamando a la actividad",Toast.LENGTH_LONG);


       /*
        Toast.makeText(getApplicationContext(),"llamando a la actividad",Toast.LENGTH_LONG);



        final Intent intent = new Intent(DataCollectorActivity.this, HMActivity.class);
       Toast.makeText(getApplicationContext(),nombre.toString(),Toast.LENGTH_LONG);
*/
        forward.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if(nombre.length()==0){
                    mp= MediaPlayer.create(DataCollectorActivity.this, R.raw.nombre);
                    mp.start();
                    mp.release();
                    //TODO aqui debe de ponerse otra vez la voz para que pinga su nombre
                    //Toast.makeText(getApplicationContext(),"Ingresa tu nombre", Toast.LENGTH_LONG);
                }else{
                    mp.release();
                    Intent intent = new Intent(DataCollectorActivity.this, HMActivity.class);
                    intent.putExtra("nombre",nombre.getText().toString());
                    startActivity(intent);
                    //velocidad de aparcion de otra actividad
                   // overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_left);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    backPrincipalMenu(0);
    }

}
