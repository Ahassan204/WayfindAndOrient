package com.example.hamsajama.wayfindandorient.routes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hamsajama.wayfindandorient.R;
import com.example.hamsajama.wayfindandorient.util.GetLocation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class Route1 extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    GetLocation locationManager;

    ArrayList<LatLng> markerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        markerPoints = new ArrayList<LatLng>();
        /********** get Gps location service LocationManager object ***********/
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    protected void onResume() {
        super.onResume();
        locationManager = new GetLocation(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        route1();
        locationManager.setmMap(mMap);
        LatLng me = new LatLng(locationManager.getLatitude(),locationManager.getLongitude());
        markerPoints.add(me);
    }
    void route1(){
        ArrayList<LatLng>points = new ArrayList<>();
        LatLng acton = new LatLng(51.505374, -0.273377);
        LatLng acton1 = new LatLng(51.505434, -0.273952);
        LatLng acton2 = new LatLng(51.506148, -0.274348);
        LatLng acton3 = new LatLng(51.506771, -0.274254);
        LatLng acton4 = new LatLng(51.507272, -0.273551);
        LatLng acton5 = new LatLng(51.507906, -0.272366);
        LatLng acton6 = new LatLng(51.507996, -0.271322);
        LatLng acton7 = new LatLng(51.507775, -0.271180);
        LatLng acton8 = new LatLng(51.507377, -0.271268);
        LatLng acton9 = new LatLng(51.507218, -0.271349);
        LatLng acton10 = new LatLng(51.507128, -0.270890);
        LatLng acton11 = new LatLng(51.506801, -0.269736);
        LatLng acton12 = new LatLng(51.506689, -0.268818);
        LatLng actonFinish = new LatLng(51.506612, -0.266909);


        points.add(acton);
        points.add(acton1);
        points.add(acton2);
        points.add(acton3);
        points.add(acton4);
        points.add(acton5);
        points.add(acton6);
        points.add(acton7);
        points.add(acton8);
        points.add(acton9);
        points.add(acton10);
        points.add(acton11);
        points.add(acton12);
        points.add(actonFinish);

        LatLng actoncrossing1 = new LatLng(51.506416, -0.274355);
        LatLng actoncrossing2 = new LatLng(51.507683, -0.272964);
        LatLng actoncrossing3 = new LatLng(51.507297, -0.271360);
        LatLng actoncrossing4 = new LatLng(51.507182, -0.271279);
        LatLng actoncrossing5 = new LatLng(51.506891, -0.270075);
        LatLng actoncrossing6 = new LatLng(51.506727, -0.269426);
        LatLng actoncrossing7 = new LatLng(51.506654, -0.268141);

        for (int i = 0; i < points.size() - 1; i++) {
            LatLng src = points.get(i);
            LatLng dest = points.get(i + 1);

            mMap.addPolyline(
                    new PolylineOptions().add(
                            new LatLng(src.latitude, src.longitude),
                            new LatLng(dest.latitude,dest.longitude)
                    ).width(100).color(Color.rgb(74, 193, 232)).geodesic(true)
            );
        }

//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing1)
//                //.rotation(250)
//                //.flat(true)
//                .title("Careful when crossing "));
//
//
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.startpoint))
//                .position(acton)
//                .title("Start point "));
//
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.trafficsign))
//                .position(actoncrossing2)
//                .title("Careful when crossing "));
//
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing3)
//                .title("Careful when crossing "));
//
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing4)
//                .title("Careful when crossing "));
//
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing5)
//                .title("Careful when crossing "));
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing6)
//                .title("Careful when crossing "));
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zebracrossing))
//                .position(actoncrossing7)
//                .title("Careful when crossing "));
//
    }

}
