package com.spilab.alberto.karabella.events;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

/**
 * Created by alberto on 21/09/17.
 *
 * View clicked - represents the event of clicking on a View like Button, CompoundButton, etc.
 */

public class TypeViewClicked extends AccessEvent {

    static final String eventType = "TYPE_VIEW_CLICKED";

    private static final String TAG = "TypeViewClicked";

    public TypeViewClicked(AccessibilityEvent event, String timestamp){
        super(event, eventType, timestamp);
    }

}
