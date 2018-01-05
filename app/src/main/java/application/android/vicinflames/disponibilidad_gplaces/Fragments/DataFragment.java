package application.android.vicinflames.disponibilidad_gplaces.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import org.w3c.dom.Text;

import application.android.vicinflames.disponibilidad_gplaces.R;

/**
 * Created by VicInFlames on 30/12/2017.
 */

public class DataFragment extends Fragment {

    private View rootView;

    private TextView coords;
    private TextView coords_text;
    private TextView address;
    private TextView address_text;
    private ImageView img;

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.activity_data, container, false);

        coords_text = (TextView)rootView.findViewById(R.id.coords_text);

        return rootView;
    }

    public void renderText(String text){
        coords_text.setText(text);
    }
}
