package com.example.myapplication.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
public class Location {
    private String region,locationName;
    private LatLng pos;

    private int locationID;
    private float rating;

    public Location(int locId, String locName, float rating,LatLng pos,String region) {
        this.locationID = locId;
        this.region = region;
        this.locationName = locName;
        this.pos = pos;
        this.rating = rating;
    }


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public void setLocId(int locId) {
        this.locationID = locId;
    }


    public String getLocName() {
        return locationName;
    }

    public void setLocName(String locName) {
        this.locationName = locName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "region='" + region + '\'' +
                ", locationName='" + locationName + '\'' +
                ", pos=" + pos +
                ", locationID=" + locationID +
                ", rating=" + rating +
                '}';
    }

    public int getLocId(){
        return locationID;
    }

}
