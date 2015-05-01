package com.example.shweta.flickrphotoviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* Created by Shweta Philip
*  Purpose: To retrieve images in the format of Bitmaps from specified URL
* The images thus obtained from this class is directly displayed using ImageView on the second Activity
*/
public class DisplayImage extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    String TAG = "DisplayImage";

    //Constructor for Display Image
    public DisplayImage(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        // Creates a new Entry class object and stores the detail of the photo obtained from the EntryList
        // The itImage is the index of the photo that is selected by the user in the Second Activity
        Entry entry = Data.EntryList.get(Data.itImage);

        // A String urlDisplay is created using the details obtained from the EntryList
        String urlDisplay = new String("https://farm" + entry.farm + ".staticflickr.com/" + entry.server + "/" + entry.id + "_" + entry.secret + ".jpg");
        Bitmap image = null;

        // The corresponding image is retrieved from the url stored in image
        try {
            InputStream in = new URL(urlDisplay).openStream();
                image = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.v(TAG,"IO Exception");
        }

        return image;
    }

    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}
