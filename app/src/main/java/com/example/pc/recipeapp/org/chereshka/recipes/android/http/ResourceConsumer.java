package com.example.pc.recipeapp.org.chereshka.recipes.android.http;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceConsumer extends AsyncTask<String, Integer, JsonElement> {

    @Override
    protected JsonElement doInBackground(String... params) {
        String url = params[1];
        String method = params[0];
        HttpClient client = new DefaultHttpClient();
        HttpRequestBase request = null;
        switch (method){
            case HttpGet.METHOD_NAME:
                request = new HttpGet(url);
                break;
            case HttpPost.METHOD_NAME:
                request = new HttpPost(url);
                break;
            case HttpPut.METHOD_NAME:
                request = new HttpPut(url);
                break;
            case HttpDelete.METHOD_NAME:
                request = new HttpDelete(url);
                break;
            default:
                break;
        }
        try {
            HttpResponse response = client.execute(request);
            String json = getJsonFromResponse(response);
            JsonParser parser = new JsonParser();
            return parser.parse(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getJsonFromResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        InputStream stream = entity.getContent();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        while((line = bufferedReader.readLine()) != null){
            builder.append(line);
        }
        return builder.toString();
    }
}
