package com.example.pc.recipeapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mRecipiName;
    private ListView mList;
    private ArrayList<String> mListData;
    private ArrayList<Recipe> mRecipies;
    private ArrayAdapter<String> mItemsAdapter;
    private boolean responseReceived;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById(R.id.btn_search).setOnClickListener(this);
        mList = (ListView) findViewById(R.id.list_result);
        mRecipiName = (EditText) findViewById(R.id.et_search);
        mListData = new ArrayList<>();
        mRecipies = new ArrayList<>();

        responseReceived = false;

        mRecipies.add(new Recipe("Scrambled Eggs"));
        mRecipies.add(new Recipe("Mashed Potatoes"));
        mRecipies.add(new Recipe("Chicken with rice"));

        mItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListData);
        mList.setAdapter(mItemsAdapter);

        //TODO: uncomment and add webservice url when it is known
//        getData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getData() {
        Service.getApiService().getRecepies(new Callback<ArrayList<Recipe>>() {
            @Override
            public void success(ArrayList<Recipe> recipes, Response response) {
                responseReceived = true;
                mRecipies.clear();
                mRecipies.addAll(recipes);
            }

            @Override
            public void failure(RetrofitError error) {
                responseReceived = false;
                mRecipies.clear();
                Toast.makeText(SearchActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search) {
            if (responseReceived) {
                String searchWord = mRecipiName.getText().toString();
                if (!searchWord.equals("")) {
                    mListData.clear();
                    mListData.addAll(sserachInRecepyArray(searchWord));
                    mItemsAdapter.notifyDataSetChanged();
                } else {
                    mListData.clear();
                    mItemsAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Enter search ohrase", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "We run out of service", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<String> sserachInRecepyArray(String keyWord) {
        ArrayList<String> result = new ArrayList<>();
        for (Recipe r : mRecipies) {
            if (r.contains(keyWord))
                result.add(r.getName());
        }

        return result;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Search Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.pc.recipeapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Search Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.pc.recipeapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
