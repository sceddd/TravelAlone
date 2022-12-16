package com.example.myapplication.locationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.model.Location;
import com.example.myapplication.model.LocationAdapter;
import com.example.myapplication.model.LocationInterface;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HomeFragment extends Fragment implements LocationInterface {
    private TabLayout tabItem;
    ArrayList<Location> loc;
    RecyclerView rec;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String query1 = "SELECT * FROM LOCATION WHERE City_ID IN (44,58,24)";
        String query2 = "SELECT top 10 * FROM LOCATION Order by Rating Desc";
        loc = setUpDatabase(query1);
        tabItem = view.findViewById(R.id.tl_home);
        rec = view.findViewById(R.id.rv_home);
        LocationAdapter locAdap = getAdap(loc);
        rec.setAdapter(locAdap);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        tabItem.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        loc.clear();
                        loc.addAll(setUpDatabase(query1));
                        locAdap.notifyDataSetChanged();
                        break;
                    case 1:
                        loc.clear();
                        loc.addAll(setUpDatabase(query2));
                        locAdap.notifyDataSetChanged();
                        break;
                    case 2:
                        loc.clear();
                        locAdap.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public LocationAdapter getAdap(ArrayList<Location> locations) {
        return new LocationAdapter(getContext(),locations,this);
    }
    @Override
    public void onClickLocation(int pos) {
        Intent intent = new Intent(getContext(), LocationDetails.class);
        intent.putExtra("LocationID",loc.get(pos).getLocId());
        startActivity(intent);
    }
    protected ArrayList<Location> setUpDatabase(String query){
        ArrayList<Location> locations = new ArrayList<>();
        ConnSQL c = new ConnSQL();
        try {
            ResultSet rs = c.executeQ(query);
            while (rs.next()){
                Location location = new Location(rs.getInt("City_ID"),rs.getString("Name"),rs.getFloat("Rating"),new LatLng(rs.getDouble("Longtitude"),rs.getDouble("Latitude")),rs.getString("Region"));
                locations.add(location);
            }
        }
        catch (SQLException e) {
            Log.d("error", "onCreate: "+e);
        }
        return locations;
    }
}