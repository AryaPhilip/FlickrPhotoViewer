package com.example.shweta.flickrphotoviewer;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* Created by: Shweta Philip
 * Purpose: This class uses the Flickr API method people.getPhotos
 * to return details of the publically available photos
 * in JSON format and stores the details in a List of Entry class
 */

public class URLReader extends AsyncTask<String, Void, Void> {


    String FlickrBaseURL = "https://api.flickr.com/services/rest/?"; // The base Flickr URL used by the Flickr API
    String FlickrMethodUsed = "method=flickr.people.getPhotos"; // The Flickr API method used to get photos
    String FlickrApiKey = "&api_key=19a62898cff39f6397da2d2079b06b77"; // The application key obtained from the Flickr Website
    String FlickrUserId = "&user_id=commons"; // The argument in the people.getPhotos method which specifies the user. In this case, it is commons: publically available images
    String FlickrPrivacyFilter = "&privacy_filter=1"; // The argument in the people.getPhotos which specifies the privacy filter. privacy_filter = 1 is public
    String FlickrFormat = "&format=json"; // The format in which the API returns the details of the photos
    String FlickrPerPage = "&per_page=500"; // The number of photos whose details are mentioned per page
    String jsonText = null; // The doInBackground function uses this string text to read from the URL which specifies the FlickrAPI method

    String TAG = "URLReader class";

    private  String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        int i = 0;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    protected Void doInBackground(String... params) {

        String url = new String(FlickrBaseURL+FlickrMethodUsed+FlickrApiKey+FlickrUserId+FlickrPrivacyFilter+FlickrFormat+FlickrPerPage); // URL that specifies the FlickrAPI method
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
            if(httpEntity!=null){
                InputStream inputStream = httpEntity.getContent();
                Reader in = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(in);
                jsonText = readAll(bufferedReader);
                jsonText = jsonText.substring(jsonText.indexOf("{"), jsonText.lastIndexOf("}") + 1); // Selects the string between 'jsonFlickrApi({' & '})' to make it easier to read JSON format
            }
        } catch (ClientProtocolException e) {
            Log.d(TAG,"Client Protocol Exception");
        } catch (IOException e) {
            Log.d(TAG,"IO Exception");
        }

        try {
            JSONObject JsonObject = new JSONObject(jsonText);
            JSONObject Json_photos = JsonObject.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");
            for(int i = 0; i < JsonArray_photo.length(); i++) {

                JSONObject FlickrPhoto = JsonArray_photo.getJSONObject(i);

                // Stores objects of Entry class in the Entry List extracted from the JSONObject obtained from the URL
                // Each entry has 4 attributes: Farm, Server, Photo Id, Secret
                Data.EntryList.add(new Entry(FlickrPhoto.getString("farm"), FlickrPhoto.getString("server"), FlickrPhoto.getString("id"), FlickrPhoto.getString("secret")));
            }

        } catch (JSONException e) {
            Log.d(TAG,"JSON Exception");
        }

        return null;
    }

}

