package application.android.vicinflames.disponibilidad_gplaces.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import application.android.vicinflames.disponibilidad_gplaces.Activities.MainActivity;
import application.android.vicinflames.disponibilidad_gplaces.Activities.MapsActivity;
import application.android.vicinflames.disponibilidad_gplaces.R;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by VicInFlames on 30/12/2017.
 */

public class MpFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1;

    private View rootView;
    private MapView mapView;
    private GoogleMap gmap;

    private FloatingActionButton fab;

    private LocationManager locationManager;
    private Location currentLocation;

    private Marker marker = null;
    private CameraPosition camera;

    public MpFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        //fab.setEnabled(true);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.map);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            //asíncrono
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);


        //comprobar si los permisos de POSICION están activados
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if ( ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                        &&
                        ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) ) {

                        Toast.makeText(getContext(), "Permisos no habilitados!",Toast.LENGTH_SHORT).show();

                        //en caso de no estar activados los permisos, se piden!
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                } else {

                    Toast.makeText(getContext(), "Permisos habilitados!",Toast.LENGTH_SHORT).show();

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);


                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            return;
        }


        gmap.setMyLocationEnabled(true);
        //desactivando boton de "llevar a mi posicion"
        gmap.getUiSettings().setMyLocationButtonEnabled(false);


        //debería de crear un checking de conexiones, si existe la conexion a internet omitir gps, ya que tarda mucho
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

    }

    private boolean isGPSEnabled() {
        try {
            //Comprobar si el GPS está activado
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            //gpsSignal devuelve 0 si no está activado , y 1 si está activado

            if (gpsSignal == 0) {
                //El GPS no está activado
                return false;
            } else {
                return true;
            }

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }


    private void showInfoAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle("Señal GPS")
                .setMessage("No tienes la señal de GPS activada, deseas activarla ahora?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //entonces llevamos al usuario a la ventana de activación de GPS para activarlo manualmente
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel", null)
                .show();
    }


    @Override
    public void onClick(View view) {

        if (!this.isGPSEnabled()) {
            showInfoAlert();
        } else {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }



            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            currentLocation = location;

            if (currentLocation != null) {
                createOrUpdateMarkerByLocation(location);
                zoomToLocation(location);
            }
        }
    }

    private void createOrUpdateMarkerByLocation(Location location){
        if (marker == null) {
            marker = gmap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).draggable(true));
        } else {
            marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
        }

    }

    private void zoomToLocation(Location location){
        camera = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(18)
                .bearing(0)
                .tilt(30)
                .build();

        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    @Override
    public void onLocationChanged(Location location) {

        fab.setEnabled(true);
        Toast.makeText(getContext(), "Changed! " + "\nLong: " + location.getLongitude() + "\nLat: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        createOrUpdateMarkerByLocation(location);

    }




    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    //PERMISOS
    //sobreescribimos un metodo
    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    //checkeamos si el permiso correspondiente está garantizado

    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    */


}
