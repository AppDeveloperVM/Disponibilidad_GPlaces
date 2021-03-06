package application.android.vicinflames.disponibilidad_gplaces.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import application.android.vicinflames.disponibilidad_gplaces.Fragments.DataFragment;
import application.android.vicinflames.disponibilidad_gplaces.Fragments.MpFragment;
import application.android.vicinflames.disponibilidad_gplaces.R;

import static android.location.LocationManager.GPS_PROVIDER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(20);

        // Add a marker in Sydney and move the camera
        LatLng majorca = new LatLng(39.57031860238611, 2.6493893429931337);
        mMap.addMarker(new MarkerOptions().position(majorca).title("Marker in Majorca").draggable(true));




        CameraPosition camera = new CameraPosition.Builder()
                .target(majorca)
                .zoom(18)    //limite -> 21
                .bearing(90) //bearing - orientación de la cámara hacia el este -> 365º
                .tilt(90)    //tilt - inclinación -> limite de 90º
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

        //click sobre el mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Click on :\n" +
                        " Lat:" + latLng.latitude +
                        ",Long:" + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
        //click continuado sobre el mapa
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Long Click on :\n" +
                        " Lat:" + latLng.latitude +
                        ",Long:" + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        ///interacción con marcador
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(MapsActivity.this, ",Marker dragged to :\n" +
                        " Lat:" + marker.getPosition().latitude +
                        ",Long:" + marker.getPosition().longitude , Toast.LENGTH_SHORT).show();

            }
        });


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(majorca));
    }



}
