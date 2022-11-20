package com.example.myapplication.model;

import java.util.Date;

public class Location {
    private String phoneNumber,locationName;
    private Date suggestionDate;
    private int locationID;
    private float rating;



    public Location(int locId, String locName, String locNumber, float rating, Date suggestionDate ) {
        this.locationID = locId;
        this.phoneNumber = locNumber;
        this.locationName = locName;
        this.suggestionDate = suggestionDate;
        this.rating = rating;
    }
    public Date getSuggestionDate() {
        return suggestionDate;
    }

    public void setSuggestionDate(Date suggestionDate) {
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
}
