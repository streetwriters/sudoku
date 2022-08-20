package com.streetwriters.sudoku.Functions;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class Analytics extends AsyncTask<String, String, String> {

    String WEBSITE_ID = "b3b679f9-f122-4b3c-aa64-dcd0ea4563bc";
    String baseUrl = "https://analytics.streetwriters.co/api/collect";

    /*
    let body = {
    payload: {
      website: WEBSITE_ID,
      url: `notesnook-${Platform.OS}${prevRoute}${route}`,
      referrer: `https://notesnook.com/notesnook-${Platform.OS}${prevRoute}`,
      hostname: `notesnook-${Platform.OS}`,
      language: 'en-US',
      screen: '1920x1080'
    },
    type: 'pageview'
  };
     */
    @Override
    protected String doInBackground(String... strings) {
        String response="";
        try {
            JSONObject body = new JSONObject();
            JSONObject payload = new JSONObject();
            payload.put("url", strings[0]);
            payload.put("referrer", "");
            payload.put("hostname", "sudoku.com");
            payload.put("language", "en-US");
            payload.put("screen", "1920x1080");
            payload.put("website",WEBSITE_ID);

            body.put("payload", payload);
            body.put("type", "pageview");

            byte[] out = body.toString().getBytes(StandardCharsets.UTF_8);

            URL url = new URL(baseUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setFixedLengthStreamingMode(out.length);
            urlConnection.connect();
            OutputStream os = urlConnection.getOutputStream();
            os.write(out);
            os.close();

            int responseCode = urlConnection.getResponseCode();

            //Log.d(Analytics.class.getSimpleName(), "doInBackground: "+strings[0]);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                //Log.e(TAG, "14 - HTTP_OK");

                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                //Log.e(TAG, "14 - False - HTTP_OK");
                response = "";
            }

            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            //writer.write(body.toString());
            //writer.flush();
            //writer.close();
            //out.close();

            //urlConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Log.d(Analytics.class.getSimpleName(), "doInBackground: "+response);
        return response;
    }
}
