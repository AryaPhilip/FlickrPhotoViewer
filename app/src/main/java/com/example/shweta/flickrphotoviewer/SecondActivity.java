package com.example.shweta.flickrphotoviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/* Created by: Shweta Philip
* Purpose: The Second Activity has a drop down menu for the user
* to select which photo he/she wants to display
* The photo thus selected is displayed on the screen
*/

public class SecondActivity extends ActionBarActivity implements OnItemSelectedListener{

    // The drop down menu
    private Spinner spinner;

    String TAG = "Second activity";
    // Creates a new list of all photo Ids obtained from the EntryList
    private static final List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.v(TAG, "size " + Data.EntryList.size());

        // Populating the list with photo ids
        // Selected photo id to fill the drop down menu because not all the images had a title
        spinner = (Spinner) findViewById(R.id.spinner);
        for(int i = 0 ; i < Data.EntryList.size(); i++) {
            Entry entry = Data.EntryList.get(i);
            list.add(entry.id);
        }
        Log.v(TAG,"size "+list.size());

        // Uses the list to create the drop down view resource
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Once the user selects a photo id, the index of the list is updated
        Data.itImage = position;

        // The index updated now is used to display the corresponding images
        new DisplayImage((ImageView) findViewById(R.id.imageView)).execute();
    }



    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // Noting is done
        // As default is setup
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action b ar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
