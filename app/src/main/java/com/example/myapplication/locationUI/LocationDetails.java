package com.example.myapplication.locationUI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.jsonplaceholder.JsonPlaceHolder;
import com.example.myapplication.model.WikiLoc.WikiLoc;
//import com.example.myapplication.model.WikiLoc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
    RatingBar ratingBar;
    ImageButton imB;
    Button ticketPageBtn;


    ViewFlipper viewFlipper;
    int[] images;


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
        locationID = getIntent().getIntExtra("LocationID",0);
        ResultSet rs = c.executeQ("SELECT * FROM LOCATION WHERE LOCATIONID = '"+locationID+"'");
        try {
            rs.next();
            locationName = rs.getString("locationName");
            phoneNumber = rs.getString("phoneNumber");
            suggestionDate = rs.getString("suggestionDay");
            rating = rs.getFloat("rating");
        } catch (SQLException e) {
            Log.d("ERROR GET VALUE", "onCreate: "+e);
        }
        locName.setText(locationName);
        ratingBar.setRating(rating);

        imB.setOnClickListener(v -> finish());
        ratingBar.setOnRatingBarChangeListener((r,v,b)-> {
            c.updateSet("LOCATION", "RATING = " + v
                    , "LOCATIONID = " + locationID);
            r.setIsIndicator(true);
        });
        ticketPageBtn.setOnClickListener(this::onClickToBuyTicket);

        // get description for the location on wiki
        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://en.wikipedia.org/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<WikiLoc> call = jsonPlaceHolder.getWiki(locationName);
        try {
            descript = Objects.requireNonNull(call.execute().body()).getQuery().getPages().get(0).getExtract();
        } catch (IOException e) {
            e.printStackTrace();
        }

        description.setText(descript);

//        description = findViewById(R.id.description_text);
//        locName = findViewById(R.id.labeled);
//        ratingBar = findViewById(R.id.ratingBar);
//        imB = findViewById(R.id.exitBtn);
//        ticketPageBtn = findViewById(R.id.ticket_page);
//        locationID = getIntent().getIntExtra("LocationID",0);
//        ResultSet rs = c.executeQ("SELECT * FROM LOCATION WHERE LOCATIONID = '"+locationID+"'");
//        Log.d("1111", "onCreate: "+"SELECT * FROM LOCATION WHERE LOCATIONID = '"+locationID+"')");
//        try {
//            rs.next();
//            locationName = rs.getString("locationName");
//            phoneNumber = rs.getString("phoneNumber");
//            suggestionDate = rs.getString("suggestionDay");
//            rating = rs.getFloat("rating");
//        } catch (SQLException e) {
//            Log.d("ERROR GET VALUE", "onCreate: "+e);
//        }
//
//
//        locName.setText(locationName);
//        ratingBar.setRating(rating);
//        imB.setColorFilter(Color.argb(255, 255, 255, 255));
//
//        imB.setOnClickListener(v -> finish());
//        ratingBar.setOnRatingBarChangeListener((r,v,b)-> c.updateSet("LOCATION","RATING = "+ v
//                , "LOCATIONID = "+ locationID));

//
//        // get description for the location on wiki
//        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(strictMode);
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://en.wikipedia.org/").addConverterFactory(GsonConverterFactory.create()).build();
//        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
//        Call<WikiLoc> call = jsonPlaceHolder.getWiki(locationName);
//        try {
//            descript = Objects.requireNonNull(call.execute().body()).getQuery().getPages().get(0).getExtract();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        description.setText(descript);


        // By PT
        images = new int[]{R.drawable.picture_1, R.drawable.picture_4};
        createViewFlipper(images);

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

    public void createViewFlipper(int[] images){
//        int[] images = {R.drawable.picture_1, R.drawable.picture_4};

        viewFlipper = findViewById(R.id.flipView);

        for (int image:images){
//            flipperImage(image);
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });


        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setFlipInterval(8000); // 4ms
        viewFlipper.setAutoStart(true);
        viewFlipper.startFlipping();
    }
}