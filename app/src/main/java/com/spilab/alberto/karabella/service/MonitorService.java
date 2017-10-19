package com.spilab.alberto.karabella.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.handler.EventHandler;
import com.spilab.alberto.karabella.sensor.SensorListener;
import com.spilab.alberto.karabella.utils.EventDataExtractor;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alberto on 14/09/17.
 *
 * This class demonstrates how an accessibility service can query
 * window content to improve the feedback given to the user.
 */
public class MonitorService extends AccessibilityService{

    static final String TAG = "RecorderService";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SS");

    private EventHandler eventHandler;

    private SensorListener sensorListener;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String time = dateFormat.format(Calendar.getInstance().getTime());
        // TODO gipsy kings event logger
       //EventDataExtractor.printEvent(event);

        eventHandler.computeEvent(event, time);
    }

    @Override
    public void onInterrupt() {
        Log.v(TAG, "Service interrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        Log.v(TAG, "Service connected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);

        // Setup realm config
        setupRealm();

        // Instantiate the event handler
        eventHandler = new EventHandler();

        // Initialize the sensorListener class
        sensorListener = new SensorListener(this.getApplicationContext());
        sensorListener.setupSensorManager();

     }

    private void setupRealm(){
        // Instance realm
        Realm.init(this.getApplicationContext());

        // Set up realm DB default configuration
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("karabella.realm")
                .schemaVersion(0)
                .build());
    }
}