package anel.com.mx.kgardenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.media.MediaPlayer;

/**
 * Created by ddarredondo on 04/04/2018.
 */

public class HMActivity extends NumParentActivity{
    private MediaPlayer mp;
    private String sexo;
    private String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sexo_slide);
        final ToggleButton buttonM = findViewById(R.id.toggleButtonMale);
        Button forward = findViewById(R.id.buttonForward2);
        final ToggleButton  buttonF = findViewById(R.id.toggleButtonFemale);
        mp= MediaPlayer.create(HMActivity.this, R.raw.ninoonina);
        mp.start();





            final Intent intent = new Intent(this, AgeActivity.class);

            forward.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(!buttonM.isChecked()&& !buttonF.isChecked())
                    {
                        mp= MediaPlayer.create(HMActivity.this, R.raw.ninoonina);
                        mp.start();
                    }else
                    {
                        intent.putExtra("nombre",nombre);
                        intent.putExtra("sexo",sexo);
                        startActivity(intent);
                        //velocidad de aparcion de otra actividad
                       // overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_left);
                    }


                }
            });






         nombre =getIntent().getExtras().getString("nombre");

        buttonM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    buttonF.setChecked(false);
                    sexo="Hombre";
                } else {
                    // The toggle is disabled

                }
            }
        });


        buttonF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    buttonM.setChecked(false);
                    sexo="Mujer";
                } else {
                    // The toggle is disabled

                }
            }
        });
        //int[] state = button.getDrawableState();




        //regresa a la actividad anterior
/*
        Button back = findViewById(R.id.buttonBack);


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HMActivity.super.onBackPressed();
            }
        });
        */

    }


    @Override
    public void onBackPressed() {
        backPrincipalMenu(0);
    }

}
