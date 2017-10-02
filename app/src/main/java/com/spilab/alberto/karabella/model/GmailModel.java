package com.spilab.alberto.karabella.model;

import java.util.LinkedList;

import io.realm.RealmObject;

/**
 * Created by alberto on 27/09/17.
 *
 * Gmail model class
 */

public class GmailModel {

    private String sender;

    private LinkedList<String> receivers = new LinkedList<>();

    private String subject;

    private String body;

    private String timestamp;

    public GmailModel(){};

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LinkedList<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(LinkedList<String> receivers) {
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
        return "GmailModel{" + "\n" +
                "timestamp='" + timestamp + "'\n" +
                "sender='" + sender + "'\n" +
                "receivers=" + receivers + "'\n" +
                "subject='" + subject + "'\n" +
                "body='" + body + "'\n" +
                "}" + "\n";
    }
}
