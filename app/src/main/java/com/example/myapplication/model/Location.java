package com.example.myapplication.model;

public class Location {
    private String locId,locNumber,locName;

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public void setLocNumber(String locNumber) {
        this.locNumber = locNumber;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocId(){
        return locId;
    }
    public String getLocNumber(){
        return locNumber;
    }
}
