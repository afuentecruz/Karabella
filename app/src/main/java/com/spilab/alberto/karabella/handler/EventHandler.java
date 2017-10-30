package com.spilab.alberto.karabella.handler;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.manager.GmailManager;
import com.spilab.alberto.karabella.model.GmailDB;
import com.spilab.alberto.karabella.scrapper.GeneralScrapper;
import com.spilab.alberto.karabella.scrapper.GmailScrapper;
import com.spilab.alberto.karabella.scrapper.WhatsappScrapper;
import com.spilab.alberto.karabella.utils.EventDataExtractor;
import com.spilab.alberto.karabella.utils.Strings;

import java.util.List;

/**
 * Created by alberto on 26/09/17.
 *
 * Main class that process the accessibility events, filter and saves them in GeneralScrapper
 */

public class EventHandler {

    private static final String TAG = "EventHandler";

    private GeneralScrapper generalScrapper = new GeneralScrapper();

    private GmailScrapper gmailScrapper = new GmailScrapper();

    private WhatsappScrapper whatsappScrapper = new WhatsappScrapper();

    public void computeEvent(AccessibilityEvent event, String timestamp){

        if(event.getText() == null || EventDataExtractor.getEventText(event).equals(""))
            return; // If the event has no relevant information... exit.

        // TODO borrar este if cuando la papeleta esté solucionada
        if(event.getPackageName() == null || event.getPackageName().equals("com.google.android.inputmethod.latin"))
            return;

        //Checks if the user navigates from whatsapp to another activity and remains data to process
        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            whatsappScrapper.addUserInput(timestamp);


        // Switch event Package Name to discern the app that fired the event
        switch(event.getPackageName().toString()){
            case Strings.PACKAGE_GMAIL:
                // Scrap gmail data
                gmailScrapper.getData(event, timestamp);

                break;

            case Strings.PACKAGE_WHATSAPP:
                // Scrap whatsapp data
                whatsappScrapper.getData(event, timestamp);

            default:
              //  EventDataExtractor.printEvent(event);
                break;
        }



       /* switch(EventDataExtractor.getEventType(event)){
            case "TYPE_WINDOW_STATE_CHANGED":
                // Add new registry to TAD
                generalScrapper.addAppLaunch(event, timestamp);
                break;
            default:
                // Add the interaction to com.spilab.alberto.karabella.App TAD registry
                if(!EventDataExtractor.getEventText(event).equals("")){
                    // If the event has useful information
                    generalScrapper.addAppInteraction(event, timestamp);
                }
                break;
        }*/



        //generalScrapper.addEvent(event);
    }
}
