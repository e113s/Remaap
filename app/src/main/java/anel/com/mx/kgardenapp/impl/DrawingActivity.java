package anel.com.mx.kgardenapp.impl;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArraySet;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anel.com.mx.kgardenapp.DataCollectorActivity;
import anel.com.mx.kgardenapp.MainActivity;
import anel.com.mx.kgardenapp.Num3_1Activity;
import anel.com.mx.kgardenapp.Num3_5Activity;
import anel.com.mx.kgardenapp.Num3_5_0Activity;
import anel.com.mx.kgardenapp.Num3_5_1Activity;
import anel.com.mx.kgardenapp.Num3_7a_Activity;
import anel.com.mx.kgardenapp.NumParentActivity;
import anel.com.mx.kgardenapp.R;
import anel.com.mx.kgardenapp.VideoNumeros;
import anel.com.mx.kgardenapp.dao.AppDatabase;
import anel.com.mx.kgardenapp.dto.Actividad;
import anel.com.mx.kgardenapp.entitty.Eje;
import anel.com.mx.kgardenapp.entitty.User;
import anel.com.mx.kgardenapp.firebase.UsuarioFB;

/**
 * created by Anel G. G.
 * This class will  handle the whole menu about "Eje, objetivo, habilidad y actividad
 */
public class DrawingActivity  extends NumParentActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener{

    private List<Integer> layoutList = new ArrayList<Integer>();
    private int[] Layout={R.id.LayoutIdOne,R.id.LayoutIdTwo,R.id.LayoutIdThree,R.id.LayoutIdFour,R.id.LayoutIdSix,R.id.LayoutIdSeven,R.id.LayoutIdEight};

    private TextView nombreMenu;
    private  TextView edadMenu;
    private ImageView imageSexo;
    User usuario = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);


        getConnection();
//        super.getInformation();
        


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.LayoutIdOne).setOnClickListener(this);
        findViewById(R.id.LayoutIdTwo).setOnClickListener(this);
        findViewById(R.id.LayoutIdThree).setOnClickListener(this);
        findViewById(R.id.LayoutIdFour).setOnClickListener(this);
        findViewById(R.id.LayoutIdFive).setOnClickListener(this);
        findViewById(R.id.LayoutIdSix).setOnClickListener(this);
        findViewById(R.id.LayoutIdSeven).setOnClickListener(this);
        findViewById(R.id.LayoutIdEight).setOnClickListener(this);


        layoutList.add(R.id.LayoutIdOne);

        layoutList.add(R.id.LayoutIdTwo);
        layoutList.add(R.id.LayoutIdThree);
        layoutList.add(R.id.LayoutIdFour);
        layoutList.add(R.id.LayoutIdFive);
        layoutList.add(R.id.LayoutIdSix);
        layoutList.add(R.id.LayoutIdSeven);
        layoutList.add(R.id.LayoutIdEight);


        //LayoutMap.put(R.id.la)
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =navigationView.getHeaderView(0);

        nombreMenu = hView.findViewById(R.id.textViewMenuIzqNombre);
        edadMenu = hView.findViewById(R.id.textViewMenuIzqEdad);
        imageSexo= hView.findViewById(R.id.imageViewSexo);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Insert Data
                mDb = AppDatabase.getDatabase(getApplicationContext());
                mDb.userDao();

                usuario= mDb.userDao().getUserbyId(getUIDUsuario());
                /*if(usuario == null){
                   Intent intent = new Intent(getApplicationContext(), DataCollectorActivity.class);
                    startActivity(intent);
                }else {*/
                    nombreMenu.setText(usuario.nombre);

                    edadMenu.setText(usuario.edad);

                    if(usuario.sexo.equalsIgnoreCase("Hombre")){
                        imageSexo.setImageResource(R.drawable.boy);
                    }else if(usuario.sexo.equalsIgnoreCase("Mujer")){
                        imageSexo.setImageResource(R.drawable.girl);
                    }


              //  }
                /*
                StringBuilder users = new StringBuilder();

                for (int i = 0; i < AppDatabase.getDatabase(getApplicationContext()).UserDao().getAll().size(); i++) {

        l            //Get user Data
                    users.append(" \n "+ AppDatabase.getDatabase(getApplicationContext()).UserDao().getAll().get(i).toString());

                }
                */
                //textView.setText(users);
            }
        });


        navigationView.setNavigationItemSelectedListener(this);

        View childView =null;
        LinearLayout layout= null;
        Animation animation= null;
        setTitle(R.string.action_comunicar_numeros);
        childView = getLayoutInflater().inflate(R.layout.activity_main_content,null);
        layout =(LinearLayout) findViewById(R.id.child_linear);
        layout.removeAllViews();
        //setContentView(linera);
        //Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //linera.startAnimation(hyperspaceJumpAnimation);
        animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        //animation.setDuration(800);
        //linera.setAnimation(animation);
        layout.startAnimation(animation);
        layout.addView(childView);

        if(getEstatusActividad(Num3_5_1Activity.class)) {
            layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
        }
        layout.findViewById(R.id.cardViewContentMainOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miSeleccion(view, R.string.eje_numeros, R.string.action_comunicar_numeros, R.string.action_percepcion);
            }
        });


        if(getEstatusActividad(Num3_1Activity.class)) {
            layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
        }
        layout.findViewById(R.id.cardViewContentMainTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_atencion);
            }
        });

        if(getEstatusActividad(Num3_7a_Activity.class)) {
            layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
        }
        layout.findViewById(R.id.cardViewContentMainThree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_memoria);
            }
        });

        if(getEstatusActividad(VideoNumeros.class)) {
            layout.findViewById(R.id.imageViewVideo).setBackgroundResource(R.drawable.image_video);
        }
        layout.findViewById(R.id.cardViewContentMainFour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_video);
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.backPrincipalMenu(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //sale de la app
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void miSeleccion(View view, final int eje, int objetivo, int habilidad){

        Class claseDestino=null;


        switch (habilidad){
            case  R.string.action_percepcion:
                claseDestino=Num3_5Activity.class;
                break;

            case  R.string.action_atencion:
                claseDestino=Num3_1Activity.class;
                break;

            case  R.string.action_memoria:
                claseDestino=Num3_7a_Activity.class;
                break;
            case  R.string.action_video:
                claseDestino=VideoNumeros.class;
                break;
        }



        //Class actividad =getActivity();
        //getNextActividad(R.string.)
        llamaOtroNivel(new Actividad(),this.getClass(),claseDestino, new int[33][11], 0, 0, new ArraySet<Integer>(),0,super.getUser());
        //Snackbar.make(view, "THis Click is working hahaahaha", Snackbar.LENGTH_LONG)
         //       .setAction("Action", null).show();
    }

    @Override
    public void onClick(View view) {
        Class claseDestino = null;
        View childView =null;
        LinearLayout layout= null;
        Animation animation= null;

        //if (layoutList.get(view.getId()) != null) {

            switch (view.getId()){

                case R.id.LayoutIdOne:

                    setTitle(R.string.action_comunicar_numeros);
                    childView = getLayoutInflater().inflate(R.layout.activity_main_content,null);
                    layout =(LinearLayout) findViewById(R.id.child_linear);
                    layout.removeAllViews();
                    //setContentView(linera);
                    //Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                    //linera.startAnimation(hyperspaceJumpAnimation);
                    animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
                    //animation.setDuration(800);
                    //linera.setAnimation(animation);
                    layout.startAnimation(animation);
                    layout.addView(childView);

                    if(getEstatusActividad(Num3_5_1Activity.class)) {
                        layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
                    }
                    layout.findViewById(R.id.cardViewContentMainOne).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.eje_numeros, R.string.action_comunicar_numeros, R.string.action_percepcion);
                        }
                    });


                    if(getEstatusActividad(Num3_1Activity.class)) {
                        layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
                    }
                    layout.findViewById(R.id.cardViewContentMainTwo).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_atencion);
                        }
                    });

                    if(getEstatusActividad(Num3_7a_Activity.class)) {
                        layout.findViewById(R.id.imageViewPercepcion).setBackgroundResource(R.drawable.percepcion);
                    }
                    layout.findViewById(R.id.cardViewContentMainThree).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_memoria);
                        }
                    });

                    if(getEstatusActividad(VideoNumeros.class)) {
                        layout.findViewById(R.id.imageViewVideo).setBackgroundResource(R.drawable.image_video);
                    }
                    layout.findViewById(R.id.cardViewContentMainFour).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.eje_numeros,R.string.action_comunicar_numeros, R.string.action_video);
                        }
                    });
                    //claseDestino=Num3_1Activity.class;
                   // llamaOtroNivel(new Actividad(), claseDestino, new int[33][11], 0, 0, new ArraySet<Integer>(),0);
                 break;

                 /*
                case R.id.LayoutIdTwo:
                    setTitle(R.string.action_escribir_numeros);
                     childView= getLayoutInflater().inflate(R.layout.activity_main_content,null);
                     layout=(LinearLayout) findViewById(R.id.child_linear);
                    layout.removeAllViews();
                    //setContentView(linera);
                    //Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                    //linera.startAnimation(hyperspaceJumpAnimation);
                    animation= AnimationUtils.loadAnimation(this,R.anim.alpha);
                    //animation.setDuration(800);
                    //linera.setAnimation(animation);
                    layout.startAnimation(animation);
                    layout.addView(childView);
                    layout.findViewById(R.id.cardViewContentMainOne).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.action_escribir_numeros, R.string.action_escribir_numeros, R.string.action_percepcion);
                        }
                    });
                    layout.findViewById(R.id.cardViewContentMainTwo).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.action_escribir_numeros, R.string.action_escribir_numeros, R.string.action_atencion);
                        }
                    });
                    layout.findViewById(R.id.cardViewContentMainThree).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            miSeleccion(view, R.string.action_escribir_numeros, R.string.action_escribir_numeros, R.string.action_memoria);
                        }
                    });
                    break;*/
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        //}
    }


}
