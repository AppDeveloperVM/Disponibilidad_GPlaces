package application.android.vicinflames.disponibilidad_gplaces.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;

import application.android.vicinflames.disponibilidad_gplaces.R;

/**
 * Created by VicInFlames on 30/12/2017.
 */

public class DataActivity extends AppCompatActivity{

    private TextView Textv;

    private DataActivity(){

    }


    protected void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        View view = inflater.inflate(R.layout.fragment_data, container, false);//Inflate Layout
        TextView text = (TextView) view.findViewById(R.id.coords_text);//Find textview Id

        //Get Argument that passed from activity in "data" key value
        //String getArgument = getArguments().getString("data");
        text.setText("COORDS!");//set string over textview
        //return view;//return view
    }





}
