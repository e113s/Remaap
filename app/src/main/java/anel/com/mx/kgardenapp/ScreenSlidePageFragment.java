package anel.com.mx.kgardenapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by ddarredondo on 27/03/2018.
 */

public class ScreenSlidePageFragment extends android.support.v4.app.Fragment {

    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutToShow =0;
        layoutToShow=  getFragment(getPageNumber());

        // Inflate the layout containing a title and body text.
        //ViewGroup rootView = (ViewGroup) inflater
          //      .inflate(R.layout.fragment_screen_slide_page, container, false);

        ViewGroup rootView = (ViewGroup) inflater
             .inflate(layoutToShow, container, false);


        return rootView;
    }



    public int getFragment(int layoutNumber){

        HashMap<Integer,Integer> mapFragments = new HashMap<>();

        mapFragments.put(0,R.layout.fragment_animation_slide);
        mapFragments.put(1,R.layout.fragment_screen_slide_page);
        mapFragments.put(2,R.layout.fragment_name_slide);
        mapFragments.put(3,R.layout.fragment_age_slide);
        mapFragments.put(4,R.layout.fragment_sexo_slide);



        return mapFragments.get(layoutNumber).intValue();
    }


    public int getPageNumber() {
        return mPageNumber;
    }
}
