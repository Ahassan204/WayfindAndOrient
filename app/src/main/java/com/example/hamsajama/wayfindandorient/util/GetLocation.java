package com.example.hamsajama.wayfindandorient.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;


/**
 * Created by hamsa on 08/04/2016.
 */
public class GetLocation implements LocationListener {

    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private GoogleMap mMap;
    static final int MY_PERMISSION_REQUEST_LOCATION=1;
    Location myLocation;

    public GetLocation(Context context) {
        final LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        final Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        final String provider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return ;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000,   // 3 sec
                2, (android.location.LocationListener) this);
        setMostRecentLocation(locationManager.getLastKnownLocation(provider));
        myLocation = new Location("");
        myLocation.setLatitude(this.getLatitude());
        myLocation.setLongitude(this.getLongitude());
    }

    private void setMostRecentLocation(Location lastKnownLocation) {
        if(lastKnownLocation==null){
            latitude=0.0;
            setLatitude(latitude);
            longitude=0.0;
            setLongitude(longitude);
        }else{
            latitude = lastKnownLocation.getLatitude();
            longitude = lastKnownLocation.getLongitude();
        }
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
        setLatitude(latitude);
        longitude = location.getLongitude();
        setLongitude(longitude);
    }

    public GoogleMap getmMap() { return mMap; }

    public void setmMap(GoogleMap mMap) { this.mMap = mMap; }
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }
}
