package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public String latitud;
    public String longitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        latitud=intent.getStringExtra(ResturantActivity.EXTRA_LATITUD);
        longitud=intent.getStringExtra(ResturantActivity.EXTRA_LONGITUD);

        Log.d("recibe","recibe "+ latitud +" - "+ longitud);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        //setUpMapIfNeeded();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera ,
        LatLng sydney1 = new LatLng( Double.parseDouble(latitud), Double.parseDouble(longitud));
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Restaurant1"));

       /* LatLng sydney = new LatLng(-12.0875127, -77.052761);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Restaurant"));*/
        mMap.setMinZoomPreference(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));
    }

   /* private void setUpMapIfNeeded() {
        // Configuramos el objeto GoogleMaps con valores iniciales.
        if (mMap == null) {
            //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
            //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync();
            // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
            if (mMap != null) {
                // El objeto GoogleMap ha sido referenciado correctamente
                //ahora podemos manipular sus propiedades

                //Seteamos el tipo de mapa
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                //ACCESS_FINE_LOCATION
                      //  ACCESS_COARSE_LOCATION

                mMap.setMyLocationEnabled(true);
            }
        }
    }*/


}
