package com.spilab.alberto.karabella.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alberto on 27/09/17.
 *
 * Gmail model class
 */

public class GmailModel {

    private String sender;

    private List<String> receivers = new LinkedList<>();

    private String subject;

    private String body;

    public GmailModel(String sender, List<String> receivers, String subject, String body) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
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

    public GmailModel modelConstructor(){

        return this;

    }
}
