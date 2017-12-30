package application.android.vicinflames.disponibilidad_gplaces.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.android.vicinflames.disponibilidad_gplaces.R;

/**
 * Created by VicInFlames on 30/12/2017.
 */

public class DataFragment extends Fragment{

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //idiomas
        //getString(R.string.welcome);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }
}
