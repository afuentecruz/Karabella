package com.spilab.alberto.karabella.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.manager.EventManager;

/**
 * Created by alberto on 14/09/17.
 *
 * This class demonstrates how an accessibility service can query
 * window content to improve the feedback given to the user.
 */
public class MonitorService extends AccessibilityService {

    static final String TAG = "RecorderService";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    EventManager eventManager = new EventManager();


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //String time = dateFormat.format(Calendar.getInstance().getTime());

        eventManager.computeEvent(event);
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

        //

    }


}