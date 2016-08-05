package com.example.hamsajama.wayfindandorient.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.hamsajama.wayfindandorient.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hamsajama on 31/07/2016.
 */
public class AppLocationManager implements LocationListener {
    private LocationManager locationManager;
    private Double latitude=0.0;
    private Double longitude=0.0;
    private GoogleMap mMap;

    private Criteria criteria;
    private String provider;

    public AppLocationManager(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, true);



        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,2,this);
        setMostRecentLocation(locationManager.getLastKnownLocation(provider));

    }
    private void setMostRecentLocation(Location lastKnownLocation) {
        if(lastKnownLocation==null){
            latitude=0.0;
            longitude=0.0;
        }else{
            latitude = lastKnownLocation.getLatitude();
            longitude = lastKnownLocation.getLongitude();
        }
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    Marker currentLocationMarker;
    @Override
    public void onLocationChanged(Location location) {

        Location old = new Location("OLD");
        old.setLatitude(0);
        old.setLongitude(0);

        double distance = location.distanceTo(old);

        Log.i("MyTag", "Distance: " + distance);

        float bearing=0;
        if(location.hasBearing()){
            bearing = location.getBearing();
        }
        if(currentLocationMarker!=null){
            currentLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public GoogleMap getmMap() { return mMap; }

    public void setmMap(GoogleMap mMap) { this.mMap = mMap; }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

