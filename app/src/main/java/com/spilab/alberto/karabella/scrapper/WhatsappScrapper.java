package com.spilab.alberto.karabella.scrapper;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.manager.GmailManager;
import com.spilab.alberto.karabella.manager.WhatsappManager;
import com.spilab.alberto.karabella.model.GmailDB;
import com.spilab.alberto.karabella.model.WhatsappDB;
import com.spilab.alberto.karabella.utils.EventDataExtractor;
import com.spilab.alberto.karabella.utils.Strings;

import java.util.List;

/**
 * Created by alberto on 18/10/17.
 *
 * Whatsapp scrapper, this class scraps and puts in a POJO the whatsapp user interaction
 */

public class WhatsappScrapper {

    private static final String TAG = "WhatsappScrapper";

    private WhatsappDB whatsappDB;

    /** String oldText, stores what the user has written in the whatsapp editText */
    private String writtenText = "";

    /** String actualText, stores what the user is writing right now in the whatsapp editText */
    private String actualText = "";


    public void getData(AccessibilityEvent event, String timestamp){
        // EventDataExtractor.printEvent(event);
        switch (event.getClassName().toString()){

            case Strings.CLASS_HOMEACTIVITY: //Whatsapp Home
                Log.d(TAG, "Navigate to whatsapp home");
                // First checks if remains data to process
                checkUserInput(timestamp);
                // Save the previous whatsappDB object (if exists) in database
                storeObjectInRealm();

                // TODO remove ----
                List<WhatsappDB> whatsappDBList= WhatsappManager.getAllWhatsappModels();
                for(WhatsappDB wdb: whatsappDBList){
                    Log.d("Whatsapp stored content", wdb.toString());
                }
                // TODO remove ~~~~

                whatsappDB = new WhatsappDB();
                whatsappDB.setTimestamp(timestamp);
                break;

            case Strings.WIDGET_RELATIVE_LAYOUT: //Clicked whatsapp conversation
                // Extract the contact name string
                String interlocutor = this.getContactName(EventDataExtractor.getEventText(event));
                whatsappDB.setInterlocutor(interlocutor);

                Log.d(TAG, "Clicked whatsapp conversation: " + interlocutor);
                break;

            case Strings.WIDGET_EDITTEXT: //Writting in whatsapp conversation
                // Check that the eventText is not "Escribir mensaje"
                if(EventDataExtractor.getEventText(event).equals("Escribir mensaje") || event.getText() == null || event.getBeforeText() == null)
                    return;
                /*
                * La lógica que se plantea es la siguiente:
                *
                * El usuario comienza a escribir y se va guardando to en un String
                *
                * En el momento en el que manda el mensaje y comienza a escribir de nuevo el texto del evento pasa de
                * tener la longitud que sea a tener una longitud de 1 (primera letra que se escribe, cuando esto ocurra
                * se debe almacenar la mandanga en el POJO y resetear la cadena.
                * */

                if(event.getBeforeText().length() == 0){

                    // The editText widget is empty, that mains that the user has sends the previous string
                    if(writtenText.length() != 0){
                        Log.d("shit", writtenText);
                        whatsappDB.addText(writtenText, timestamp);
                        writtenText = "";
                    }
                }
                writtenText = EventDataExtractor.getEventText(event);
                break;

            default:
                EventDataExtractor.printEvent(event);
                break;
        }
    }

    /**
     * getContactName, method that extracts the name of the person or group that the user is writing on
     * @param eventText, accessibilityEvent text.
     * @return String contactName, the extracted name
     * */
    private String getContactName(String eventText){

        String contactName = "";
        for(int i = 0; i < eventText.length(); i++){
            if(eventText.charAt(0) == eventText.charAt(i)){
                contactName = eventText.substring(0, i);
            }
        }

        return contactName;
    }

    /**
     * checkUserInput, checks if there is any existing content in the data string
     * that must be saved
     *
     * This method is called when the user navigates to Whatsapp Home or when there is any
     * change of context in the screen (new activities loaded...etc).
     */
    public void checkUserInput(String timestamp){

        if(this.writtenText.length() != 0){
            //Hay texto escrito por procesar
            Log.d("shit2", writtenText);
            whatsappDB.addText(writtenText, timestamp);
            writtenText = "";
        }
    }

    /**
     * sotreObjectInRealm, method that calls WhatsappManager to
     * save or update the WhatsappDB model.
     */
    public void storeObjectInRealm(){
        if(this.whatsappDB != null && this.whatsappDB.getInterlocutor() != null)
            WhatsappManager.saveOrUpdateWhatsappDB(this.whatsappDB);
    }


}
