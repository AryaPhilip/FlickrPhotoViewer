package com.example.shweta.flickrphotoviewer;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by: Shweta Philip
 * Purpose: Data has static attributes which store common data for
 * both the activities and different classes in the package
 */
public class Data {

    // index obtained from the GUI, selected by the user. Used to retrieve the details of the right image.
    // By default it is zero,hence it will always display first photo when application starts
    public static int itImage = 0;

    // An array list of class Entry, which stores all the data about photos obtained by using the FlickrAPI methods
    public static List<Entry> EntryList = new ArrayList<>();
}
