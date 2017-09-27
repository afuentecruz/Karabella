package com.spilab.alberto.karabella.manager;

import android.view.accessibility.AccessibilityEvent;

import com.spilab.alberto.karabella.Scrapper.MainScrapper;

/**
 * Created by alberto on 26/09/17.
 */

public class EventManager {

    private MainScrapper mainLogger = new MainScrapper();

    public void computeEvent(AccessibilityEvent event){
        mainLogger.addEvent(event);
    }
}
