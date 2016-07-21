package com.example.pc.recipeapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.pc.recipeapp.org.chereshka.recipes.android.http.BackendFacade;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MainCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BackendFacade backendFacade = new BackendFacade();
        JsonArray mainRecipies = backendFacade.getFavoriteUserRecipesByCategory(3L, "MAIN");
        JsonObject object = mainRecipies.get(0).getAsJsonObject();
        String name = object.get("name").getAsString();

        TextView recipeNameView = (TextView) this.findViewById(R.id.textView10);
        recipeNameView.setText(name);

        JsonObject objectOne = mainRecipies.get(2).getAsJsonObject();
        String nameOne = object.get("name").getAsString();


        TextView recipeNameViewSecond = (TextView) this.findViewById(R.id.textView11);
        recipeNameView.setText(nameOne);

        JsonObject objectTwo = mainRecipies.get(2).getAsJsonObject();
        String nameTwo = object.get("name").getAsString();

        TextView recipeNameViewThird = (TextView) this.findViewById(R.id.textView12);
        recipeNameView.setText(nameTwo);




    }

}
