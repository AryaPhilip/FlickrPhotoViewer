package com.example.shweta.flickrphotoviewer;

/*
 * Created by: Shweta Philip
 * Purpose: A class which has attributes that can be used to store the important
 * information of the photos needed to be retrieved from the Flickr Website
 */
public class Entry {

    String farm;  // farm
    String server; // server
    String id;  // photo id
    String secret; // secret

    // Constructor for class Entry
    Entry(String farm, String server, String id, String secret){
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
    }
}
