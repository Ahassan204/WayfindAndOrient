package com.example.hamsajama.wayfindandorient.geofenceUtil;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by hamsajama on 10/08/2016.
 */
public class Constants {
    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.example.hamsajama.wayfindandorient.geofenceUtil";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 20; // 1 mile, 1.6 km

    /**
     * Map for storing information about landmarks London area.
     */
    public static final HashMap<String, LatLng> LONDON_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // My house.
        LONDON_LANDMARKS.put("My House", new LatLng(51.505374, -0.273377));

        // Googleplex.
        LONDON_LANDMARKS.put("Acton crossroad", new LatLng(51.507272,-0.273551));
    }
}
