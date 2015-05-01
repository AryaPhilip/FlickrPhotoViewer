package com.example.shweta.flickrphotoviewer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*
* Created by : Shweta Philip
* Purpose: FlickrMainActivity represents the first page of the android application.
* An introduction to the application.
*/
public class FlickrMainActivity extends ActionBarActivity {

    //Start App button
    Button button;

    String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_main);
        //urlr is an object of the class URLReader
        //This class uses the Flickr API method people.getPhotos
        // to return details of the publically available photos
        // in JSON format and stores the details in a List of Entry class

        URLReader urlr = new URLReader();
        urlr.execute();

        // Start App Button
        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flickr_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Creates an Intent which links the two acitivity classes FlickrMainActivity and SecondActivity
    //When this button is selected the user is directed to a different page in the app where the user
    //can select photo ids from the list and the app displays the image.
    public void addListenerOnButton() {

        Log.v(TAG, "Start App button clicked");
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(FlickrMainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}


