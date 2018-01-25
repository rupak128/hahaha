package com.esoxjem.movieguide.favorites;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackGroundMovie extends AsyncTask<String,Void,Void> {
    Context context;
    BackGroundMovie (Context ctx) {
        context = ctx;
    }
    public BackGroundMovie(){}
    @Override
    protected Void doInBackground(String... params) {
        String user = params[0];
        String movieId = params[1];
        try{
                String watched_url = "http://192.168.0.104/addWatched.php";
                URL url = new URL(watched_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode("5", "UTF-8") + "&"
                        + URLEncoder.encode("movie_id", "UTF-8") + "=" + URLEncoder.encode(movieId, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}