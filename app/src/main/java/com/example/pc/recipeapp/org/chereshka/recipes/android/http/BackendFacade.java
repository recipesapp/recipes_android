package com.example.pc.recipeapp.org.chereshka.recipes.android.http;

import android.os.AsyncTask;
import android.os.Handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class BackendFacade {

    private static final String BACKEND_API_URL = "https://recipesi322053trial.hanatrial.ondemand.com/recipes-backend/api/v1/";

    public JsonArray getRecipes(){
        ResourceConsumer consumer = new ResourceConsumer();
        AsyncTask<String, Integer, JsonElement> task = consumer.execute("GET", BACKEND_API_URL + "recipes/allrecipes");
        try {
            return task.get().getAsJsonArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject getUserById(Long id){
        ResourceConsumer consumer = new ResourceConsumer();
        AsyncTask<String, Integer, JsonElement> task = consumer.execute("GET", BACKEND_API_URL + "user/" + String.valueOf(id));
        try {
            return task.get().getAsJsonObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JsonArray getFavoriteUserRecipes(Long userId){
        ResourceConsumer consumer = new ResourceConsumer();
        AsyncTask<String, Integer, JsonElement> task = consumer.execute("GET", BACKEND_API_URL + "user/favorites?userId" + String.valueOf(userId));
        try {
            return task.get().getAsJsonArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JsonArray getFavoriteUserRecipesByCategory(Long userId, String category){
        ResourceConsumer consumer = new ResourceConsumer();
        AsyncTask<String, Integer, JsonElement> task = consumer.execute("GET", BACKEND_API_URL + "user/favorites?userId" + String.valueOf(userId) + "&category="+category);
        try {
            return task.get().getAsJsonArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
