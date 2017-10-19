package com.spilab.alberto.karabella.model;

import io.realm.RealmObject;

/**
 * Created by alberto on 2/10/17.
 *
 * RealmString class needed for RealmList
 */

public class RealmString extends RealmObject {

    private String value;

    public RealmString(){}

    public RealmString(String value){
        this.value = value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

}
