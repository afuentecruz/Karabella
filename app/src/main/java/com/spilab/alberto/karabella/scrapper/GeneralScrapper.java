package com.spilab.alberto.karabella.scrapper;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.Logs.AppLog;
import com.spilab.alberto.karabella.model.GmailModel;
import com.spilab.alberto.karabella.utils.EventDataExtractor;
import com.spilab.alberto.karabella.utils.Strings;

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

public class GeneralScrapper {

    static final String TAG = "GeneralScrapper";

    /**
     * Buffer with all the events managed by the system
     */
    private Queue<AccessibilityEvent> eventBuffer = new LinkedList<>();

    /**
     * A list with all the relevant information about the accessibility events previously managed
     */
    private List<AppLog> appLogList = new ArrayList<>();

    /**
     * Object list with the contextual info about the apps launched and interacted
     */
    private List<Object> appInfoList = new ArrayList<>();

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

        getAppInfo(event);
        //Ensure that the app is really the same that the last app launched
        if(appLogList.get(appLogList.size()-1).getPackageName().equals(event.getPackageName())){
            Log.d(TAG, "Misma app ");

            appLogList.get(appLogList.size()-1).addElementToNavigationList(event, timestamp);
        }
        printLog();
    }

    private void printLog(){
        Log.d(TAG, this.appLogList.get(this.appLogList.size()-1).toString());
    }

    public void addAppInfo(AccessibilityEvent event, String timestamp){

    }

    private void getAppInfo(AccessibilityEvent event){

        switch(event.getPackageName().toString()){
            case Strings.PACKAGE_GMAIL:


                break;
            default:
                break;
        }

    }
}
