package com.spilab.alberto.karabella.catcher;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

/**
 * Created by alberto on 18/09/17.
 * <p>
 * Called from EventHandler when the user performs a text input
 */

public class TextCatcher {

    static final String TAG = "TextCatcher";

    // String that saves the real time user input in the system
    String data = "";

    // dataContext, contains the application name where the text was written
    String dataContext = "";

    public void setDataContext(String dataContext){
        if(!dataContext.equals("")){
            Log.d(TAG, "Seteando dataContext a " + dataContext);
            this.dataContext = dataContext;
        }

    }

    public void printInputText(AccessibilityEvent event, String timestamp){
        if(!this.data.equals("")){
            Log.d(TAG, "User sends: <" + this.data + "> at " + timestamp + " in " + this.dataContext);
            this.data = "";
        }


    }

    /**
     * Logs the text that the user writes in an EditText
     * */
    public void getInputText(AccessibilityEvent event, String timestamp) {
        if (event.getEventType() != AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            return;
        }
        if(event.getText() == null || event.getBeforeText() == null){
            return;
        }

        // The editText widget is empty
        if(event.getBeforeText().length()== 0){
            data = "";

        }else { // The user is writing an entire phrase
                this.data = EventDataExtractor.getEventText(event);
        }
    }
}
