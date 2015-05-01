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

/**
 * Created by shweta on 4/30/15.
 */
public class URLReader extends AsyncTask<String, Void, Void> {


    String FlickrBaseURL = "https://api.flickr.com/services/rest/?";
    String FlickrMethodUsed = "method=flickr.people.getPhotos";
    String FlickrApiKey = "&api_key=19a62898cff39f6397da2d2079b06b77";
    String FlickrUserId = "&user_id=commons";
    String FlickrPrivacyFilter = "&privacy_filter=1";
    String FlickrFormat = "&format=json";
    String FlickrPerPage = "&per_page=500";
    private final String ns = null;
    String jsonText = null;

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

        String id = null;
        String owner = null;
        String url = new String(FlickrBaseURL+FlickrMethodUsed+FlickrApiKey+FlickrUserId+FlickrPrivacyFilter+FlickrFormat+FlickrPerPage);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
            if(httpEntity!=null){
                InputStream inputStream = httpEntity.getContent();
                Reader in = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(in);
                jsonText = readAll(bufferedReader);
                jsonText = jsonText.substring(jsonText.indexOf("{"), jsonText.lastIndexOf("}") + 1);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject JsonObject = new JSONObject(jsonText);
            JSONObject Json_photos = JsonObject.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");
            for(int i = 0; i < JsonArray_photo.length(); i++) {

                JSONObject FlickrPhoto = JsonArray_photo.getJSONObject(i);

                Data.EntryList.add(new Entry(FlickrPhoto.getString("farm"), FlickrPhoto.getString("server"), FlickrPhoto.getString("id"), FlickrPhoto.getString("secret")));
            }

            Log.v("Flickr_URLReader Do in Background", "size  " + Data.EntryList.size());
//          OutputEntryList = EntryList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


//        protected void onPostExecute(){
//
//        }
}

