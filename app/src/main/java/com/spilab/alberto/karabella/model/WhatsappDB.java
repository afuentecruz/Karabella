package com.spilab.alberto.karabella.model;

import java.util.Arrays;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 18/10/17.
 *
 * Whatsapp model class
 */

public class WhatsappDB extends RealmObject {

    @PrimaryKey
    private String id;

    private String interlocutor;

    private RealmList<RealmStringWithTimestamp> textList = new RealmList<>();

    private String timestamp;

    public WhatsappDB(){
        this.id = UUID.randomUUID().toString(); //Randomized id.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(String interlocutor) {
        this.interlocutor = interlocutor;
    }

    public RealmList<RealmStringWithTimestamp> getTextList() {
        return textList;
    }

    public void setTextList(RealmList<RealmStringWithTimestamp> textList) {
        this.textList = textList;
    }

    public void addText(String text, String timestamp){
        this.textList.add(new RealmStringWithTimestamp(text, timestamp));
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WhatsappDB{" + "\n" +
                "id=" + id + "\n" +
                "interlocutor=" + interlocutor + "\n" +
                "timestamp=" + timestamp + "\n" +
                "textList=" +  Arrays.toString(textList.toArray()) + "'\n" +
                '}' + "\n";
    }
}
