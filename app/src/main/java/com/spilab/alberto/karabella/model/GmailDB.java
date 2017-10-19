package com.spilab.alberto.karabella.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 27/09/17.
 *
 * Gmail model class
 */

public class GmailDB extends RealmObject {

    @PrimaryKey
    private String id;

    private String sender;

    private RealmList<RealmString> receivers = new RealmList<>();

    private String subject;

    private String body;

    private String timestamp;

    public GmailDB(){
        this.id = UUID.randomUUID().toString(); //Randomized id.
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public RealmList<RealmString> getReceivers() {
        return receivers;
    }

    public void setReceivers(RealmList<RealmString> receivers) {
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "GmailDB{" + "\n" +
                "timestamp='" + timestamp + "'\n" +
                "sender='" + sender + "'\n" +
                "receivers=" + Arrays.toString(receivers.toArray()) + "'\n" +
                "subject='" + subject + "'\n" +
                "body='" + body + "'\n" +
                "}" + "\n";
    }
}
