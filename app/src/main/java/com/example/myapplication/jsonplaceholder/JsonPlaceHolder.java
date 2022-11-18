package com.example.myapplication.jsonplaceholder;

import com.example.myapplication.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<Location>> getPost();
}
