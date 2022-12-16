package com.example.myapplication.locationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FavFragment extends Fragment implements LocationInterface {
    private RecyclerView recyclerView;
    ArrayList<Location> locations = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fav_list);
        ConnSQL c = new ConnSQL();

        ResultSet rs = c.getSetWithoutEle("Location","*","isLiked = '1'");
        try {
            while (rs.next()){
                locations.add(new Location(rs.getInt("City_ID"),rs.getString("Name"),rs.getFloat("Rating"),new LatLng(rs.getDouble("Longtitude"),rs.getDouble("Latitude")),rs.getString("Region")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        LocationAdapter locationAdapter = new LocationAdapter(getContext(),locations,this);
        recyclerView.setAdapter(locationAdapter);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onClickLocation(int pos) {
        Intent intent = new Intent(getContext(), LocationDetails.class);
        intent.putExtra("LocationID",locations.get(pos).getLocId());
        startActivity(intent);
    }
}