package com.example.hamsajama.wayfindandorient.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;



public class GetLocation implements LocationListener,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {
    Context context;
    private final String TAG = "LOC_RECURRING_SAMPLE";

    // Constants that define how often location updates will be delivered
    private final long LOC_UPDATE_INTERVAL = 10000; // 10s in milliseconds
    private final long LOC_FASTEST_UPDATE = 5000; // 5s in milliseconds
    protected GoogleApiClient mGoogleApiClient;

    public GetLocation(Context context) {
        this.context = context;
        // build the Play Services client object
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}