package com.yossisegev.fragmentslectureexercise.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yossi Segev on 10/12/2017.
 */

public class UnsplashApiHelper {

    private Retrofit retrofit;

    public UnsplashApiHelper() {
         retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UnsplashService service() {
        return retrofit.create(UnsplashService.class);
    }
}
