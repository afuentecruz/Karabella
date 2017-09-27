package com.spilab.alberto.karabella.Scrapper;

/**
 * Created by alberto on 21/09/17.
 */

public class Registry {

    private String timestamp;

    private String appName;

    private String packageName;

    private String data;

    public Registry(){}

    public Registry(String timestamp, String packageName, String data){
        this.timestamp = timestamp;
        this.packageName = packageName;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "timestamp=" + timestamp + '\n' +
                ", appName=" + appName + '\n' +
                ", packageName=" + packageName + '\n' +
                ", data=" + data + '\n' +
                '}' +  '\n';
    }
}
