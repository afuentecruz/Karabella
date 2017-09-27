package com.spilab.alberto.karabella.scrapper;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

import java.util.ArrayList;
import java.util.Date;
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
    private Queue<AccessibilityEvent> eventBuffer = new LinkedList<>();

    /**
     * A list with all the relevant information about the accessibility events previously managed
     */
    private List<AppLog> appLogList = new ArrayList<>();

    public void addAppLaunch(AccessibilityEvent event, String timestamp){

        if(appLogList.size() == 0){ // Populate the list
            AppLog appLog = new AppLog(
                    timestamp,
                    EventDataExtractor.getEventText(event),
                    event.getPackageName().toString(),
                    event.getClassName().toString());

            appLogList.add(appLog);
        }else{
            if(appLogList.get(appLogList.size()-1).getPackageName().equals(event.getPackageName()))
                Log.d(TAG, "Misma app ");
            else{
                AppLog appLog = new AppLog(
                        timestamp,
                        EventDataExtractor.getEventText(event),
                        event.getPackageName().toString(),
                        event.getClassName().toString());

                appLogList.add(appLog);
                Log.d(TAG, "Cambio de contexto");
            }
        }
        printLog();

    }

    public void addAppInteraction(AccessibilityEvent event, String timestamp){

        //Ensure that the app is really the same that the last app launched
        if(appLogList.get(appLogList.size()-1).getPackageName().equals(event.getPackageName())){
            Log.d(TAG, "Misma app ");

            appLogList.get(appLogList.size()-1).addElementToNavigationList(event, timestamp);
        }
        printLog();
    }
    public void logEvent(AccessibilityEvent event) {

        // Si el ultimo evento almacenado corresponde con la misma package que vamos a procesar ahora,
        // lo añadimos a la lista de navegación
        if(EventDataExtractor.getEventText(event).equals("")){
            Log.d("Catch", "Catch");
        }


        if(appLogList.size() > 0 && appLogList.get(appLogList.size()-1).getPackageName().equals(event.getPackageName())){
            Log.d(TAG, "Mismo package name");

            EventDataExtractor.printEvent(event);
        }else{
            // Cambio de contexto de la aplicación
            //AppLog(String appName, String packageName, String className)
            if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                AppLog appLog = new AppLog(
                        new Date(event.getEventTime()).toString(),
                                EventDataExtractor.getEventText(event),
                                event.getPackageName().toString(),
                                event.getClassName().toString());

                appLogList.add(appLog);
                Log.d(TAG, "Cambio de contexto");
               // EventDataExtractor.printEvent(event);
            }else{
                //EventDataExtractor.printEvent(event);
               // appLogList.get(appLogList.size()-1).addElementToNavigationList(event, timestamp);
            }
        }
        printLog();
    }

    private void printLog(){
        Log.d(TAG, this.appLogList.get(this.appLogList.size()-1).toString());
    }
}
