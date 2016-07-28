package com.example.hamsajama.wayfindandorient;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

public class NavigateActivity extends FragmentActivity implements OnMapReadyCallback {
    GetLocation myLocation;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        myLocation = new GetLocation(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float bearing=0;

        LatLng currentLocation = new LatLng(51.570207, -0.271443);
        LatLng destination = new LatLng(51.566493, -0.265649);
        LatLng offPath = new LatLng(51.567018, -0.263542);
        LatLng onPoint = new LatLng(51.567604, -0.267434);

        List<LatLng> points = new ArrayList<>();
        points.add(currentLocation);
        points.add(destination);

//        double lat = myLocation.getLatitude();
//        double lon = myLocation.getLongitude();
//
//        if(myLocation!=null){
//            currentLocation = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
//        }else {
//            currentLocation = new LatLng(0,0);
//        }

        //LatLng currentLocation = new LatLng(0,0);

        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
                .position(currentLocation)
                .title("Current Location")
                .rotation(360));

        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
                .position(destination)
                .title("Current Location")
                .rotation(360));

        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
                .position(onPoint)
                .title("Current Location")
                .rotation(360));
        mMap.addPolyline(
                new PolylineOptions().add(currentLocation,destination
                ).width(10).color(Color.rgb(74, 193, 232)).geodesic(true)
        );

        double tolerance = 10; // meters
        boolean isLocationOnPath = PolyUtil.isLocationOnPath(onPoint, points, true, tolerance);

        // Change the toast to pop up alert and vibration if it is false.
        if(isLocationOnPath){
            Toast.makeText(getApplicationContext(), "Location is on path", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Location is NOT on path", Toast.LENGTH_SHORT).show();
        }

        // Move the camera instantly to the user with a zoom of 20.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLocation)
                .zoom(13)
                .bearing(bearing)
                .tilt(80)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    public void toast(View v){
        Toast.makeText(getApplicationContext(), "Latitude : " + myLocation.getLatitude()+"Longitude : "+myLocation.getLongitude(), Toast.LENGTH_SHORT).show();
    }
}
