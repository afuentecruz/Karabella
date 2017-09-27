package com.spilab.alberto.karabella.events;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

/**
 * Created by alberto on 21/09/17.
 *
 * Represents a plain accessibility event
 */

public class AccessEvent {

    private static final String TAG = "AccessEvent";
    private AccessibilityEvent event;

    private String eventType = "";
    private String timestamp = "";

    public AccessEvent(){}

    public AccessEvent(String eventType){
        this.eventType = eventType;
    }

    public AccessEvent(AccessibilityEvent event, String eventType){
        EventDataExtractor.printEvent(event);
        event.describeContents();
        this.event = event;
        this.eventType = eventType;
    }

    public AccessEvent(AccessibilityEvent event, String eventType, String timestamp){
        //if(event.getText().equals(""))
          //  return;

        EventDataExtractor.printEvent(event);

        this.event = event;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    public String getEventType(){
        return this.eventType;
    }

    public AccessibilityEvent getEvent(){
        return this.event;
    }
}
