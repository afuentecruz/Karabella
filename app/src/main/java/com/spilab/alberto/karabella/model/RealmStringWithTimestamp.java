package com.spilab.alberto.karabella.model;

import io.realm.RealmObject;

/**
 * Created by alberto on 19/10/17.
 */

public class RealmStringWithTimestamp extends RealmObject {

    private String content;

    private String timestamp;

    public RealmStringWithTimestamp(){}

    public RealmStringWithTimestamp(String content){
        this.content = content;
    }

    public RealmStringWithTimestamp(String content, String timestamp){
        this.content = content;
        this.timestamp = timestamp;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getcontent(){ return this.content; }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" + '\n' +
                '\t' +'\t' + "content: " + content + '\n' +
                '\t' +'\t' + "timestamp: " + timestamp + '\n' +
                '\t' + '}' + '\n';
    }

}
