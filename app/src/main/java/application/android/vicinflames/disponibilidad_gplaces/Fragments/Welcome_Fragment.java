package application.android.vicinflames.disponibilidad_gplaces.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import application.android.vicinflames.disponibilidad_gplaces.R;

/**
 * Created by VicInFlames on 30/12/2017.
 */

public class Welcome_Fragment extends Fragment {

    public Welcome_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //idiomas
        //getString(R.string.welcome);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }
}
