package com.example.njxfuture.API;

import com.example.njxfuture.API.DataModels.MTMDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MoneyControlsAPI {
    static String BASE_URL = "https://www.moneycontrol.com/rss/";
    interface MTM {
        @GET("/MCtopnews.xml")
        Call<List<MTMDataModel>> getData();
    }


    // Create a Retrofit instance
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final MTM mtm = retrofit.create(MTM.class);
    public static Call<List<MTMDataModel>> fetchMTM() {
        return mtm.getData();
    }
}
