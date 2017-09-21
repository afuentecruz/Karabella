package com.spilab.alberto.karabella.catcher;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

import java.util.Date;

/**
 * Created by alberto on 18/09/17.
 *
 * Called when MonitorService detects a change of the foreground activity
 */

public class AppCatcher {

    static final String TAG = "AppCatcher";



    public AppCatcher() {}

    public void manageActivityLaunch(AccessibilityEvent event, String timestamp){
        if (event.getEventType()!= AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED || EventDataExtractor.getEventText(event).equals("")) {
            return;
        }

        Log.d(TAG, String.format(
                "App Launched: [text] %s at [time] %s ",
                EventDataExtractor.getEventText(event), timestamp));
    }



}
