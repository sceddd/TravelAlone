package com.example.myapplication.locationUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.LocationAdapter;
import com.example.myapplication.model.LocationInterface;
//import com.example.myapplication.model.WikiLoc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationView extends AppCompatActivity implements LocationInterface {
    private RecyclerView recyclerView;
    ArrayList<Location> locations = new ArrayList<>();
    // ------------------------------------------------------------
    //                    RecyclerView Location                  //
    // ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDatabase();
        setContentView(R.layout.recycler_view_location);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LocationAdapter locationAdapter = new LocationAdapter(this,locations,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(locationAdapter);
    }

    protected void setUpDatabase(){
        ConnSQL c = new ConnSQL();
        try {
            ResultSet rs = c.getSet("LOCATION");
            while (rs.next()){
                Location location = new Location(rs.getInt("locationID"),rs.getString("locationName"),
                    rs.getString("phoneNumber"),rs.getFloat("rating"), rs.getString("suggestionDay"));
                locations.add(location);
            }
        }
        catch (SQLException e) {
            Log.d("error", "onCreate: "+e);
        }
    }
    @Override
    public void onClickLocation(int pos) {
        Intent intent = new Intent(this, LocationDetails.class);
        intent.putExtra("Name",locations.get(pos).getLocName());
        intent.putExtra("LocationID",locations.get(pos).getLocId());
        intent.putExtra("PLocation",locations.get(pos).getLocNumber());
        intent.putExtra("Rating",locations.get(pos).getRating());
        intent.putExtra("SuggestDay",locations.get(pos).getSuggestionDate());
        startActivity(intent);
    }
}