package com.example.myapplication.locationUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.jsonplaceholder.JsonPlaceHolder;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.WikiLoc.Page;
import com.example.myapplication.model.WikiLoc.WikiLoc;
//import com.example.myapplication.model.WikiLoc;

import java.sql.ResultSet;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://en.wikipedia.org/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<WikiLoc> call = jsonPlaceHolder.getWiki("Indonesia");

        call.enqueue(new Callback<WikiLoc>() {
            @Override
            public void onResponse(@NonNull Call<WikiLoc> call, @NonNull Response<WikiLoc> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Page wikiPage = response.body().getQuery().getPages().get(0);
                String description = wikiPage.getExtract();
                String name = wikiPage.getTitle();
            }
            @Override
            public void onFailure(@NonNull Call<WikiLoc> call, @NonNull Throwable t) {
            }
        });
    }
}