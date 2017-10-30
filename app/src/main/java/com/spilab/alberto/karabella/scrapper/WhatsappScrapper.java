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

    // Booleanano para controlar la doble entrada en el navigate to whatsapp home
    private boolean doubleTry = false;


    public void getData(AccessibilityEvent event, String timestamp){
        // EventDataExtractor.printEvent(event);

        if(whatsappDB == null)
            whatsappDB = new WhatsappDB();

        switch (event.getClassName().toString()){

            case Strings.CLASS_HOMEACTIVITY: //Whatsapp Home
                // First checks if remains data to process
                //checkUserInput(timestamp);

                // Save the previous whatsappDB object (if exists) in database
                storeObjectInRealm(timestamp);

                if(!doubleTry){
                    doubleTry = true;
                }else {
                    Log.d(TAG, "Navigate to whatsapp home");

                    // TODO remove ----
                    List<WhatsappDB> whatsappDBList = WhatsappManager.getAllWhatsappModels();
                    for (WhatsappDB wdb : whatsappDBList) {
                        Log.d("Whatsapp stored content", wdb.toString());
                    }
                    // TODO remove ~~~~

                    whatsappDB = new WhatsappDB();
                    whatsappDB.setTimestampStart(timestamp);

                    doubleTry = false;
                }
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

                // First get the eventText
                writtenText = EventDataExtractor.getEventText(event);
                Log.d(TAG, Integer.toString(event.getBeforeText().length()));
                if(event.getBeforeText().length() == 0){ // Checks if the text corresponds to a new line in the conversation
                        // Store the previous WhatsappDB (if exists) in Realm
                        storeObjectInRealm(timestamp);
                        Log.d(TAG, "Añadiendo nuevo registro");
                        whatsappDB.addNewTextRegistry("", timestamp);
                }else{
                    if(event.getBeforeText().length() < writtenText.length()){ // The user is not erasing the line
                        addUserInput(timestamp);
                    }else{
                        addUserInput(timestamp);
                    }

                }
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
    public void addUserInput(String timestamp){

        if(this.writtenText.length() > 1){ //There is a non-erasing string
            Log.d("CheckUserInput: ", writtenText);
            whatsappDB.addTextToRegistry(writtenText, timestamp);
            writtenText = ""; // Reset the stored string
        }else{
            Log.d(TAG, "CheckUserInput: No hay writtenText");
        }
    }

    /**
     * sotreObjectInRealm, method that calls WhatsappManager to
     * save or update the WhatsappDB model.
     * @param timestamp String describing the time when the user abandoned the conversation
     */
    public void storeObjectInRealm(String timestamp){

        if(this.whatsappDB != null && this.whatsappDB.getInterlocutor() != null) {
          //  if(this.whatsappDB.getLastTextRegistry().length() != 0){ // There is some data to store
                this.whatsappDB.setTimestampEnd(timestamp);
                WhatsappManager.saveOrUpdateWhatsappDB(this.whatsappDB);
            //}

        }
    }

    public void contextChange(String timestamp){
        Log.d(TAG, "Comprobando el cambio de contexto desde whatsapp");
        if(this.whatsappDB == null || this.whatsappDB.getTextList() == null) //If whatsappDB object is empty
            return;

        if(this.writtenText.length() > 1){ //There is a non-erasing string
            Log.d("contextChange: ", writtenText);
            whatsappDB.addNewTextRegistry(writtenText, timestamp);
            storeObjectInRealm(timestamp);
        }else{
            Log.d(TAG, "CheckUserInput: No hay writtenText");
        }
    }


}
