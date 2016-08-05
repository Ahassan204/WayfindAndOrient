package com.example.hamsajama.wayfindandorient;


import static com.example.hamsajama.wayfindandorient.util.Constants.TAG;
import static com.example.hamsajama.wayfindandorient.util.Constants.ANDROID_BUILDING_ID;
import static com.example.hamsajama.wayfindandorient.util.Constants.ANDROID_BUILDING_LATITUDE;
import static com.example.hamsajama.wayfindandorient.util.Constants.ANDROID_BUILDING_LONGITUDE;
import static com.example.hamsajama.wayfindandorient.util.Constants.ANDROID_BUILDING_RADIUS_METERS;
import static com.example.hamsajama.wayfindandorient.util.Constants.CONNECTION_FAILURE_RESOLUTION_REQUEST;
import static com.example.hamsajama.wayfindandorient.util.Constants.GEOFENCE_EXPIRATION_TIME;
import static com.example.hamsajama.wayfindandorient.util.Constants.YERBA_BUENA_ID;
import static com.example.hamsajama.wayfindandorient.util.Constants.YERBA_BUENA_LATITUDE;
import static com.example.hamsajama.wayfindandorient.util.Constants.YERBA_BUENA_LONGITUDE;
import static com.example.hamsajama.wayfindandorient.util.Constants.YERBA_BUENA_RADIUS_METERS;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hamsajama.wayfindandorient.routes.Route1;
import com.example.hamsajama.wayfindandorient.util.GeofenceTransitionsIntentService;
import com.example.hamsajama.wayfindandorient.util.GetLocation;
import com.example.hamsajama.wayfindandorient.util.SimpleGeofence;
import com.example.hamsajama.wayfindandorient.util.SimpleGeofenceStore;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class NavigateActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    GetLocation myLocation;
    GoogleMap mMap;

    // Internal List of Geofence objects. In a real app, these might be provided by an API based on
    // locations within the user's proximity.
    List<Geofence> mGeofenceList;

    // These will store hard-coded geofences in this sample app.
    private SimpleGeofence mAndroidBuildingGeofence;
    private SimpleGeofence mYerbaBuenaGeofence;

    // Persistent storage for geofences.
    private SimpleGeofenceStore mGeofenceStorage;
    private LocationServices mLocationService;
    // Stores the PendingIntent used to request geofence monitoring.
    private PendingIntent mGeofenceRequestIntent;
    private GoogleApiClient mApiClient;

    // Defines the allowable request types (in this example, we only add geofences).
    private enum REQUEST_TYPE {
        ADD
    }

    private REQUEST_TYPE mRequestType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        //myLocation = new GetLocation(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Rather than displayng this activity, simply display a toast indicating that the geofence
        // service is being created. This should happen in less than a second.
        if (!isGooglePlayServicesAvailable()) {
            Log.e(TAG, "Google Play services unavailable.");
            finish();
            return;
        }

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();

        // Instantiate a new geofence storage area.
        mGeofenceStorage = new SimpleGeofenceStore(this);
        // Instantiate the current List of geofences.
        mGeofenceList = new ArrayList<Geofence>();
        createGeofences();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float bearing = 0;

        List<LatLng> points = Route1.getRoute();
        LatLng currentLocation = new LatLng(points.get(0).latitude, points.get(0).longitude);
        LatLng onPoint = new LatLng(points.get(4).latitude, points.get(4).longitude);
        LatLng destination = new LatLng(points.get(points.size() - 1).latitude, points.get(points.size() - 1).longitude);
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
                new PolylineOptions().addAll(points
                ).width(100).color(Color.rgb(74, 193, 232)).geodesic(true)
        );

        CircleOptions circleOptions = new CircleOptions()
                .center(currentLocation)
                .radius(50)
                .fillColor(0x40ff0000)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(2);
        mMap.addCircle(circleOptions);
        double tolerance = 10; // meters
        boolean isLocationOnPath = PolyUtil.isLocationOnPath(onPoint, points, true, tolerance);

        // Change the toast to pop up alert and vibration if it is false.
        if (isLocationOnPath) {
            Toast.makeText(getApplicationContext(), "Location is on path", Toast.LENGTH_SHORT).show();
        } else {
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

    public void toast(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:07951795900"));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
       // Toast.makeText(getApplicationContext(), "Latitude : " + myLocation.getLatitude() + "Longitude : " + myLocation.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Get the PendingIntent for the geofence monitoring request.
        // Send a request to add the current geofences.
        mGeofenceRequestIntent = getGeofenceTransitionPendingIntent();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.GeofencingApi.addGeofences(mApiClient, mGeofenceList,
                mGeofenceRequestIntent);
        Toast.makeText(this, getString(R.string.start_geofence_service), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (null != mGeofenceRequestIntent) {
            LocationServices.GeofencingApi.removeGeofences(mApiClient, mGeofenceRequestIntent);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // If the error has a resolution, start a Google Play services activity to resolve it.
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "Exception while resolving connection error.", e);
            }
        } else {
            int errorCode = connectionResult.getErrorCode();
            Log.e(TAG, "Connection to Google Play services failed with error code " + errorCode);
        }

    }
    /**
     * Checks if Google Play services is available.
     * @return true if it is.
     */
    private boolean isGooglePlayServicesAvailable() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == resultCode) {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Google Play services is available.");
            }
            return true;
        } else {
            Log.e(TAG, "Google Play services is unavailable.");
            return false;
        }
    }
    /**
     * Create a PendingIntent that triggers GeofenceTransitionIntentService when a geofence
     * transition occurs.
     */
    private PendingIntent getGeofenceTransitionPendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void createGeofences() {
        // Create internal "flattened" objects containing the geofence data.
        mAndroidBuildingGeofence = new SimpleGeofence(
                ANDROID_BUILDING_ID,                // geofenceId.
                ANDROID_BUILDING_LATITUDE,
                ANDROID_BUILDING_LONGITUDE,
                ANDROID_BUILDING_RADIUS_METERS,
                GEOFENCE_EXPIRATION_TIME,
                Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT
        );
        mYerbaBuenaGeofence = new SimpleGeofence(
                YERBA_BUENA_ID,                // geofenceId.
                YERBA_BUENA_LATITUDE,
                YERBA_BUENA_LONGITUDE,
                YERBA_BUENA_RADIUS_METERS,
                GEOFENCE_EXPIRATION_TIME,
                Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT
        );

        // Store these flat versions in SharedPreferences and add them to the geofence list.
        mGeofenceStorage.setGeofence(ANDROID_BUILDING_ID, mAndroidBuildingGeofence);
        mGeofenceStorage.setGeofence(YERBA_BUENA_ID, mYerbaBuenaGeofence);
        mGeofenceList.add(mAndroidBuildingGeofence.toGeofence());
        mGeofenceList.add(mYerbaBuenaGeofence.toGeofence());
    }
}
