package com.chrisfeb.app.chrislogin;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Networks extends AsyncTask<String, Void, String> {

    private static final String TAG = "Networks";
    @Override
    protected String doInBackground(String... strings) {

        String username = strings[0];
        String password = strings[1];
        String link = "http://120.78.137.216/mytaskapp/login.php";

        try {
            String utf8 = "UTF-8";
            String data = URLEncoder.encode("username", utf8)
                    + ("=") + URLEncoder.encode(username, utf8);
            data += "&" + URLEncoder.encode("password", utf8)
                    + "=" + URLEncoder.encode(password, utf8);

            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            Log.d(TAG, "doInBackground: url :" + url);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            writer.write(data);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                builder.append(line);
                Log.d(TAG, "doInBackground:read."+line);
                break;
            }
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Exception : " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: success " + s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
