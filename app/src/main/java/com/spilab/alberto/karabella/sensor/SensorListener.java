package com.spilab.alberto.karabella.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by alberto on 3/10/17.
 *
 * SensorListener, implements SensorEventListener2.
 *
 * This class must listen for changes on the sensors added to sensor manager.
 */

public class SensorListener implements SensorEventListener2{

    private static final String TAG = "SensorListener";

    private SensorManager sensorManager;

    private Sensor sensorLight;

    private Context context;

    public SensorListener(Context context){
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setupSensorManager(){
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    /**
     * onSensorChanged, catch all sensor changes produced in the system.
     * @param sensorEvent Sensor information changed.
     * */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        float lux = sensorEvent.values[0];

        //Log.d(TAG, Float.toString(lux));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
