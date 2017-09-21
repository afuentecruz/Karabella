package com.spilab.alberto.karabella.utils;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.manager.EventManager;

/**
 * Created by alberto on 19/09/17.
 */

public class EventDataExtractor {

    private static final String TAG = "EventDataExtractor";

    public static String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void printEvent(AccessibilityEvent event){

        Log.d(EventManager.TAG, String.format(
                "[type] %s [eventId] %s [class] %s [package] %s [time] %s [text] %s",
                getEventType(event), event.getEventType(), event.getClassName(), event.getPackageName(),
                event.getEventTime(), getEventText(event))
        );
    }

    private static String getEventType(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                return "TYPE_NOTIFICATION_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                return "TYPE_VIEW_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                return "TYPE_VIEW_FOCUSED";
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                return "TYPE_VIEW_LONG_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                return "TYPE_VIEW_SELECTED";
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                return "TYPE_WINDOW_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                // TODO Event that catch written text
                return "TYPE_VIEW_TEXT_CHANGED";
        }
        return "default";
    }

}