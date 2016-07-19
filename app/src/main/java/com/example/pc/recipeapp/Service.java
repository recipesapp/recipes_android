package com.example.pc.recipeapp;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;

public class Service {
    //http://www.google.bg
    private static final String BASE_URL = "put_base_url_here";
    //      /getAddresses
    private static final String SERVICE_METHOD = "name";
        private static ApiService sSbarService;


        private static RestAdapter getRestAdapter(){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(BASE_URL)
                    .build();
            return restAdapter;
        }

        public static ApiService getApiService() {
            if(sSbarService == null){
                sSbarService = getRestAdapter().create(ApiService.class);
            }

            return sSbarService;
        }

    public interface ApiService {
        @FormUrlEncoded
        @GET(SERVICE_METHOD)
        void getRecepies(Callback<ArrayList<Recipe>> callback);
    }
}
