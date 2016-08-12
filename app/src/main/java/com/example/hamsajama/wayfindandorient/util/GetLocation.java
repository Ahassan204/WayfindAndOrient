package com.example.hamsajama.wayfindandorient.util;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.location.LocationListener;
import android.widget.Toast;

import com.example.hamsajama.wayfindandorient.R;
import com.example.hamsajama.wayfindandorient.geofenceUtil.GeoConstants;
import com.example.hamsajama.wayfindandorient.routes.Route1;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;
import java.util.Map;


/**
 * Created by hamsa on 08/04/2016.
 */
public class GetLocation {

//    private Double latitude;
//    private Double longitude;
//    private float bearing = 0;
//    GoogleMap mMap;
//    LocationManager locationManager;
//    Criteria criteria;
//    String provider;
//    static final int MY_PERMISSION_REQUEST_LOCATION = 1;
//    Location myLocation;
//
//    public GetLocation(Context context) {
//        locationManager = (LocationManager) context
//                .getSystemService(Context.LOCATION_SERVICE);
//        criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        provider = locationManager.getBestProvider(criteria, true);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 2, this);
//
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        setMostRecentLocation(locationManager.getLastKnownLocation(provider));
//    }
//
//    private void setMostRecentLocation(Location lastKnownLocation) {
//        if(lastKnownLocation==null){
//            setLatitude(0.0);
//            setLongitude(0.0);
//        }else{
//            setLatitude(lastKnownLocation.getLatitude());
//            setLongitude(lastKnownLocation.getLongitude());
//        }
//    }
//    Marker currentLocationMarker;
//
//    @Override
//    public void onLocationChanged(Location location) {
//        Location old = new Location("OLD");
//        old.setLatitude(0);
//        old.setLongitude(0);
//
//        double distance = location.distanceTo(old);
//
//        Log.i("MyTag", "Distance: " + distance);
//
//        float bearing=0;
//        if(location.hasBearing()){
//            bearing = location.getBearing();
//        }
//        if(currentLocationMarker!=null){
//            currentLocationMarker.remove();
//        }
//        if(location!=null){
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//        }else{
//            latitude = getLatitude();
//            longitude = getLongitude();
//        }
//
//
//        List<LatLng> points = Route1.getRoute();
//        LatLng myPosition = new LatLng(latitude, longitude);
//
//
//        double tolerance = 10; // meters
//        boolean isLocationOnPath = PolyUtil.isLocationOnPath(myPosition, points, true, tolerance);
//
////        // Change the toast to pop up alert and vibration if it is false.
////        if (isLocationOnPath) {
////            Toast.makeText(getApplicationContext(), "Location is on path", Toast.LENGTH_SHORT).show();
////        } else {
////            Toast.makeText(getApplicationContext(), "Location is NOT on path", Toast.LENGTH_SHORT).show();
////        }
//
//        if(getmMap()!=null){
//            currentLocationMarker = getmMap().addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
//                    .position(myPosition)
//                    .title("Current Location")
//                    .rotation(360));
//            // Move the camera instantly to the user with a zoom of 20.
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(myPosition)
//                    .zoom(20)
//                    .bearing(bearing)
//                    .tilt(80)
//                    .build();
//            getmMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        }
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Location getMyLocation() {
//        return myLocation;
//    }
//
//    public void setMyLocation(Location myLocation) {
//        this.myLocation = myLocation;
//    }
//
//    public float getBearing() {
//        return bearing;
//    }
//
//    public void setBearing(float bearing) {
//        this.bearing = bearing;
//    }
//    public GoogleMap getmMap() { return mMap; }
//
//    public void setmMap(GoogleMap mMap) { this.mMap = mMap; }

}
