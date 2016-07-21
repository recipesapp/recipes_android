package com.example.pc.recipeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pc.recipeapp.org.chereshka.recipes.android.http.BackendFacade;
import com.example.pc.recipeapp.org.chereshka.recipes.android.http.ResourceConsumer;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        BackendFacade facade = new BackendFacade();
        JsonObject userAsJson = facade.getUserById(2L);
        System.out.println(userAsJson.toString());
    }
}
