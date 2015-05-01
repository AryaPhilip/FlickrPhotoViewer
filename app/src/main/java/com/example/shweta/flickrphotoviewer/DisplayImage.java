package com.example.shweta.flickrphotoviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by shweta on 4/30/15.
 */
public class DisplayImage extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;

    public DisplayImage(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

            Entry entry = Data.EntryList.get(Data.itImage);
            String urlDisplay = new String("https://farm" + entry.farm + ".staticflickr.com/" + entry.server + "/" + entry.id + "_" + entry.secret + ".jpg");
            Bitmap image = null;

            try {
                InputStream in = new URL(urlDisplay).openStream();
                image = BitmapFactory.decodeStream(in);
//                Data.bitmapList.add(mIcon);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return image;
    }


    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}
