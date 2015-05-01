package com.example.shweta.flickrphotoviewer;

/**
 * Created by shweta on 4/28/15.
 */
public class Entry {

    String farm;
    String server;
    String id;
    String secret;

    Entry(String farm, String server, String id, String secret){
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
    }
}
