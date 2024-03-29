package com.example.myapplication.locationUI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.jsonplaceholder.LocationCities;
import com.example.myapplication.map.MapsActivity;
import com.google.android.gms.maps.model.LatLng;
//import com.example.myapplication.model.WikiLoc;

import org.json.JSONObject;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LocationDetails extends AppCompatActivity {
    private String locationName;
    private int locationID;
    private float rating;
    ImageButton openMapbtn;
    TextView description,locName;
    RatingBar ratingBar;
    ImageButton imB,favorB;
    Button ticketPageBtn;
    ArrayList<Bitmap> bitmaps= new ArrayList<>();
    LocationCities locationCities;
    ViewFlipper viewFlipper;
    LatLng pos;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);
        ConnSQL c = new ConnSQL();
        description = findViewById(R.id.description_text);
        openMapbtn = findViewById(R.id.openMap);
        locName = findViewById(R.id.labeled);
        ratingBar = findViewById(R.id.ratingBar);
        imB = findViewById(R.id.exitBtn);

        ticketPageBtn = findViewById(R.id.ticket_page);
        favorB = findViewById(R.id.addFav);
        locationID = getIntent().getIntExtra("LocationID",0);
        ResultSet rs = c.executeQ("SELECT * FROM LOCATION WHERE City_ID = '"+locationID+"'");
        try {
            rs.next();
            locationName = rs.getString("Name");
            rating = rs.getFloat("Rating");
            Log.d("11111", "onCreate: "+rs.getDouble("Latitude"));
            pos = new LatLng(rs.getDouble("Latitude"),rs.getDouble("Longtitude"));
                    } catch (SQLException e) {
            Log.d("ERROR GET VALUE", "onCreate: "+e);
        }
        favorB.setOnClickListener(v->{
            c.updateSet("Location","isLiked = '1'","City_ID = '"+locationID+"'");
            Toast.makeText(this, "add Favorite List", Toast.LENGTH_SHORT).show();
        });
        openMapbtn.setOnClickListener(v -> {
            Intent intent = new Intent(LocationDetails.this, MapsActivity.class);
            intent.putExtra("lat",pos.latitude);
            intent.putExtra("long",pos.longitude);
            intent.putExtra("posName",locationName);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Map open", Toast.LENGTH_LONG).show();
        });

        ratingBar.setRating(rating);
        imB.setOnClickListener(v -> finish());
        ratingBar.setOnRatingBarChangeListener((r,v,b)-> {
            c.updateSet("LOCATION", "RATING = " + v
                    , "City_ID = " + locationID);
            r.setIsIndicator(true);
        });
        ticketPageBtn.setOnClickListener(this::onClickToBuyTicket);

        // get description for the location on wiki q
        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        locationCities = retrofit.create(LocationCities.class);
        // By PT
        createViewFlipper();
    }

    public void onClickToBuyTicket(View v){
        Intent intent = new Intent(LocationDetails.this, BuyTicketPage.class);
        intent.putExtra("LOCATION", locationName);
        intent.putExtra("LocationID",locationID);
        startActivity(intent);
    }

    public void createViewFlipper(){
        String exception = "Hà Nội Hồ Chí Minh",title= locationName.replace(" ", "_");
        if (!exception.contains(locationName)) {
            title = title.concat("_province");
        }
        else {
            title = locationName.equals("Hà_Nội") ? "Ho Chi Minh city" : "Hanoi" ;
        }
        ArrayList<Bitmap> bitmap = getImageAndDesApi(title);
        viewFlipper = findViewById(R.id.flipView);
        for (Bitmap i:bitmap){
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(i);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setOnClickListener(view -> viewFlipper.showPrevious());

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

        viewFlipper.setFlipInterval(8000); // 4ms
        viewFlipper.setAutoStart(true);
        viewFlipper.startFlipping();
    }
    public ArrayList<Bitmap> getImageAndDesApi(String locationS){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Call<String> call = locationCities.getWiki(locationS);
        Call<String> locApiReq = locationCities.returnListPic(locationS);
        try {
            JSONObject json = new JSONObject(Objects.requireNonNull(call.execute().body()));
            String des = json.getJSONObject("query").getJSONArray("pages").getJSONObject(0).getString("extract");
            description.setText(des);
            JSONObject ob = new JSONObject(Objects.requireNonNull(locApiReq.execute().body()));
            for (int i=0;i<4;i++){
                String imageName = ob.getJSONObject("query").getJSONArray("pages").getJSONObject(0).getJSONArray("images").getJSONObject(i).getString("title");
                if (imageName.equals("File:Commons-logo.svg"))
                    continue;
                Call<String> imageCallRequest = locationCities.returnPic(imageName);
                JSONObject o = new JSONObject(Objects.requireNonNull(imageCallRequest.execute().body()));
                String imageUrl = o.getJSONObject("query").getJSONArray("pages").getJSONObject(0).getJSONArray("imageinfo").getJSONObject(0).getString("url");
                URL url = new URL(imageUrl);
                Bitmap bit = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                int nh = (int) ( bit.getHeight() * (512.0 / bit.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bit, 512, nh, true);
                bitmaps.add(scaled);
            }
        }catch (Exception e){
            Log.d("111111111111", "onCreate: "+e);
        }
        return bitmaps;
    }


}