package com.example.myapplication.locationUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.LocationAdapter;
import com.example.myapplication.model.LocationInterface;
//import com.example.myapplication.model.WikiLoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationView extends AppCompatActivity implements LocationInterface {
    private RecyclerView recyclerView;
    private ConnSQL conn = new ConnSQL();
    ArrayList<Location> locations;
    // ------------------------------------------------------------
    //                    RecyclerView Location                  //
    // ------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ResultSet resultSet = conn.getSet("LOCATION");
        try {
        while (!resultSet.next()) {
            Location location = new Location(resultSet.getInt("locationID"),resultSet.getString("locationName"),
                    resultSet.getString("phoneNumber"),resultSet.getFloat("rating"), resultSet.getDate("suggestionDate"));
            locations.add(location);
        }}catch (SQLException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.recycler_view_location);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LocationAdapter locationAdapter = new LocationAdapter(this,locations,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(locationAdapter);
    }

    @Override
    public void onClickLocation(int post) {

    }
}