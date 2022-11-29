package com.example.myapplication.locationUI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.jsonplaceholder.JsonPlaceHolder;
import com.example.myapplication.model.WikiLoc.WikiLoc;
//import com.example.myapplication.model.WikiLoc;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationDetails extends AppCompatActivity {
    private String locationName,phoneNumber,suggestionDate;
    private int locationID;
    private float rating;
    private String descript;
    TextView description,locName;
    RatingBar ratingBar;
    ImageButton imB,ticketPageBtn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);
        ConnSQL c = new ConnSQL();
        description = findViewById(R.id.description_text);
        locName = findViewById(R.id.labeled);
        ratingBar = findViewById(R.id.ratingBar);
        imB = findViewById(R.id.exitBtn);
        ticketPageBtn = findViewById(R.id.ticket_page);

        locationName = getIntent().getStringExtra("Name");
        phoneNumber = getIntent().getStringExtra("PLocation");
        locationID = getIntent().getIntExtra("LocationID",0);
        suggestionDate = getIntent().getStringExtra("SuggestDay");
        rating = getIntent().getFloatExtra("Rating",0);

        locName.setText(locationName);
        ratingBar.setRating(rating);
        imB.setColorFilter(Color.argb(255, 255, 255, 255));

        imB.setOnClickListener(v -> finish());
        ratingBar.setOnRatingBarChangeListener((r,v,b)-> {
            c.updateSet("LOCATION","RATING = "+ v, "LOCATIONID = "+ locationID);
        });
        ticketPageBtn.setOnClickListener(this::onClickToBuyTicket);

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

    public void onClickToBuyTicket(View v){
        Intent intent = new Intent(LocationDetails.this, BuyTicketPage.class);
        intent.putExtra("LOCATION", locationName);
        intent.putExtra("LocationID",locationID);
        ticketPageLaunch.launch(intent);
    }
    ActivityResultLauncher<Intent> ticketPageLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                }
            });
}