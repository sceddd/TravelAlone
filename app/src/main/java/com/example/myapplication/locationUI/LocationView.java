package com.example.myapplication.locationUI;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.google.android.material.tabs.TabLayout;
//import com.example.myapplication.model.WikiLoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationView extends Fragment implements LocationInterface {
    private RecyclerView recyclerView;
    ArrayList<Location> locations = new ArrayList<>();
    LocationAdapter locationAdapter;
    TabLayout tabs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover,container,false);
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_discover);
        tabs = view.findViewById(R.id.tl_discover);
        locations = setUpDatabase("All");
        locationAdapter = new LocationAdapter(getContext(),locations,this);
        recyclerView.setAdapter(locationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> region = new ArrayList<>(Arrays.asList("All","NorthEast","Southwest", "SouthEast","North Central","South Central","SouthWest","Mekong Delta River"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                locations.clear();
                locations.addAll(setUpDatabase(region.get(tab.getPosition())));
                locationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                locations.clear();
                locations.addAll(setUpDatabase(region.get(tab.getPosition())));
                locationAdapter.notifyDataSetChanged();
            }
        });
    }

    protected ArrayList<Location> setUpDatabase(String region){
        ConnSQL c = new ConnSQL();
        ArrayList<Location> loc = new ArrayList<>();
        try {
            ResultSet rs;
            if (region.equals("All")){
                rs = c.getFullSet("LOCATION");
            }
            else {
                rs =c.getSetWithoutEle("LOCATION", "*", "REGION = '"+region+"'");
            }
            Log.d("111111", "setUpDatabase: "+rs);
            while (rs.next()){
                Location location = new Location(rs.getInt("City_ID"),rs.getString("Name"),rs.getFloat("Rating"),new LatLng(rs.getDouble("Longtitude"),rs.getDouble("Latitude")),rs.getString("Region"));
                loc.add(location);
            }
        }
        catch (SQLException e) {
            Log.d("error", "onCreate: "+e);
        }
        return loc;
    }
    @SuppressLint("NotifyDataSetChanged")
    protected void onTextFind(String text){
        ConnSQL c = new ConnSQL();
        ArrayList<Location> locs = new ArrayList<>();
        Log.d("11111", "onTextFind: "+text);
        try {
            ResultSet rs = c.executeQ("SELECT * " + "from Location" + " where lower(Name) like '%"+text+"%'");
            while (rs.next()) {
                Location location = new Location(rs.getInt("City_ID"),rs.getString("Name"),rs.getFloat("Rating"),new LatLng(rs.getDouble("Longtitude"),rs.getDouble("Latitude")),rs.getString("Region"));
                locs.add(location);
            }
        } catch (SQLException e) {
            Log.d("error", "onTextFind "+e);
        }
        locations.clear();
        if (locs.size()==0){
            return;
        }
        locations.addAll(locs);
        locationAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClickLocation(int pos) {
        Intent intent = new Intent(getContext(), LocationDetails.class);
        intent.putExtra("LocationID",locations.get(pos).getLocId());
        startActivity(intent);

    }

    ActivityResultLauncher<Intent> launchLocationDetail = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                    }
                }

            });
}