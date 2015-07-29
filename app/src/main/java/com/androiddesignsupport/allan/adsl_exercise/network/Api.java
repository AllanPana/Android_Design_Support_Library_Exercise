package com.androiddesignsupport.allan.adsl_exercise.network;


import com.androiddesignsupport.allan.adsl_exercise.pojos.Flower;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by allan on 19/07/15.
 */
public interface Api {

    @GET("/feeds/flowers.json")
    public void getFlowerData(Callback<List<Flower>> response);
}
