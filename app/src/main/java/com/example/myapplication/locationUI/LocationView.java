package com.example.myapplication.locationUI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.LocationAdapter;
import com.example.myapplication.model.LocationInterface;
import com.google.android.gms.maps.model.LatLng;
//import com.example.myapplication.model.WikiLoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationView extends Fragment implements LocationInterface {
    private RecyclerView recyclerView;
    ArrayList<Location> locations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_location,container,false);
    }
    public ArrayList<Location> getLocations(){
        return locations;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDatabase();
        recyclerView = view.findViewById(R.id.recyclerView);
        LocationAdapter locationAdapter = new LocationAdapter(getContext(),locations,this);
        recyclerView.setAdapter(locationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected void setUpDatabase(){
        ConnSQL c = new ConnSQL();
        try {
            ResultSet rs = c.getFullSet("LOCATION");
            while (rs.next()){
                Location location = new Location(rs.getInt("City_ID"),rs.getString("Name"),rs.getFloat("Rating"),new LatLng(rs.getDouble("Longtitude"),rs.getDouble("Latitude")),rs.getString("Region"));
                locations.add(location);
            }
        }
        catch (SQLException e) {
            Log.d("error", "onCreate: "+e);
        }
    }
    @Override
    public void onClickLocation(int pos) {
        Intent intent = new Intent(getContext(), LocationDetails.class);
        intent.putExtra("LocationID",locations.get(pos).getLocId());
        launchLocationDetail.launch(intent);
    }

    ActivityResultLauncher<Intent> launchLocationDetail = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent!=null) {
                    }
                }
            });
}