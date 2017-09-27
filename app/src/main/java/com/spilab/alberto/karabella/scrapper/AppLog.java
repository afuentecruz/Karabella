package com.spilab.alberto.karabella.scrapper;

import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.utils.EventDataExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 21/09/17.
 *
 * AppLog class, logs all the user interaction with a certain application.
 */

public class AppLog {

    private String timestamp;

    private  String appName;

    private String packageName;

    private String className;

    private List<Registry> navigation = new ArrayList<>();

    public AppLog(){}

    public AppLog(String timestamp, String appName, String packageName, String className, List<Registry> navigation) {
        this.timestamp = timestamp;
        this.appName = appName;
        this.packageName = packageName;
        this.className = className;
        this.navigation = navigation;
    }

    public AppLog(String timestamp, String appName, String packageName, String className){
        this.timestamp = timestamp;
        this.appName = appName;
        this.packageName = packageName;
        this.className = className;
    }

    public String getPackageName(){
        return this.packageName;
    }

    public void addElementToNavigationList(AccessibilityEvent event, String timestamp){
        //String timestamp, String appName, String packageName, String data)

        Registry registry = new Registry(timestamp,
                event.getPackageName().toString(),
                EventDataExtractor.getEventText(event));
        navigation.add(registry);
    }

    @Override
    public String toString() {
        return "AppLog{" +
                "timestamp=" + timestamp + '\n' +
                ", appName=" + appName + '\n' +
                ", packageName=" + packageName + '\n' +
                ", navigation=" + navigation +
                '}' + '\n';
    }
}
