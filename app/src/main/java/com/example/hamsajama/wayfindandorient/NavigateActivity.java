package com.example.hamsajama.wayfindandorient;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hamsajama.wayfindandorient.util.GetLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

public class NavigateActivity extends FragmentActivity implements OnMapReadyCallback {
    GetLocation myLocation;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        myLocation = new GetLocation(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float bearing=0;
        if(myLocation.getMyLocation().hasBearing()){
            bearing = myLocation.getMyLocation().getBearing();
        }

//        LatLng currentLocation;
//        double lat = myLocation.getLatitude();
//        double lon = myLocation.getLongitude();
//
//        if(myLocation!=null){
//            currentLocation = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
//        }else {
//            currentLocation = new LatLng(0,0);
//        }

        LatLng currentLocation = new LatLng(0,0);

        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
                .position(currentLocation)
                .title("Current Location")
                .rotation(360));
        // Move the camera instantly to the user with a zoom of 20.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLocation)
                .zoom(20)
                .bearing(bearing)
                .tilt(80)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    public void toast(View v){
        Toast.makeText(getApplicationContext(), "Latitude : " + myLocation.getLatitude()+"Longitude : "+myLocation.getLongitude(), Toast.LENGTH_SHORT).show();
    }
}
