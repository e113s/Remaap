package anel.com.mx.kgardenapp;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import anel.com.mx.kgardenapp.dao.AppDatabase;
import anel.com.mx.kgardenapp.entitty.User;
import anel.com.mx.kgardenapp.impl.DrawingActivity;

/**
 *
 */
public class MainActivity extends NumParentActivity {
    private MediaPlayer mp;
    private Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View logo =findViewById(R.id.imageView3);

        if(super.isUserExist()){
            intent = new Intent(MainActivity.this, DrawingActivity.class);
        }else{
            intent = new Intent(MainActivity.this, DataCollectorActivity.class);
        }

        //final Intent intent = new Intent(MainActivity.this, DataCollectorActivity.class);
      Thread thread = new Thread(){

          public void run(){
              try {
                  mp= MediaPlayer.create(MainActivity.this, R.raw.bienvenidos_inicio);
                  mp.start();
                  sleep(5000);

                  startActivity(intent);
                  overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_left);
                  mp.stop();
                  finish();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      };
        thread.start();


    }


    @Override
    public void onBackPressed() {
        MainActivity.super.onBackPressed();
    }


}
