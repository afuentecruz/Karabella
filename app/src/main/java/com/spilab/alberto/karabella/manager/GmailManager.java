package com.spilab.alberto.karabella.manager;

import com.spilab.alberto.karabella.model.GmailDB;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 2/10/17.
 *
 * GmailDB database manager
 */

public class GmailManager {

    private static final String TAG = "GmailManager";

    /**
     * Save or update an gmailDB object in DB
     * @param gmailDB, gmailDB object to save in realm
     */
    public static void saveOrUpdateGmailDB(GmailDB gmailDB){
        Realm realm = Realm.getDefaultInstance(); //instantiate RealmDB

        //Save or update event
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(gmailDB);
        realm.commitTransaction();
    }

    /**
     * getAllGmailModels, returns all the GmailDB objects saved in Realm
     * @return gmailDBList
     * */
    public static List<GmailDB> getAllGmailModels(){
        List<GmailDB> gmailDBList;
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //Find all the gmail models
        gmailDBList = realm.where(GmailDB.class).findAll();
        realm.commitTransaction();

        return gmailDBList;
    }

}
