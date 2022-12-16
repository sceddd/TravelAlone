package com.example.myapplication.jsonplaceholder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationCities {
    @GET("w/api.php?action=query&format=json&prop=extracts&formatversion=2&exchars=400&exlimit=1&explaintext=1&exsectionformat=wiki")
    Call<String> getWiki(@Query("titles") String titles);
    @GET("w/api.php?action=query&format=json&prop=images&formatversion=2&imlimit=4")
    Call<String> returnListPic(@Query("titles") String page);
    @GET("w/api.php?action=query&format=json&prop=imageinfo&formatversion=2&iiprop=url")
    Call<String> returnPic(@Query("titles") String page);
}
