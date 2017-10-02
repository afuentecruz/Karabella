package com.spilab.alberto.karabella.handler;

import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.scrapper.GeneralScrapper;
import com.spilab.alberto.karabella.scrapper.GmailScrapper;
import com.spilab.alberto.karabella.utils.EventDataExtractor;
import com.spilab.alberto.karabella.utils.Strings;

/**
 * Created by alberto on 26/09/17.
 *
 * Main class that process the accessibility events, filter and saves them in GeneralScrapper
 */

public class EventHandler {

    private GeneralScrapper generalScrapper = new GeneralScrapper();

    private GmailScrapper gmailScrapper = new GmailScrapper();

    public void computeEvent(AccessibilityEvent event, String timestamp){

        if(event.getText() == null || EventDataExtractor.getEventText(event).equals(""))
            return; // If the event has no relevant information... exit.

        // TODO borrar este if cuando la papeleta esté solucionada
        if(event.getPackageName() == null || event.getPackageName().equals("com.google.android.inputmethod.latin"))
            return;

        // Switch event Package Name to discern the app that fired the event
        switch(event.getPackageName().toString()){
            case Strings.PACKAGE_GMAIL:
                // Do something with gmail
                gmailScrapper.getData(event, timestamp);
                break;
            default:
                break;
        }



       /* switch(EventDataExtractor.getEventType(event)){
            case "TYPE_WINDOW_STATE_CHANGED":
                // Add new registry to TAD
                generalScrapper.addAppLaunch(event, timestamp);
                break;
            default:
                // Add the interaction to App TAD registry
                if(!EventDataExtractor.getEventText(event).equals("")){
                    // If the event has useful information
                    generalScrapper.addAppInteraction(event, timestamp);
                }
                break;
        }*/



        //generalScrapper.addEvent(event);
    }
}
