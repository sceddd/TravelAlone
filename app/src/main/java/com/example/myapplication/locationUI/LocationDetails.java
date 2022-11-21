package com.example.myapplication.locationUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.jsonplaceholder.JsonPlaceHolder;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.WikiLoc.Page;
import com.example.myapplication.model.WikiLoc.WikiLoc;
//import com.example.myapplication.model.WikiLoc;

import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationDetails extends AppCompatActivity {
    private String locationName,phoneNumber,suggestionDate;
    private int locationID;
    private float rating;
    private String descript;
    TextView description,locName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);

        description = findViewById(R.id.description_text);
        locName = findViewById(R.id.labeled);

        locationName = getIntent().getStringExtra("Name");
        phoneNumber = getIntent().getStringExtra("PLocation");
        locationID = getIntent().getIntExtra("LocationID",0);
        suggestionDate = getIntent().getStringExtra("SuggestDay");

        locName.setText(locationName);

        // get description for the location on wiki

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://en.wikipedia.org/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<WikiLoc> call = jsonPlaceHolder.getWiki(locationName);
        try {
            descript = Objects.requireNonNull(call.execute().body()).getQuery().getPages().get(0).getExtract();
        } catch (IOException e) {
            e.printStackTrace();
        }
        description.setText(descript);
    }
}