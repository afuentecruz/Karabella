package com.spilab.alberto.karabella.manager;

import android.util.Log;

import com.spilab.alberto.karabella.model.WhatsappDB;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 19/10/17.
 *
 * WhatsappManager class, manages the interaction of WhatsappDB models with Realm. 
 */

public class WhatsappManager {

    /**
     * Save or updates a WhatsappDB object in DB
     * @param whatsappDB, WhatsappDB object to save or update
     */
    public static void saveOrUpdateWhatsappDB(WhatsappDB whatsappDB){
        Log.d("WhatsappManager", "SaveOrUpdateWhatsappDB");
        Realm realm = Realm.getDefaultInstance();

        //Save or update whatsappDB
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(whatsappDB);
        realm.commitTransaction();
    }

    public static List<WhatsappDB> getAllWhatsappModels(){
        List<WhatsappDB> whatsappDBList;
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        whatsappDBList = realm.where(WhatsappDB.class).findAll();
        realm.commitTransaction();

        return whatsappDBList;
    }
}
