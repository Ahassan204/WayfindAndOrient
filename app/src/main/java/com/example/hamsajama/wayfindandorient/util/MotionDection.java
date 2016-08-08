package com.example.hamsajama.wayfindandorient.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by hamsajama on 05/08/2016.
 */
public class MotionDection implements SensorEventListener {
    private static final String TAG = "Sensor";

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] mGravity;
    private float[] mGeomagnetic;

    public MotionDection(Context context) {
        // Setup the sensors
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer == null) {
            Log.d(TAG, "accelerometer is null");
        }
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer == null) {
            Log.d(TAG, "magnetometer is null");
        }
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        // Detect the window position
        switch (wm.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Log.d(TAG, "Rotation 0!!");
                break;
            case Surface.ROTATION_90:
                Log.d(TAG, "Rotation 90!!");
                break;
            case Surface.ROTATION_180:
                Log.d(TAG, "Rotation 180");
                break;
            case Surface.ROTATION_270:
                Log.d(TAG, "Rotation 270");
                break;
            default:
                Log.w(TAG, "Rotation unknown");
                break;
        }
    }
    /**
     * Convert degrees to absolute tilt value between 0-100
     */
    private int degreesToPower(int degrees) {
        // Tilted back towards user more than -90 deg
        if (degrees < -90) {
            degrees = -90;
        }
        // Tilted forward past 0 deg
        else if (degrees > 0) {
            degrees = 0;
        }
        // Normalize into a positive value
        degrees *= -1;
        // Invert from 90-0 to 0-90
        degrees = 90 - degrees;
        // Convert to scale of 0-100
        float degFloat = degrees / 90f * 100f;
        return (int) degFloat;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d(TAG, "onSensorChanged()");
        if (event.values == null) {
            Log.w(TAG, "event.values is null");
            return;
        }
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mGravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGeomagnetic = event.values;
                break;
            default:
                Log.w(TAG, "Unknown sensor type " + sensorType);
                return;
        }
        if (mGravity == null) {
            Log.w(TAG, "mGravity is null");
            return;
        }
        if (mGeomagnetic == null) {
            Log.w(TAG, "mGeomagnetic is null");
            return;
        }
        float R[] = new float[9];
        if (! SensorManager.getRotationMatrix(R, null, mGravity, mGeomagnetic)) {
            Log.w(TAG, "getRotationMatrix() failed");
            return;
        }

        float orientation[] = new float[9];
        SensorManager.getOrientation(R, orientation);
        // Orientation contains: azimuth, pitch and roll - we'll use roll
        float roll = orientation[2];
        int rollDeg = (int) Math.round(Math.toDegrees(roll));
        int power = degreesToPower(rollDeg);
        //Log.d(TAG, "deg=" + rollDeg + " power=" + power);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
