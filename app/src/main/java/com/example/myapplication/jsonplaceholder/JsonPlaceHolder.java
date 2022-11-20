package com.example.myapplication.jsonplaceholder;

//import com.example.myapplication.model.WikiLoc;

import com.example.myapplication.model.WikiLoc.WikiLoc;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    @GET("w/api.php?action=query&format=json&prop=extracts&formatversion=2&exchars=400&exlimit=1&explaintext=1&exsectionformat=wiki")
    Call<WikiLoc> getWiki(@Query("titles") String titles);
}
