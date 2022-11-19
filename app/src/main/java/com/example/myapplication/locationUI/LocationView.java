package com.example.myapplication.locationUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.jsonplaceholder.JsonPlaceHolder;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.LocationAdapter;
import com.example.myapplication.model.LocationInterface;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationView extends AppCompatActivity implements LocationInterface {
    private RecyclerView recyclerView;
    // ------------------------------------------------------------
    //                    RecyclerView Location                  //
    // ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_location);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&continue=%7C%7C&titles=Indonesia%7CChina%7CVietnam&formatversion=2&exchars=500&exlimit=3&explaintext=1&exsectionformat=wiki&excontinue=0").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);


        call.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Location>> call, @NonNull Response<ArrayList<Location>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LocationView.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Location> locationsList = response.body();
                JSONObject obj = new JSONObject(response.body());
                LocationAdapter locationAdapter = new LocationAdapter(LocationView.this, locationsList,LocationView.this);
                recyclerView.setAdapter(locationAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Location>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClickLocation(int post) {

    }
    public String array2String(ArrayList<Location>){

    }
}