package com.spilab.alberto.karabella.Scrapper;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by alberto on 21/09/17.
 *
 * logs all the user interaction with the UI.
 */

public class MainScrapper {

    static final String TAG = "MainScrapper";


    /**
     * Buffer with all the events managed by the system
     */
    Queue<AccessibilityEvent> eventBuffer = new LinkedList<>();

    /**
     * A list with all the relevant information about the accessibility events previously managed
     */
    List<AppLog> appLogList = new ArrayList<>();

    public void addEvent(AccessibilityEvent event) {
        eventBuffer.add(event);
        this.processEvent();
    }

    private void processEvent(){
        if(eventBuffer.isEmpty()){
            Log.d(TAG, "Error, the event buffer is empty!");
            return;
        }
        AccessibilityEvent event = eventBuffer.poll();
        if(event == null)
            return;

        if(!EventDataExtractor.getEventText(event).equals("") && !EventDataExtractor.getEventText(event).equals(" ")){
            logEvent(event);
            //EventDataExtractor.printEvent(event);
        }
    }

    public void logEvent(AccessibilityEvent event) {
       // EventDataExtractor.printEvent(event);

        // Si el ultimo evento almacenado corresponde con la misma package que vamos a procesar ahora,
        // lo añadimos a la lista de navegación
        if(EventDataExtractor.getEventText(event).equals("")){
            Log.d("Catch", "Catch");
        }
        if(appLogList.size() == 0){
            AppLog appLog = new AppLog(EventDataExtractor.getEventText(event),
                    event.getPackageName().toString(), event.getClassName().toString());
            appLogList.add(appLog);
        }

        if(appLogList.size() > 0 && appLogList.get(appLogList.size()-1).getPackageName().equals(event.getPackageName())){
            Log.d(TAG, "Mismo package name");

            EventDataExtractor.printEvent(event);
            appLogList.get(appLogList.size()-1).addElementToNavigationList(event);
        }else{
            // Cambio de contexto de la aplicación
            //AppLog(String appName, String packageName, String className)
            if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                AppLog appLog = new AppLog(EventDataExtractor.getEventText(event),
                                event.getPackageName().toString(), event.getClassName().toString());

                appLogList.add(appLog);
                Log.d(TAG, "Cambio de contexto");
               // EventDataExtractor.printEvent(event);
            }else{
                //EventDataExtractor.printEvent(event);
                appLogList.get(appLogList.size()-1).addElementToNavigationList(event);
            }
        }
        printLog();
    }

    private void printLog(){
        Log.d(TAG, this.appLogList.get(this.appLogList.size()-1).toString());
    }
}
