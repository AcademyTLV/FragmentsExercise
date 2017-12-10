package com.yossisegev.fragmentslectureexercise.api;

import com.yossisegev.fragmentslectureexercise.entities.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yossi Segev on 09/12/2017.
 */

public interface UnsplashService {

    @GET("/collections/curated/?client_id=47fcafd8325da4e303bcea81da22e67ce35b5c41552b68517999fb085db9dcd1")
    Call<List<Collection>> getFeaturedCollections();
}
