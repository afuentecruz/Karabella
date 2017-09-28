package com.spilab.alberto.karabella.scrapper;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.model.GmailModel;
import com.spilab.alberto.karabella.utils.EventDataExtractor;
import com.spilab.alberto.karabella.utils.Strings;

import java.util.LinkedList;

/**
 * Created by alberto on 27/09/17.
 *
 * Gmail scrapper class, this extracts and puts on a POJO gmail data class
 */

public class GmailScrapper {

    private static final String TAG = "GmailScrapper";

    private GmailModel gmailModel = new GmailModel();

    private LinkedList<String> formatReceivers(String receivers){
        //Receivers is a String like "<albertodlfnte@gmail.com>, <adelafue@gmail.com>, ";
        LinkedList<String> receiversList = new LinkedList<>();
        String[] receiversArray = receivers.split(", ");

        for(int i = 0; i<receiversArray.length; i++){
            receiversList.add(receiversArray[i]);
        }
        return receiversList;
    }

    public void getData(AccessibilityEvent event, String timestamp){
        //EventDataExtractor.printEvent(event);

        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
            // Gmail was launched, instantiate it
            gmailModel.setTimestamp(timestamp);
        }else{
            // Whatever accessibility event
            switch(event.getClassName().toString()){
                case Strings.WIDGET_IMAGEBUTTON:
                    Log.d(TAG, "Redactar nuevo correo!");

                    break;
                case Strings.WIDGET_SPINNER: //Sender
                    gmailModel.setSender(EventDataExtractor.getEventText(event));

                    break;
                case Strings.WIDGET_MULTIAUTOCOMPLETETEXTVIEW: //Receivers
                    if(event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED){
                        String receiver = EventDataExtractor.getEventText(event);
                        if(receiver.endsWith(", ")){
                            // Extract all the receivers one by one
                            receiver = receiver.replace("<", "");
                            receiver = receiver.replace(">", "");
                            gmailModel.setReceivers(this.formatReceivers(receiver));
                           // Log.d(TAG, receiver);
                        }
                    }

                    break;
                case Strings.WIDGET_EDITTEXT: //Mail subject
                    gmailModel.setSubject(EventDataExtractor.getEventText(event));

                    break;
                case Strings.VIEW_VIEW: //Mail body
                    gmailModel.setBody(EventDataExtractor.getEventText(event));

                    break;
                case Strings.WIDGET_TOAST: //Mail finally send
                    if(EventDataExtractor.getEventText(event).equals("Enviando mensaje…")) {
                        Log.d(TAG, gmailModel.toString());
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
