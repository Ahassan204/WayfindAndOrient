package com.example.hamsajama.wayfindandorient.util;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.hamsajama.wayfindandorient.R;
import com.example.hamsajama.wayfindandorient.routes.Route1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

/**
 * Created by hamsajama on 12/08/2016.
 */
public class GetMyLocation implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    Context context;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public GetMyLocation(Context context) {
        this.context = context;

        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000); // Update location every second

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

        }
    }
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context,
                            "permission denied, ...:(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    private Double latitude;
    private Double longitude;
    Marker currentLocationMarker;
    GoogleMap mMap;
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
        List<LatLng> points = Route1.getRoute();
        LatLng myPosition = new LatLng(latitude, longitude);


        double tolerance = 10; // meters
        boolean isLocationOnPath = PolyUtil.isLocationOnPath(myPosition, points, true, tolerance);
        if(getmMap()!=null){
            currentLocationMarker = getmMap().addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.myposition))
                    .position(myPosition)
                    .title("Current Location")
                    .rotation(360));
            // Move the camera instantly to the user with a zoom of 20.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myPosition)
                    .zoom(20)
                    .bearing(bearing)
                    .tilt(80)
                    .build();
            getmMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public GoogleMap getmMap() { return mMap; }

    public void setmMap(GoogleMap mMap) { this.mMap = mMap; }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        buildGoogleApiClient();
    }

}
