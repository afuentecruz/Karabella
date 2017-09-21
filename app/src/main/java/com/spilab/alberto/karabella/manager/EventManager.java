package com.spilab.alberto.karabella.manager;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.EventLog;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.spilab.alberto.karabella.catcher.*;
import com.spilab.alberto.karabella.utils.EventDataExtractor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alberto on 18/09/17.
 *
 * This class manages all the events captured by monitor service.
 */

public class EventManager {

    public static final String TAG = "EventManager";

    private AppCatcher appCatcher = new AppCatcher();

    private TextCatcher textCatcher = new TextCatcher();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


    public EventManager(){}


    public void manageEvent(AccessibilityEvent event){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss.SS z");
        String time = df.format(Calendar.getInstance().getTime());


        switch (event.getEventType()) {

            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                //textCatcher.printInputText(event, time);

                Log.d(TAG, "TYPE_VIEW_SCROLLED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.d(TAG, "TYPE_VIEW_CLICKED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                Log.d(TAG, "TYPE_VIEW_FOCUSED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                Log.d(TAG, "TYPE_NOTIFICATION_STATE_CHANGED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                Log.d(TAG, "TYPE_VIEW_LONG_CLICKED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                Log.d(TAG, "TYPE_VIEW_SELECTED");
                EventDataExtractor.printEvent(event);
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                // App launch
                Log.d(TAG, "TYPE_WINDOW_STATE_CHANGED");
                EventDataExtractor.printEvent(event);
                //appCatcher.manageActivityLaunch(event, time);
                //textCatcher.setDataContext(EventDataExtractor.getEventText(event));
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                Log.d(TAG, "TYPE_VIEW_TEXT_SELECTION_CHANGED");
                EventDataExtractor.printEvent(event);
                //this.textCatcher.getInputText(event, time);
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: // Text input
                Log.d(TAG, "TYPE_VIEW_TEXT_CHANGED");
                EventDataExtractor.printEvent(event);
                //this.textCatcher.getInputText(event, time);

                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.d(TAG, "TYPE_WINDOW_CONTENT_CHANGED");
                EventDataExtractor.printEvent(event);

                // User text input
                //textCatcher.confirmUserInput(event, time);
                break;
            default:
                Log.d(TAG, "DEFAULT");
                EventDataExtractor.printEvent(event);
                break;
        }
        if(event.getPackageName().equals("com.google.android.gm")){
            EventDataExtractor.printEvent(event);
        }
    }
}
