package com.example.myapplication.model;

import androidx.annotation.NonNull;

public class Location {
    private String phoneNumber,locationName;
    private String suggestionDate;
    private int locationID;
    private float rating;



    public Location(int locId, String locName, String locNumber, float rating, String suggestionDate ) {
        this.locationID = locId;
        this.phoneNumber = locNumber;
        this.locationName = locName;
        this.suggestionDate = suggestionDate;
        this.rating = rating;
    }
    public String getSuggestionDate() {
        return suggestionDate;
    }

    public void setSuggestionDate(String suggestionDate) {
        this.suggestionDate = suggestionDate;
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

    public void setLocNumber(String locNumber) {
        this.phoneNumber = locNumber;
    }

    public String getLocName() {
        return locationName;
    }

    public void setLocName(String locName) {
        this.locationName = locName;
    }

    public int getLocId(){
        return locationID;
    }


    public String getLocNumber(){
        return phoneNumber;
    }
    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", locationName='" + locationName + '\'' +
                ", suggestionDate=" + suggestionDate +
                ", locationID=" + locationID +
                ", rating=" + rating +
                '}';
    }
}
