package com.example.onlinenurserystore;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.onlinenurserystore.model.LatLong;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

        List<LatLong> latlngs = new ArrayList<>();
        latlngs.add(new LatLong(27.706195, 85.3278509, "Softwarica College Of IT & E-Commerce"));
        latlngs.add(new LatLong(27.717244, 85.3239595, "XYZ Nursery Store"));
        latlngs.add(new LatLong(27.724086, 85.2939873, "Paradise Nursery\n"));
        latlngs.add(new LatLong(27.690162, 85.3047993, "All Plants Hub"));
        latlngs.add(new LatLong(27.6901614, 85.3047993, "Flowers Centre - New Baneshwor\n"));
        latlngs.add(new LatLong(27.7145697, 85.3132025, "Evergreen Store\n"));
        latlngs.add(new LatLong(27.7145697, 85.3132025, "Nursery house"));
        latlngs.add(new LatLong(27.7151036, 85.3188539, "Take Away Plants Nepal"));

        CameraUpdate center, zoom;
        for (int i = 0; i < latlngs.size(); i++) {
            center = CameraUpdateFactory.newLatLng(new LatLng(latlngs.get(i).getLat(), latlngs.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(10);
            mMap.addMarker(new MarkerOptions().position(new LatLng(latlngs.get(i).getLat(), latlngs.get(i).getLon()))
                    .title(latlngs.get(i).getMarker()));

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }

    }
}