package anel.com.mx.kgardenapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import anel.com.mx.kgardenapp.impl.GameAtencion;
import anel.com.mx.kgardenapp.impl.GameMemoriaUno;
import anel.com.mx.kgardenapp.impl.GameOperacionesAritmeticas;

/**
 * @author Anel Guitierrez, Alberto delgadillo
 * Esta clase es usuada para realizar el juego de memoria, se les presenta una tarjeta con numeros por determinado tiempo
 * se oculta y se le muestra otra tarjeta para que haga tocuh sobre los numeros que estaban en la tarjeta anterior
 */
public class Num_3_3Activity extends NumParentActivity implements View.OnClickListener {

    private CardView cardViewOne,cardViewTwo,cardViewThree,cardViewFour,cardViewFive,cardViewSix;
    private ImageView imageViewOne,imageViewTwo, imageViewThree,imageViewFour,imageViewFive,imageViewSix;
    private long tInicio, tFinal, tDuracion;
    private GameMemoriaUno gameMemoriaUno;
    private int resultado=0,elemento1=0,elemento2=0, elemento3=0, numeroSeleccionado =0,imagenNumero;

    boolean mShowingBack;
    private ImageView imageViewBack;
    TextView texto1;
   private  ProgressBar progressBar;
   Handler progressHandler = new Handler();
    int progressStatusCounter = 0;

    private int imagesArray[]={R.id.imageViewNumeroUno,R.id.imageViewNumeroDos,R.id.imageViewNumeroTres,R.id.imageViewNumeroCuatro,R.id.imageViewNumeroCinco,R.id.imageViewNumeroSeis};
    private ImageView imageView;
    HashMap<Integer,Integer> numPantallaMap = new HashMap<Integer, Integer>();
    List<Integer> lista = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_3_3);


        super.getInformation();
        gameMemoriaUno = new GameMemoriaUno(super.getActividad());


        texto1 =findViewById(R.id.textViewTitulo2);

        progressBar=  findViewById(R.id.progressBarId);
        progressBar.setProgress(1);


        findViewById(R.id.container);

        imageViewBack= findViewById(R.id.cardViewTarjetaBack);


     generaNumeros() ;


        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }


    }

    @Override
    public void onClick(View view) {

        flipCard();

    }


    /**
     * A fragment representing the front of the card.
     */
    @SuppressLint("ValidFragment")
    public class  CardFrontFragment extends Fragment implements View.OnClickListener {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View  vista =inflater.inflate(R.layout.memoria_front_view, container, false);

            /*
            int v = 0;
            for (Integer valor : lista) {
                imageView = findViewById(imagesArray[v]);
                // agrego numeros de la izquierda
                numPantallaMap.put(imagesArray[v], valor);
                //obtiene el resource de la imagen correspondiente al numero obtenido
                int resource = gameMemoriaUno.getImageResource().get(valor);
                imageView.setBackgroundResource(resource);
                //button.setOnClickListener(this);
                v++;
            }*/

                //vista.setOnClickListener(this);
            vista.findViewById(R.id.cardViewNumeroUno).setOnClickListener(this);

            vista.findViewById(R.id.cardViewNumeroDos).setOnClickListener(this);

            vista.findViewById(R.id.cardViewNumeroTres).setOnClickListener(this);

            vista.findViewById(R.id.cardViewNumeroCuatro).setOnClickListener(this);

            vista.findViewById(R.id.cardViewNumeroCinco).setOnClickListener(this);

            vista.findViewById(R.id.cardViewNumeroSeis).setOnClickListener(this);

            return vista;
        }

        @Override
        public void onClick(View view) {
            mShowingBack=false;
            flipCard();
           // Toast.makeText(getActivity(), "estoy dentro del fragment", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * A fragment representing the back of the card.
     */
    @SuppressLint("ValidFragment")
    public class CardBackFragment extends Fragment implements View.OnClickListener{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View  vista =inflater.inflate(R.layout.memoria_back_view, container, false);
            vista.findViewById(R.id.cardViewTarjetaBack).setOnClickListener(this);
            return vista;
        }

        @Override
        public void onClick(View view) {
            flipCard();
        }
    }





    @SuppressLint("ResourceType")
    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in, R.anim.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }


    /**
     * metodo que genera los numeros de la tarjeta
     */
    public void generaNumeros() {


        int number =0;
        int numeroSeleccionado=gameMemoriaUno.getNumberaleatorio();
        //se agrega numero a las lista de numeros

        if(numeroSeleccionado == -1){
            llamaResultado(super.getActividad(),  tFinal,  tInicio, Num3_5_1Activity.class,gameMemoriaUno.getNumeros(),gameMemoriaUno.getAciertosTotales(),gameMemoriaUno.getFallosTotales(),gameMemoriaUno.getNumEcxluidosList(),gameMemoriaUno.getNumeroPregunta());

        }else {


            gameMemoriaUno.addNumerosSet(numeroSeleccionado);
            for (int i = 1; i < 6; i++) {
                //obtiene numeros del costalito menos el numero seleccionado
                number = gameMemoriaUno.getNumeroArreglo();
                //agrego numeros
                gameMemoriaUno.addNumerosSet(number);
            }

            Iterator<Integer> iter = gameMemoriaUno.getNumerosSet().iterator();

            int contadorNumeros=0;
            while (iter.hasNext()) {
                int valor=iter.next().intValue();
                lista.add(valor);
                if(gameMemoriaUno.getDificultad()==contadorNumeros){
                    gameMemoriaUno.setNumerosElejidos(new int[valor]);
                }

                contadorNumeros+=1;
            }
            Collections.shuffle(lista, new Random());
            Collections.shuffle(lista, new Random());



        }


    }//fin de genera



    @Override
    public void onBackPressed() {
        backPrincipalMenu(1);
    }

}
