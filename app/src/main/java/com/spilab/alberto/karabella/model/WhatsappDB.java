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

    // Describes the start time when the user clicked a conversation
    private String timestampStart;

    // Describes the end time when the user abandoned a conversation
    private String timestampEnd;

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

    /**
     * addTextRegistry, method that adds a new RealmStringWithTimeStamp registry
     * to the LinkedList
     * @param text initial text content
     * @param timestamp initial timestamp
     */
    public void addNewTextRegistry(String text, String timestamp){
        this.textList.add(new RealmStringWithTimestamp(text, timestamp));
    }

    /**
     * addTextToRegistry, adds the new text to the current working registry
     * and updates the timestamp
     * @param text String with the new content to add to the registry
     * @param timestamp Timestamp describing when the content was added
     */
    public void addTextToRegistry(String text, String timestamp){
        // if the text list is not empty add content
        if(!this.textList.isEmpty()) {
            this.textList.last().setContent(text);
            this.textList.last().setTimestamp(timestamp);
        }
    }

    public String getLastTextRegistry(){
        return this.textList.last().getcontent();
    }

    public String getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(String timestampStart) {
        this.timestampStart = timestampStart;
    }

    public String getTimestampEnd(){
        return this.timestampEnd;
    }

    public void setTimestampEnd(String timestampEnd){
        this.timestampEnd = timestampEnd;
    }

    @Override
    public String toString() {
        return "WhatsappDB{" + "\n" +
                '\t' + "id: " + id + "\n" +
                '\t' + "interlocutor: " + interlocutor + "\n" +
                '\t' + "timestamp Start: " + timestampStart + "\n" +
                '\t' + "timestamp End: " + timestampEnd + "\n" +
                '\t' + "textList: " +  Arrays.toString(textList.toArray()) +
                '}' + "\n";
    }
}
