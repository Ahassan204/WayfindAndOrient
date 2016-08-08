package com.example.hamsajama.wayfindandorient.routes;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Route1{

    ArrayList<LatLng> markerPoints;

    public Route1(){
        markerPoints = new ArrayList<>();
    }

    public static ArrayList<LatLng> getRoute(){
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

        return points;
    }

}
