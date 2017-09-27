package com.spilab.alberto.karabella.events;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

/**
 * Created by alberto on 21/09/17.
 *
 * Window state changed - represents the event of opening a PopupWindow, Menu, Dialog, etc.
 */

public class TypeWindowStateChanged extends AccessEvent {

    static final String eventType = "TYPE_WINDOW_STATE_CHANGED";

    private static final String TAG = "TypeWindowStateChanged";

    public TypeWindowStateChanged(AccessibilityEvent event, String timestamp){
        super(event, eventType, timestamp);
    }
}
