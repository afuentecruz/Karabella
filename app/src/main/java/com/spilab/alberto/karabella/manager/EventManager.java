package com.spilab.alberto.karabella.manager;

import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.scrapper.MainScrapper;
import com.spilab.alberto.karabella.utils.EventDataExtractor;

/**
 * Created by alberto on 26/09/17.
 *
 * Main class that process the accessibility events, filter and saves them in MainScrapper
 */

public class EventManager {

    private MainScrapper mainScrapper = new MainScrapper();

    public void computeEvent(AccessibilityEvent event, String timestamp){

        if(event.getPackageName().equals("com.google.android.inputmethod.latin"))
            return;


        switch(EventDataExtractor.getEventType(event)){
            case "TYPE_WINDOW_STATE_CHANGED":
                // Add new registry to TAD
                mainScrapper.addAppLaunch(event, timestamp);
                break;
            default:
                // Add the interaction to App TAD registry
                if(!EventDataExtractor.getEventText(event).equals("")){
                    // If the event has useful information
                    mainScrapper.addAppInteraction(event, timestamp);
                }
                break;
        }

        //mainScrapper.addEvent(event);
    }
}
